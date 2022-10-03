package com.uncal.dt.remote.sh.cfg;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@ComponentScan(basePackages = {"com.uncal.dt.remote.sh.cfg"})
public class MVCconfig implements WebMvcConfigurer{

}
