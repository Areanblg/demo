package com.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.service")
@PropertySource("classpath:jdbc.properties")
@Import({MybatisConfig.class, MyDatasource.class})
public class SpringConfig {
}
