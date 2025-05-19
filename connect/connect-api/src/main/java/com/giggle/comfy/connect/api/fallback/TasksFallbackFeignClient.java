package com.giggle.comfy.connect.api.fallback;

import com.giggle.comfy.common.result.Result;
import com.giggle.comfy.connect.api.TasksFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class TasksFallbackFeignClient implements TasksFeignClient {

    @Override
    public Result<?> generateTextToImageTask(Map<String,Object> param) {
        log.error("TasksFallbackFeignClient generateTextToImageTask 服务降级");
        return Result.failed();
    }

    @Override
    public Result<?> getTask(String taskId) {
        log.error("TasksFallbackFeignClient getTask 服务降级 taskId:{}",taskId);
        return Result.failed();
    }

    @Override
    public Result<?> delTask(String taskId) {
        log.error("TasksFallbackFeignClient delTask 服务降级 taskId:{}",taskId);
        return Result.failed();
    }
}
