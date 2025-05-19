package com.giggle.comfy.api.ctrl;

import com.giggle.comfy.common.result.Result;
import com.giggle.comfy.connect.api.TasksFeignClient;
import com.giggle.comfy.connect.api.fallback.TasksFallbackFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TasksCtrl {

    private final TasksFeignClient tasksFeignClient;

    @PostMapping("/generate")
    public Result<?> generateTextToImageTask(@RequestBody Map<String,Object> param){
        return tasksFeignClient.generateTextToImageTask(param);
    }
    @GetMapping("/tasks/{taskId}")
    public Result<?> getTask(@PathVariable("taskId") String taskId){
        tasksFeignClient.getTask(taskId);
        return Result.success();
    }
    @DeleteMapping("/tasks/{taskId}")
    public Result<?> delTask(@PathVariable("taskId") String taskId){
        tasksFeignClient.delTask(taskId);
        return Result.success();
    }

}
