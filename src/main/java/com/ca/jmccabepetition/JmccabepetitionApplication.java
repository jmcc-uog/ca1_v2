package com.ca.jmccabepetition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication(scanBasePackages = "com.ca.jmccabepetition")
public class JmccabepetitionApplication {

    public static void main(String[] args) {
        SpringApplication.run(JmccabepetitionApplication.class, args);
        //SpringApplication app = new SpringApplication(JmccabepetitionApplication.class);
        //app.setDefaultProperties(Collections.singletonMap("server.port","8080"));
        //app.run(args);
    }


    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JmccabepetitionApplication.class);
    }





}
