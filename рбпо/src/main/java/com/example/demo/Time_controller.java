package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class Time_controller {

    @GetMapping("/time")
    public String getCurrentTime() {
        // Получаем текущее локальное время
        LocalDateTime now = LocalDateTime.now();

        // Красиво его форматируем
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return "Текущее время: " + now.format(formatter);
    }
}
