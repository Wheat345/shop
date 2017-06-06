package com.linxi.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "domain")
//@EntityScan(basePackages = "domain")

public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
