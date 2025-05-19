package com.giggle.comfy.connect.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class ComfyuiConfig {

    @Bean
    public ComfyuiApi comfyuiApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.17:8188/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(ComfyuiApi.class);
    }
}
