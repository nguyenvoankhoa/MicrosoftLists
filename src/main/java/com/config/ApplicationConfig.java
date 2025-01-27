package com.config;

import com.service.JsonService;
import com.service.MicrosoftListService;
import com.service.SmartListService;
import com.mapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
    @Bean
    public MicrosoftListService microsoftListService() {
        return new MicrosoftListService();
    }
    @Bean
    public SmartListService smartListService() {
        return new SmartListService();
    }
    @Bean
    public JsonService jsonService() {
        return new JsonService();
    }
}
