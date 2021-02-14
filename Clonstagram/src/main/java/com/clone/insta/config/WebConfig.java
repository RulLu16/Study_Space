package com.clone.insta.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // @enableWebMvc가 설정해주는 것 이상으로 개발자가 설정을 세팅하고 싶을때 사용하여 세팅값을 추가할 수 있게 함.

    @Value("${file.path}")
    private String uploadImagePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///" + uploadImagePath)
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
