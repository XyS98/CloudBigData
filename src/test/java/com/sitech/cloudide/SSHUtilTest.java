package com.sitech.cloudide;

import com.sitech.cloudide.ssh.SSHException;
import com.sitech.cloudide.utils.SSHUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SSHUtilTest {

    @Test
    public void testMymethod() {
        String ip = "192.168.6.131";
        int port = 22;
        String username = "root";
        String password = "root";
        String str = "";
        String cmd = "free -m";
        try {
            str = SSHUtil.execute(ip, port, username, password, cmd);
        } catch (SSHException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("==========="+str);
    }
}

