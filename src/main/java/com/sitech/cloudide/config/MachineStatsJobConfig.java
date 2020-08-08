/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-08-08 15:15:11
 * @LastEditTime: 2020-08-08 15:18:10
 */
package com.sitech.cloudide.config;

import com.sitech.cloudide.job.MachineStatsJob;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MachineStatsJobConfig {
    @Bean
    public JobDetail getMemoryInfoDetail(){
        return JobBuilder.newJob(MachineStatsJob.class).withIdentity("MachineJob","MachineState")
            .withDescription("定时从Mysql获取最新主机性能数据")
            .storeDurably()
            .build();
    }

     // 把jobDetail注册到Cron表达式的trigger上去
     @Bean
     public Trigger getMemoryInfoTrigger() {
         CronScheduleBuilder  cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/2 * * * * ?");
         
         return TriggerBuilder.newTrigger().forJob(getMemoryInfoDetail()).withIdentity("MachineStateTrigger")
                 .withSchedule(cronScheduleBuilder).build();
     }
}