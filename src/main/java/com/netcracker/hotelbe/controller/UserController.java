package com.netcracker.hotelbe.controller;


import com.netcracker.hotelbe.entity.User;
import com.netcracker.hotelbe.repository.UserRepository;
import com.netcracker.hotelbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> addUser(@RequestBody @Valid User user){
        System.out.println("aa");
        return new ResponseEntity<>(userService.save(user).getId(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateUser(@Valid @RequestBody User user){
        return new ResponseEntity<>(userService.save(user).getId(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        userService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);

    }



}
