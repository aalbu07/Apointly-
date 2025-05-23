package org.example.apointly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ApointlyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApointlyApplication.class, args); //test
    }
}

