package com.example.java_spbstu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class JavaSpbstuApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSpbstuApplication.class, args);
    }

}
