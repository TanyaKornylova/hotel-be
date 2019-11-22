package com.netcracker.hotelbe.controller;


import com.netcracker.hotelbe.entity.Staff;
import com.netcracker.hotelbe.service.StaffService;
import com.netcracker.hotelbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("staff")
public class StaffController {

    @Autowired
    private StaffService staffService;
    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<Staff>> getAllStaff(){
        return new ResponseEntity<>(staffService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable Long id){
        return new ResponseEntity<>(staffService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> addStaff(@RequestBody @Valid Staff staff){
        return new ResponseEntity<>(staffService.save(staff).getId(), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Long> updateStaff(@RequestBody @Valid Staff staff, @PathVariable("id") Long id){
        staff.setId(id);
        staff.setUser(userService.findById(id));
        return new ResponseEntity<>(staffService.save(staff).getId(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStaff(@PathVariable Long id){
        staffService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }



}
