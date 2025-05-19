package com.giggle.comfy;

import com.giggle.comfy.connect.server.config.ComfyuiApi;
import com.giggle.comfy.connect.server.config.ComfyuiConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import retrofit2.Call;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@ContextConfiguration(classes = ComfyuiConfig.class)
@Slf4j
class ApiTest {
    @Autowired
    ComfyuiApi comfyuiApi;

    @Test
    void getQueue() throws IOException {
        Call<HashMap<String, Object>> http = comfyuiApi.getQueue();
        log.info("ApiTest getQueue() {}",http.execute().body());
    }

    @Test
    void getPromptStatus() throws IOException {
        Call<HashMap<String, Object>> http = comfyuiApi.getPromptStatus("9888f388-20c1-458d-9010-c66455e79f16");
        log.info("ApiTest getPromptStatus() {}",http.execute().body());
    }

    @Test
    void deleteTask() throws IOException {
        Call<Void> http =  comfyuiApi.deleteTasks(Map.of("delete", new String[]{"d1ed3504-d784-46a4-94ec-49dbf45207ad"}));
        log.info("ApiTest getPromptStatus() {}",http.execute().body());
    }

    @Test
    void getSystemStatus() throws IOException {
        Call<HashMap<String, Object>> http = comfyuiApi.getSystemStats();
        log.info("ApiTest getSystemStats() {}",http.execute().body());
    }

}
