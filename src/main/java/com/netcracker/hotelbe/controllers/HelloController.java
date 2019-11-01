package com.netcracker.hotelbe.controllers;

import com.netcracker.hotelbe.resources.Hello;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloController {
    private static final String text = "Hello, world!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/hello")
    public Hello hello() {
        return new Hello(counter.incrementAndGet(), text);
    }
}
