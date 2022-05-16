package org.zerock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.io.IOException;

/*
    ServletConfig.java로 서블릿 설정.
    1. @EnableWebMvc 어노테이션을 사용하고 WebMvcConfigurer 인터페이스 구현하는 방식

    2. @Configuration 어노테이션을 사용하고 WebMvcConfigurationSupport클래스 상속하는 방식
        - 일반 @Configuration 우선 순위가 구분되지 않는 경우에 사용용
*/
@EnableWebMvc
@ComponentScan(basePackages = {"org.zerock.controller", "org.zerock.exception"})
public class ServletConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");
        registry.viewResolver(bean);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean(name="multipartResolver")
    public CommonsMultipartResolver getResolver() throws IOException {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();

        //10MB
        resolver.setMaxUploadSize(1024 * 1024 * 10);

        //2MB
        resolver.setMaxUploadSizePerFile(1024 * 1024 * 2);

        //1MB
        resolver.setMaxInMemorySize(1024 * 1024);

        //temp upload
        resolver.setUploadTempDir(new FileSystemResource("C:\\SpringStudy\\jex01\\src\\main\\webapp\\upload"));
        resolver.setDefaultEncoding("UTF-8");

        return resolver;
    }
}
