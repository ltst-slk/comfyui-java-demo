package com.giggle.comfy.connect.server.ctrl;

import com.giggle.comfy.common.result.Result;
import com.giggle.comfy.connect.server.service.TaskService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TasksCtrl {
    @Resource
    private TaskService taskService;

    @PostMapping("/prompt")
    public Result<?> generateTextToImageTask(@RequestBody Map<String,Object> param){
        HashMap<String, Object> taskResult = taskService.addTask(param);
        return Result.success(taskResult);
    }
    @GetMapping("/tasks/{taskId}")
    public Result<?> getTask(@PathVariable("taskId") String taskId){
        return Result.success(taskService.getTaskStatus(taskId));
    }
    @DeleteMapping("/tasks/{taskId}")
    public Result<?> delTask(@PathVariable("taskId") String taskId){
        taskService.clearQueueTaskById(new String[]{taskId});
        return Result.success();
    }

}
