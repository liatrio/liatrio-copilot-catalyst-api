package com.liatrio.dojo.devopsknowledgeshareapi.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebAutoConfiguration {

    @Bean
    @Profile("local")
    WebSecurityConfigurerAdapter localWebSecurityConfigurerAdapter() {

        return new WebSecurityConfigurerAdapter() {
            @Override
            protected void configure(HttpSecurity http) throws Exception {
                http.csrf().disable().authorizeRequests().anyRequest().permitAll();
            }
        };
    }


    @Bean
    @Profile("!local")
    public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
        return new WebSecurityConfigurerAdapter() {
            @Override
            protected void configure(HttpSecurity http) throws Exception {
                http.csrf().disable().authorizeRequests().anyRequest().permitAll();
            }

        };
    }
}
