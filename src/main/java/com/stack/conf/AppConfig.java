package com.stack.conf;

import com.stack.controller.AppErrorController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Autowired
    private ErrorAttributes errorAttributes;

    @Bean
    public AppErrorController appErrorController(){return new AppErrorController(errorAttributes);}
}
