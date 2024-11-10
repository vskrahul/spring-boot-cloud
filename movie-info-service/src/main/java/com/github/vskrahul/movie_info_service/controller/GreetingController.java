package com.github.vskrahul.movie_info_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Slf4j
@RefreshScope
public class GreetingController {

    String environment;
    List<String> genre;
    String endpoint;

    public GreetingController(
            @Value("${environment}") String environment,
            @Value("${movies.genres}")List<String> genre,
            @Value("${endpoint}")String endpoint) {
        this.environment = environment;
        this.genre = genre;
        this.endpoint = endpoint;
        log.info("[environment={}] [genre={}] [endpoint={}]", environment, genre, endpoint);
    }

    @GetMapping("endpoint")
    public String endpoint() {
        return this.endpoint;
    }

    @GetMapping("environment")
    public String environment() {
        return this.environment;
    }

    @GetMapping("genre")
    public List<String> genre() {
        return this.genre;
    }
}
