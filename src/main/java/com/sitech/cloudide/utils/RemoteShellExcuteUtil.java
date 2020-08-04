/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-07-29 16:06:08
 * @LastEditTime: 2020-08-03 15:01:40
 */
package com.sitech.cloudide.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import org.springframework.stereotype.Component;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
/**
 * 执行远程Linux系统的Shell命令
 * @author 16414
 *
 */

@Component
public class RemoteShellExcuteUtil {
    private static String ip;
    private static String username;
    private static String password;

    public RemoteShellExcuteUtil(){
        
    }

    public void getLinuxInfo(String ip, String username, String password) {
        this.ip=ip;
        this.username=username;
        this.password=password;
    }

    
    public String exec(String command) throws IOException {
        boolean rtn = false;
        Connection conn = new Connection(ip);
        conn.connect();
        boolean isAuthenticated = conn.authenticateWithPassword(username, password);
        if (isAuthenticated == false) {
            throw new IOException("Authentication failed.");
        }
        Session sess = conn.openSession();
        sess.execCommand(command);

        InputStream stdout = new StreamGobbler(sess.getStdout());
        BufferedReader br = new BufferedReader(new InputStreamReader(stdout));

        InputStream stderr = new StreamGobbler(sess.getStderr());
        BufferedReader stderrReader = new BufferedReader(new InputStreamReader(stderr));

        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line);
        }
        while (true) {
            line = stderrReader.readLine();
        if (line == null)
             break;
        stringBuilder.append(line);
        }

        sess.close();
        conn.close();
    return stringBuilder.toString();
    }
}