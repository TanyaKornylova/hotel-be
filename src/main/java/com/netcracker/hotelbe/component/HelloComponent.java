package com.netcracker.hotelbe.component;

import com.netcracker.hotelbe.model.Hello;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class HelloComponent {
    private static final String text = "Hello, world!";

    private final AtomicLong counter = new AtomicLong();

    public Hello getHello(){
        return new Hello(counter.incrementAndGet(), text);
    }
}
