package com.giggle.comfy.common.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class GPURandomLoadBalancer implements ReactorServiceInstanceLoadBalancer {
    private final ServiceInstanceListSupplier serviceInstances;

    public GPURandomLoadBalancer(ServiceInstanceListSupplier serviceInstances) {
        this.serviceInstances = serviceInstances;
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        return serviceInstances.get()
                .next()
                .map(this::getInstanceResponse);
    }

    /**
     * 获取当前服务列表的，最小的 GPU使用率
     * @param instances 服务列表
     * @return 返回最小GPU使用率
     */
    private Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> instances) {
        log.info("GPURandomLoadBalancer getInstanceResponse instances : {}",instances);
        if (instances.isEmpty()) {
            return new DefaultResponse(null);
        }

        // 模拟获取 GPU 使用率
        Optional<ServiceInstance> minGPUUsageInstance = instances.stream().min(Comparator.comparingInt(this::getInstanceGPUUsage));

        return minGPUUsageInstance.map(DefaultResponse::new)
                .orElseGet(() -> new DefaultResponse(instances.getFirst()));
    }

    /**
     * 从元数据中获取 GPU使用率
     * @param instance 服务实例
     * @return 返回 gpu 使用率
     */
    private int getInstanceGPUUsage(ServiceInstance instance) {
        String gpuUsage = instance.getMetadata().getOrDefault("gpu_usage", "0");
        try {
            return Integer.parseInt(gpuUsage);
        } catch (NumberFormatException e) {
            return 0; // 默认gpu使用率 为 0
        }
    }
}
