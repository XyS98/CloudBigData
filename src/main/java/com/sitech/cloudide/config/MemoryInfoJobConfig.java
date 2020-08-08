/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-08-03 16:07:05
 * @LastEditTime: 2020-08-08 15:18:36
 */
package com.sitech.cloudide.config;

import com.sitech.cloudide.job.GetMemoryInfoJob;
import com.sitech.cloudide.job.InsertMemoryInfoJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemoryInfoJobConfig {

   /**
     * @MethodName: insertMemoryInfoDetail-->insertMemoryInfoTrigger
     * @Descripttion: 包装注册insertMemoryInfoJob
     * @param {null} 
     * @return {Trigger} 
     * @return {type}  
     * @Author: yjb
     * @version: 
     * @Date: 2020-08-04 11:10:55
     */
    
    // 使用jobDetail包装job
    @Bean
    public JobDetail insertMemoryInfoDetail(){
        return JobBuilder.newJob(InsertMemoryInfoJob.class).withIdentity("InsertMemoryInfo","MemoryInfo")
            .withDescription("定时使用shell工具获取Linux的MemoryInfo插入Mysql")
            .storeDurably()
            .build();
    }
    
    // 把jobDetail注册到Cron表达式的trigger上去
    @Bean
    public Trigger insertMemoryInfoTrigger() {
        CronScheduleBuilder  cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/2 * * * * ?");
        
        return TriggerBuilder.newTrigger().forJob(insertMemoryInfoDetail()).withIdentity("InsertMemoryInfoTrigger")
                .withSchedule(cronScheduleBuilder).build();
    }
  
    /**
     * @MethodName: getMemoryInfoDetail -->getMemoryInfoTrigger
     * @Descripttion: 包装注册GetMemoryInfoJob
     * @param {null} 
     * @return {Trigger} 
     * @return {type} 
     * @Author: yjb
     * @version: 
     * @Date: 2020-08-04 11:11:19
     */     

    @Bean
    public JobDetail getMemoryInfoDetail(){
        return JobBuilder.newJob(GetMemoryInfoJob.class).withIdentity("getMemoryInfoJob","MemoryInfo")
            .withDescription("定时从Mysql获取最新MemoryInfo数据")
            .storeDurably()
            .build();
    }

     // 把jobDetail注册到Cron表达式的trigger上去
     @Bean
     public Trigger getMemoryInfoTrigger() {
         CronScheduleBuilder  cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/2 * * * * ?");
         
         return TriggerBuilder.newTrigger().forJob(getMemoryInfoDetail()).withIdentity("getMemoryInfoTrigger")
                 .withSchedule(cronScheduleBuilder).build();
     }
}
