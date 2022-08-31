package com.example.internet_shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String pathToFolder = System.getProperty("user.home") + File.separator + "images" + File.separator;
        registry.addResourceHandler("/images/**").
                addResourceLocations("file:///" + pathToFolder);

        String pathToFolder1 = System.getProperty("user.home") + File.separator + "imag" + File.separator;
        registry.addResourceHandler("/imag/**").
                addResourceLocations("file:///" + pathToFolder1);

        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/register").setViewName("registration");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/create").setViewName("createProduct");
        registry.addViewController("/styles");
    }
}
