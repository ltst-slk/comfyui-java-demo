package com.giggle.comfy.connect.server.service;

import com.giggle.comfy.connect.server.config.ComfyuiApi;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class TaskService {
    @Resource
    ComfyuiApi comfyuiApi;

    /**
     * 根据任务id 取消任务
     * @param delete 任务id 数组
     */
    public void clearQueueTaskById(String[] delete){
        comfyuiApi.deleteTasks(Map.of("delete", delete));
    }
    /**
     * 清除队列所有任务
     */
    public void clearAllQueueTasks(){
        comfyuiApi.deleteTasks(Map.of("clear", true));
    }

    /**
     * 添加任务
     * @return
     */
    public HashMap<String, Object> addTask(Map<String,Object> param){
        try{
            return comfyuiApi.addPrompt(param).execute().body();
        } catch (Exception e){
            log.error("TaskService addTask request error request:{}",param,e);
        }
        return null;
    }

    /**
     * 获取任务状态
     * @param taskId
     * @return
     */
    public HashMap<String,Object> getTaskStatus(String taskId){
        try{
            return comfyuiApi.getPromptStatus(taskId).execute().body();
        }catch (Exception e){
            log.error("TaskService getTaskStatus request error ",e);
        }
        return null;
    }

    /**
     * 获取当前任务队列
     * @return {queue_running:[],queue_pending:[]}
     */
    public HashMap<String,Object> getComfyUIQueue(){
        try{
            return comfyuiApi.getQueue().execute().body();
        } catch (IOException e) {
            log.error("TaskService get queue request error",e);
        }
        return null;
    }

    /**
     * 取消当前正在执行的任务
     */
    public void cancelCurrentTask(){
        try{
            comfyuiApi.interruptCurrentTask().execute();
        } catch (IOException e) {
            log.error("TaskService cancelCurrentTask request error",e);
        }
    }


}
