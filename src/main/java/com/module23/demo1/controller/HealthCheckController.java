package com.module23.demo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HealthCheckController {
    @GetMapping(path = "/")
    public String healthCheck()
    {
        return "OK";
    }
}
