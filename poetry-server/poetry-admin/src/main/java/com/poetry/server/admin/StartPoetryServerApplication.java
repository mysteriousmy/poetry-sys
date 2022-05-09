package com.poetry.server.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan({"com.poetry.common","com.poetry.server.admin","com.poetry.server.miniprogram","com.poetry.db"})
@SpringBootApplication
@EnableJpaRepositories("com.poetry.db.repository")
@EntityScan("com.poetry.db.entity")
public class StartPoetryServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartPoetryServerApplication.class, args);
    }
}
