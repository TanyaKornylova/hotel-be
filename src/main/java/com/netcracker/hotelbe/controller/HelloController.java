package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.component.HelloComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloComponent helloComponent;

    @GetMapping("/hello")
    @ResponseBody
    public ResponseEntity getHello(){
        return new ResponseEntity(helloComponent.getHello(), HttpStatus.OK);
    }
}
