package com.tfotu.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.tfotu.shop.dao")   //  配置mapper扫描包
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    public ShopApplication(){
        System.out.println("shop 启动！");
    }

    @Bean
    public void getDemoBean(){
        return;
    }
}
