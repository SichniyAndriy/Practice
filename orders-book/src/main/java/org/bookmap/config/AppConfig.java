package org.bookmap.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "org.bookmap")
@PropertySource("classpath:application.properties")
public class AppConfig {

}
