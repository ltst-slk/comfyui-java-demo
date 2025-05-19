package com.giggle.comfy.connect.server.config;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.HashMap;
import java.util.Map;

public interface ComfyuiApi {

    /**
     * 取消当前正在执行的任务
     * @return
     */
    @POST("/interrupt")
    Call<HashMap<String,Object>> interruptCurrentTask();
    /**
     * 添加文生图任务
     * @return
     */
    @POST("/prompt")
    Call<HashMap<String,Object>> addPrompt(@Body Map<String, Object> requestBody);

    /**
     * 获取任务信息
     * @param promptId 任务id
     * @return 任务信息
     */
    @GET("/history/{prompt_id}")
    Call<HashMap<String,Object>> getPromptStatus(@Path("prompt_id") String promptId);
    /**
     * 获取 ComfyUI 当前队列
     * @return
     */
    @GET("/queue")
    Call<HashMap<String,Object>> getQueue();

    /**
     * 根据参数 删除 等待队列的任务
     */
    @POST("/queue")
    Call<Void> deleteTasks(@Body Map<String,Object> request);

    @GET("/system_stats")
    Call<HashMap<String, Object>> getSystemStats();
}
