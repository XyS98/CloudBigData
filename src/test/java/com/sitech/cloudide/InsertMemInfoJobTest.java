/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-08-03 17:02:02
 * @LastEditTime: 2020-08-03 17:23:17
 */
package com.sitech.cloudide;

import com.sitech.cloudide.config.MemoryInfoJobConfig;
import com.sitech.cloudide.job.InsertMemoryInfoJob;
import com.sitech.cloudide.utils.QuartzManagerUtil;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CloudideApplication.class)
public class InsertMemInfoJobTest {

    @Autowired
    private InsertMemoryInfoJob insertMemInfoJob;
    @Autowired
    private MemoryInfoJobConfig memoryInfoJobConfig;
    @Autowired
    private QuartzManagerUtil quartzManagerUtil;

    public void startMemoryInsertJob(){
        
        // quartzManagerUtil.triggerJob(jobName);
    }
    
    
}