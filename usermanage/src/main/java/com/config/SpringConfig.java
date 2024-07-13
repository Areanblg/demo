package com.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.service")
@Import({DataSourceConfig.class, MybatisConfig.class})
@PropertySource("classpath:jdbc.properties")
public class SpringConfig {

}
