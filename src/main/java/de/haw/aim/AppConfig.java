package de.haw.aim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@Configuration
@ComponentScan(basePackages = { "de.haw.aim.*" })
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Autowired
    private Environment env;

}
