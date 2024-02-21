package com.distribuida.appauthors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class AppAuthorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppAuthorsApplication.class, args);
    }

}
