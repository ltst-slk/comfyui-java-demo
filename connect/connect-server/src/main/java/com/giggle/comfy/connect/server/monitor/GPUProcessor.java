package com.giggle.comfy.connect.server.monitor;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Getter
@Slf4j
@Component
public class GPUProcessor {
    //解析代码暂时不写
    volatile String gpuInfo = "";

    /**
     * 实时解析 GPU使用信息
     */
    @PostConstruct
    public  void start() {
        ProcessBuilder bp = new ProcessBuilder("radeontop -d -");
        new Thread(()->{
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(bp.start().getInputStream()));
                String line;
                while ((line = reader.readLine()) != null){
                    log.info("GPUProcessor gpu info :{}",line);
                    gpuInfo = line;
                }
            } catch (IOException e){
                log.error("GPUProcessor gpu error");
            }
        }).start();
    }
}
