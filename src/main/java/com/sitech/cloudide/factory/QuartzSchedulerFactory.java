/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-08-03 16:38:46
 * @LastEditTime: 2020-08-03 16:50:50
 */
package com.sitech.cloudide.factory;

import java.util.Properties;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzSchedulerFactory {
    
    public Scheduler getScheduler() throws SchedulerException{
    StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
    Properties properties = new Properties();
    properties.put(stdSchedulerFactory.PROP_THREAD_POOL_CLASS, "org.quartz.simpl.SimpleThreadPool");
    properties.put("org.quartz.threadPool.threadCount", 5);
    stdSchedulerFactory.initialize(properties);
    return stdSchedulerFactory.getScheduler();
    }
}