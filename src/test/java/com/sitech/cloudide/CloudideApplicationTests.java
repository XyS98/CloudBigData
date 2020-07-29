package com.sitech.cloudide;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class CloudideApplicationTests {

	@Test
	public void contextLoads() {
        String version = SpringVersion.getVersion();
        System.out.println("version："+version);
	}

}
