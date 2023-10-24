package com.ca.jmccabepetition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;


@SpringBootApplication(scanBasePackages = "com.ca.jmccabepetition")
public class JmccabepetitionApplication {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(JmccabepetitionApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port","9090"));
        app.run(args);
    }



}
