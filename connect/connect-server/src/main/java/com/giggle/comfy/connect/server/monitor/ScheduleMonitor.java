package com.giggle.comfy.connect.server.monitor;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@Component
@Slf4j
public class ScheduleMonitor {

    @Autowired
    private NacosDiscoveryProperties discoveryProperties;
    @Autowired
    private NacosServiceManager nacosServiceManager;
    @Autowired
    private GPUProcessor gpuProcessor;
    private final Random random = new Random();

    @PostConstruct
    public void init() {
        // 每5秒更新一次CPU数据到Nacos元数据
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            Instance instance = new Instance();
            instance.setIp(discoveryProperties.getIp());
            instance.setPort(discoveryProperties.getPort());
            instance.setServiceName(discoveryProperties.getService());
            instance.setClusterName(discoveryProperties.getGroup());
            instance.setMetadata(discoveryProperties.getMetadata());
            instance.setEphemeral(discoveryProperties.isEphemeral());
            instance.setWeight(discoveryProperties.getWeight());
            instance.getMetadata().put("gpu_usage", String.valueOf(random.nextInt(101)));
            log.info("ScheduleMonitor real get gpuInfo : {}",gpuProcessor.getGpuInfo());
            try {
                nacosServiceManager.getNamingService().registerInstance(instance.getServiceName(),discoveryProperties.getGroup(), instance);
            } catch (NacosException e) {
                log.error("ScheduleMonitor error ",e);
            }
        }, 0, 5, TimeUnit.SECONDS);
    }
}
