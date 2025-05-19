package com.giggle.comfy.connect.api;

import com.giggle.comfy.common.result.Result;
import com.giggle.comfy.common.web.config.FeignConfig;
import com.giggle.comfy.connect.api.fallback.TasksFallbackFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(value = "connect-server",fallback = TasksFallbackFeignClient.class,configuration = FeignConfig.class)
public interface TasksFeignClient {

    @PostMapping("/api/v1/prompt")
    Result<?> generateTextToImageTask(Map<String,Object> param);
    @GetMapping("/api/v1/tasks/{taskId}")
    Result<?> getTask(@PathVariable("taskId") String taskId);
    @DeleteMapping("/api/v1/tasks/{taskId}")
    Result<?> delTask(@PathVariable("taskId") String taskId);
}
