package com.example.schoolforum.config;

import com.example.schoolforum.component.SearchAnalyticsInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final FileUploadProperties fileUploadProperties;
    private final SearchAnalyticsInterceptor searchAnalyticsInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/avatars/**")
                .addResourceLocations("file:" + fileUploadProperties.getAvatarPath());

        registry.addResourceHandler("/post-images/**")
                .addResourceLocations("file:" + fileUploadProperties.getPostImagePath());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(searchAnalyticsInterceptor)
                .addPathPatterns("/search", "/search/**");
    }
}
