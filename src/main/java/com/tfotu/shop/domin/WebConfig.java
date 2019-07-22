package com.tfotu.shop.domin;

import com.tfotu.shop.domin.UserLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //  解决response返回过多Accept-Charset
        StringHttpMessageConverter shmc = new StringHttpMessageConverter(Charset.forName("utf-8"));
        shmc.setWriteAcceptCharset(false);
        converters.add(shmc);
        addDefaultHttpMessageConverters(converters);
    }

    @Override
    protected void configurePathMatch(PathMatchConfigurer configurer) {
        //  springboot区分大小写
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive( false );
        configurer.setPathMatcher(matcher);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //  配置资源文件夹
        registry.addResourceHandler("/**")
                    .addResourceLocations("classpath:/static");
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginInterceptor()).addPathPatterns("/user").addPathPatterns("/user/*");
    }
}
