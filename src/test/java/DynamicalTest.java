import java.text.SimpleDateFormat;
import java.util.Date;

// import com.sitech.cloudide.job.AnotherDynamicJob;
// import com.sitech.cloudide.job.MyDynamicJob;
// import com.sitech.cloudide.utils.QuartzManagerUtil;


/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-07-31 17:21:47
 * @LastEditTime: 2020-08-03 11:16:30
 */ 

public class DynamicalTest {
    
    public static void main(String[] args) {
        // System.err.println("【系统启动】"); 
        // String jobName = "dynamciJob1";
        // String cron = "0/10 * * * * ?";
        // QuartzManagerUtil.addJob(jobName, MyDynamicJob.class, cron);
        // System.err.println("-----添加任务1----");
        // QuartzManagerUtil.getTriggerState(jobName);
        
        // String newJobName = "AnotherDynamicJob";
        // QuartzManagerUtil.modifyJob(jobName, newJobName,AnotherDynamicJob.class, cron);
        
        // // 修改任务触发时间
        // String newCron ="0/15 * * * * ? "; 
        // QuartzManagerUtil.modifyJobTime(jobName, newCron);

        
        java.util.Date utilTime = new Date();
        System.out.println("utilTime:"+ utilTime.getTime());
        java.sql.Date sqlTime = new java.sql.Date(utilTime.getTime());
        System.out.println("sqlTime:"+ sqlTime.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String simpledate = simpleDateFormat.format(utilTime);
        System.err.println("simpledate"+ simpledate);
     }
}