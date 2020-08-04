/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-08-03 22:01:07
 * @LastEditTime: 2020-08-03 22:02:32
 */
package com.sitech.cloudide.config;

import com.sitech.cloudide.job.GetMemoryInfoJob;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetMemoryInfoJobConfig {
    @Bean
    public JobDetail getMemoryInfoDetail(){
        return JobBuilder.newJob(GetMemoryInfoJob.class).withIdentity("getMemoryInfoJob","MemoryInfo")
            .withDescription("定时Mysql获取最新数据")
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