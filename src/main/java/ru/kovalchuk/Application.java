package ru.kovalchuk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
//        в SpringApplication передаем класс который надо запускать
        SpringApplication.run(Application.class, args);
    }
}