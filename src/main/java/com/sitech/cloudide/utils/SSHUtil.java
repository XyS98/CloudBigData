package com.sitech.cloudide.utils;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.sitech.cloudide.bean.MachineStats;
import com.sitech.cloudide.ssh.BaseConstant;
import com.sitech.cloudide.ssh.IllegalParamException;
import com.sitech.cloudide.ssh.SSHException;
import com.sitech.cloudide.ssh.SSHTemplate;
import com.sitech.cloudide.ssh.SSHTemplate.DefaultLineProcessor;
import com.sitech.cloudide.ssh.SSHTemplate.LineProcessor;
import com.sitech.cloudide.ssh.SSHTemplate.Result;
import com.sitech.cloudide.ssh.SSHTemplate.SSHCallback;
import com.sitech.cloudide.ssh.SSHTemplate.SSHSession;

/**
 * 
 * <p>Title: SSHUtil</p>
 * <p>Description: ssh工具</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: 思特奇 </p>
 * @author heweia
 * @version 1.0
 * @createtime 2019-4-9 上午9:07:25
 */

public class SSHUtil {

    private static final Logger logger = LoggerFactory.getLogger(SSHUtil.class);

    /**
     * WORD_SEPARATOR ( char )2
     */
    public static final String WORD_SEPARATOR = Character.toString((char) 2);

    public static final String SYSTEM_PROPERTY_CONFIG_FILE_PATH = "configFilePath";
    
    private final static String COMMAND_TOP = "top -b -n 1 | head -5";
    private final static String COMMAND_DF_LH = "df -lh";
    private final static String LOAD_AVERAGE_STRING = "load average: ";
    private final static String COMMAND_MEM = "cat /proc/meminfo | grep -E -w 'MemTotal|MemFree|Buffers|Cached'";
    private final static String MEM_TOTAL = "MemTotal";
    private final static String MEM_FREE = "MemFree";
    private final static String MEM_BUFFERS = "Buffers";
    private final static String MEM_CACHED = "Cached";
    
    //使用 @SSHTemplate 重构SSHUtil
    private final static SSHTemplate sshTemplate = new SSHTemplate();

    /**
     * Get HostPerformanceEntity[cpuUsage, memUsage, load] by ssh.<br>
     * 方法返回前已经释放了所有资源，调用方不需要关心
     *
     * @param ip
     * @param userName
     * @param password
     * @throws Exception
     * @since 1.0.0
     */
    //获取主机的信息
    public static MachineStats getMachineInfo(String ip, int port, String userName, String password) throws SSHException {
    	//判断ip参数是否为空
        if (StringUtil.isBlank(ip)) {
            try {
                throw new IllegalParamException("Param ip is empty!");
            } catch (IllegalParamException e) {
                throw new SSHException(e.getMessage(), e);
            }
        }
        //获取端口，如果端口小于0则使用默认的端口
        port = IntegerUtil.defaultIfSmallerThan0(port, BaseConstant.SSH_PORT_DEFAULT);
        
        //定义主机性能数据实体对象
        final MachineStats systemPerformanceEntity =  new MachineStats();
        
        //设置主机的指标数据实体
        systemPerformanceEntity.setIp(ip);
        
        //这个方法获取主机其他数据并且补充到主机性能数据实体对象中
        sshTemplate.execute(ip, port, userName, password, new SSHCallback() {
			public Result call(SSHSession session) {
				//解析top命令
				session.executeCommand(COMMAND_TOP, new DefaultLineProcessor() {
					public void process(String line, int lineNum) throws Exception {
		                if (lineNum > 5) {
		                    return;
		                }
		                if (1 == lineNum) {
		                    // 第一行，通常是这样：
		                    // top - 19:58:52 up 416 days, 30 min, 1 user, load average: 0.00, 0.00, 0.00
		                    int loadAverageIndex = line.indexOf(LOAD_AVERAGE_STRING);
		                    String loadAverages = line.substring(loadAverageIndex).replace(LOAD_AVERAGE_STRING, "");
		                    String[] loadAverageArray = loadAverages.split(",");
		                    if (3 == loadAverageArray.length) {
		                    	systemPerformanceEntity.setLoads(StringUtil.trimToEmpty(loadAverageArray[0]));
		                    }
		                } else if (3 == lineNum) {
		                    // 第三行通常是这样：
		                    // redhat:   %Cpu(s):  0.0 us, 0.0 sy, 0.0 ni, 100.0 id, 0.0 wa,0.0 hi, 0.0 si,0.0 st
		                	// 
		                	// centos7:Cpu(s): 0.0% us
		                    double cpuUs = getUsCpu(line);
		                    systemPerformanceEntity.setCpuUsage(String.valueOf(cpuUs));
		                } 
					}
				});
				
				//解析memory
				session.executeCommand(COMMAND_MEM, new LineProcessor() {
					private String totalMem;
					private String freeMem;
					private String buffersMem;
					private String cachedMem;
					public void process(String line, int lineNum) throws Exception {
						if (line.contains(MEM_TOTAL)) {
		            		totalMem = matchMemLineNumber(line).trim();
		            	} else if (line.contains(MEM_FREE)) {
		            		freeMem = matchMemLineNumber(line).trim();
		            	} else if (line.contains(MEM_BUFFERS)) {
		            		buffersMem = matchMemLineNumber(line).trim();
		            	} else if (line.contains(MEM_CACHED)) {
		            		cachedMem = matchMemLineNumber(line).trim();
		            	}
					}
					public void finish() {
						if (!StringUtil.isBlank(totalMem, freeMem, buffersMem)) {
							Long totalMemLong = NumberUtils.toLong(totalMem);
							Long freeMemLong = NumberUtils.toLong(freeMem);
							Long buffersMemLong = NumberUtils.toLong(buffersMem);
							Long cachedMemLong = NumberUtils.toLong(cachedMem);
							Long usedMemFree = freeMemLong + buffersMemLong + cachedMemLong;
							Double memoryUsage = 1 - (NumberUtils.toDouble(usedMemFree.toString()) / NumberUtils.toDouble(totalMemLong.toString()) / 1.0);
							systemPerformanceEntity.setMemoryTotal(String.valueOf(totalMemLong));
							systemPerformanceEntity.setMemoryFree(String.valueOf(usedMemFree));
							DecimalFormat df = new DecimalFormat("0.00");
							systemPerformanceEntity.setMemoryUsageRatio(df.format(memoryUsage * 100));
						}
					}
				});
				
				// 统计磁盘使用状况
				/**
	             * 内容通常是这样： Filesystem 容量 已用 可用 已用% 挂载点 /dev/xvda2 5.8G 3.2G 2.4G
	             * 57% / /dev/xvda1 99M 8.0M 86M 9% /boot none 769M 0 769M 0%
	             * /dev/shm /dev/xvda7 68G 7.1G 57G 12% /home /dev/xvda6 2.0G 36M
	             * 1.8G 2% /tmp /dev/xvda5 2.0G 199M 1.7G 11% /var
	             * */
				session.executeCommand(COMMAND_DF_LH, new LineProcessor() {
					private Map<String, String> diskUsageMap = new HashMap<String, String>();
                    String diskUsageMapStr = "";
                    public void process(String line, int lineNum) throws Exception {
						if(lineNum == 1) {
							return;
						}
						line = line.replaceAll(" {1,}", WORD_SEPARATOR);
		                String[] lineArray = line.split(WORD_SEPARATOR);
		                if (6 == lineArray.length) {
		                	String diskUsage = lineArray[4];
		                	String mountedOn = lineArray[5];
                            diskUsageMap.put(mountedOn, diskUsage);
                        }
                        diskUsageMapStr = JSON.toJSONString(diskUsageMap); // 便与存入数据库
					}
					public void finish() {
						systemPerformanceEntity.setDiskUsageMap(diskUsageMapStr);
					}
				});
				
				return null;
			}
		});

        // 统计当前网络流量 @TODO 
        Double traffic = 0.0;
        systemPerformanceEntity.setTraffic(traffic.toString());

        //返回主机性能数据实体对象
        return systemPerformanceEntity;
    }

    /**
     * SSH 方式登录远程主机，执行命令,方法内部会关闭所有资源，调用方无须关心。
     *
     * @param ip       主机ip
     * @param username 用户名
     * @param password 密码
     * @param command  要执行的命令
     */
    public static String execute(String ip, int port, String username, String password, 
    		final String command) throws SSHException {

        if (StringUtil.isBlank(command)) {
        	return "";
        }
        port = IntegerUtil.defaultIfSmallerThan0(port,  BaseConstant.SSH_PORT_DEFAULT);
        
        Result rst = sshTemplate.execute(ip, port, username, password, new SSHCallback() {
			public Result call(SSHSession session) {
				return session.executeCommand(command);
			}
		});
        if(rst.isSuccess()) {
        	return rst.getResult();
        }
        return "";
    }

    /**
     * @param ip
     * @param port
     * @param username
     * @param password
     * @param localPath
     * @param remoteDir
     * @return
     * @throws SSHException
     */
    public static boolean scpFileToRemote(String ip, int port, String username, 
    		String password, final String localPath, final String remoteDir) throws SSHException{
    	Result rst = sshTemplate.execute(ip, port, username, password, new SSHCallback() {
			public Result call(SSHSession session) {
				return session.scpToDir(localPath, remoteDir, "0644");
			}
		});
    	if(rst.isSuccess()) {
    		return true;
    	}
    	if(rst.getExcetion() != null) {
    		throw new SSHException(rst.getExcetion());
    	}
    	return false;
    }

    /**
     * 重载，使用默认端口、用户名和密码
     *
     * @param ip
     * @param localPath
     * @param remoteDir
     * @return
     * @throws SSHException
     */
    public static boolean scpFileToRemote(String ip, String localPath, String remoteDir) throws SSHException {
        int sshPort = SSHUtil.getSshPort(ip);
        return scpFileToRemote(ip, sshPort,  BaseConstant.USERNAME,  BaseConstant.PASSWORD, localPath, remoteDir);
    }

    /**
     * 重载，使用默认端口、用户名和密码
     *
     * @param ip
     * @param cmd
     * @return
     * @throws SSHException
     */
    public static String execute(String ip, String cmd) throws SSHException {
        int sshPort = SSHUtil.getSshPort(ip);
        return execute(ip, sshPort,  BaseConstant.USERNAME,  BaseConstant.PASSWORD, cmd);
    }

    /**
     * 查看机器ip上的端口port是否已被占用；
     *
     * @param ip    机器ip
     * @param port  要检查的端口
     * @return  如果被占用返回true，否则返回false；
     * @throws SSHException
     */
    public static boolean isPortUsed(String ip, int port) throws SSHException {
        /**
         * 执行ps命令，查看端口，以确认刚才执行的shell命令是否成功，返回一般是这样的：
         *  root     12510 12368  0 14:34 pts/0    00:00:00 redis-server *:6379
         */
        String psCmd = "/bin/ps -ef | grep %s | grep -v grep";
        psCmd = String.format(psCmd, port);
        String psResponse = execute(ip, psCmd);
        boolean isUsed = false;

        if (StringUtils.isNotBlank(psResponse)) {
            String[] resultArr = psResponse.split(System.lineSeparator());
            for (String resultLine: resultArr) {
                if (resultLine.contains(String.valueOf(port))) {
                    isUsed = true;
                    break;
                }
            }
        }
        return isUsed;
    }

    /**
     * 通过ip来判断ssh端口
     *
     * @param ip
     * @return
     */
    public static int getSshPort(String ip) {
        /**
         * 如果ssh默认端口不是22,请自行实现该逻辑
         */
        return  BaseConstant.SSH_PORT_DEFAULT;
    }

    /**
     * 匹配字符串中的数字
     * 
     * @param content
     * @return
     */
    private static String matchMemLineNumber(String content) {
        String result = "";
        if (content == null || "".equals(content.trim())) {
            return result;
        }
        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            result = matcher.group(1);
        }
        return result;
    }
    
    /**
     * 从top的cpuLine解析出us
     * @param cpuLine
     * @return
     */
    public static double getUsCpu(String cpuLine) {
        if (cpuLine == null || "".equals(cpuLine.trim())) {
            return 0;
        }
        String[] items = cpuLine.split(BaseConstant.COMMA);
        if (items.length < 1) {
            return 0;
        }
        String usCpuStr = items[0];
        return NumberUtils.toDouble(matchCpuLine(usCpuStr));
    }

    private static String matchCpuLine(String content) {
        String result = "";
        if (content == null || "".equals(content.trim())) {
            return result;
        }
        Pattern pattern = Pattern.compile("(\\d+).(\\d+)");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            result = matcher.group();
        }
        return result;
    }
    /**
     * 
     * @Title: testMachineStats
     * @Description: 使用自己知道主机、用户、密码进行验证测试
     * @throws SSHException
     * @return void
     * @author heweia
     * @version 1.0
     * @createtime 2019-4-9 上午11:41:10
     */
    // public static void main(String[] args) {
    //         String ip = "192.168.6.131";
    //         int port = 22;
    //         String username = "root";
    //         String password = "root";
    //         String str = "";
    //         String cmd = "free -m";
    //         try {
    //             str = SSHUtil.execute(ip, port, username, password, cmd);
    //         } catch (SSHException e) {
    //             // TODO Auto-generated catch block
    //             e.printStackTrace();
    //         }
    //         String result  = SSHUtil.matchMemLineNumber(str);
    //         System.out.println("==========="+str);
    //         System.out.println("==========="+result);
    //     }         
}
