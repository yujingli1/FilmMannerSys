package com.stesla.love.movie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello, Movie!";
    }

}
