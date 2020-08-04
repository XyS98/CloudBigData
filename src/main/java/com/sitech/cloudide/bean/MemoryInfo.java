/*
 * @Descripttion: Linux 内存信息
 * @Command: free -m
 * @Linux return:          total  used  free  shared  buff/cache  available
 *                  mem     xxx   xxx    xxx     xxx       xxx         xxx 
 *                  Swpa    xxx   xxx    xxx     xxx       xxx         xxx 
 * @Command: date
 * @Author: yjb
 * @Date: 2020-07-29 17:04:38
 * @LastEditTime: 2020-08-03 13:55:39
 */

package com.sitech.cloudide.bean;

import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@Data   // 自动提供 getting 、 setting、equals、canEqual、hashCode、toString 方法
public class MemoryInfo {

    private int memID;          //memroy表内存 (primary Key)
    private int linux_id;       // Linux主机id
    private int memkid;     // (fk -->MemroyKind)内存类型: 1 mem --> 物理内存 2 Swap--> 硬盘交换分区
    private int total;          //
    private int used;           // 分配给缓存（包含buffers 与cache ）使用的数量
    private int free;           // 未被使用的物理内存数量
    private int shared;         // 被共享使用的物理内存大小
    private int buffORcache;    // 被 buffer 和 cache 使用的物理内存大小
    private int available;      // 还可以被 应用程序 使用的物理内存大小 
    private String selectedTime;      // Linux接受查询命令时一同查询当前时间
    
    public MemoryInfo() {
    }

    public MemoryInfo(int linux_id, int memkid, int total, int used, int free, int shared, int buffORcache,
            int available, String selectedTime) {
        this.linux_id = linux_id;
        this.memkid = memkid;
        this.total = total;
        this.used = used;
        this.free = free;
        this.shared = shared;
        this.buffORcache = buffORcache;
        this.available = available;
        this.selectedTime = selectedTime;
    }
    
    /**
     * @MethodName: deleteMemoryInfo
     * @Descripttion: 初始化MemoryInfo对象中存储数据 int->0 String->null
     * @param {null} 
     * @return {void} 
     * @Author: yjb
     * @version: 
     * @Date: 2020-08-03 11:29:15
     */
    public void deleteMemoryInfo(){
        
        this.linux_id = 0;
        this.memkid = 0;
        this.total = 0;
        this.used = 0;
        this.free = 0;
        this.shared = 0;
        this.buffORcache = 0;
        this.available = 0;
        this.selectedTime = null;
    }
    
}