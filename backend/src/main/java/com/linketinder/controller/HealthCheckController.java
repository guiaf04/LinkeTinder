package com.linketinder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HealthCheckController {
  @GetMapping
  public String healthCheck() {
    return "Server running!";
  }

}
