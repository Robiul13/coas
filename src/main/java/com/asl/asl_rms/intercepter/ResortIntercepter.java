package com.asl.asl_rms.intercepter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ResortIntercepter implements WebMvcConfigurer {

    private static final Path APP_PATH = Paths.get(System.getProperty("user.dir"));
    private static final String UPLOADS_DIR = "uploads";

    @Autowired
    SampleInterceptor sampleInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sampleInterceptor);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = APP_PATH.resolve(UPLOADS_DIR).toUri()
            .toString();
        registry.addResourceHandler("/uploads/**").addResourceLocations(path);
    }
    
}