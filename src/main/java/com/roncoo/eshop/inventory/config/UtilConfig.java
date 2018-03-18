package com.roncoo.eshop.inventory.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 外部引入的工具bean
 *
 * @auther sgyz
 * @create 2018/3/17 0017
 */
@Configuration
public class UtilConfig {

    @Bean
    public Gson  gson(){
        return new Gson();
    }
}
