package com.sitech.cloudide.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class LocalShellExcuteUtil {
    
    public String exec(String shellPath) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Process process1 = null;
        String line = null;
         
        process1 = Runtime.getRuntime().exec(shellPath);
        BufferedReader bufferedReader = new BufferedReader(
        new InputStreamReader(process1.getInputStream(), StandardCharsets.UTF_8));
        
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
}