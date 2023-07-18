package com.aforv.userservice.controller;

import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aforv.userservice.dto.ResponseDto;
import com.aforv.userservice.entity.User;
import com.aforv.userservice.service.UserService;

@RestController
@RequestMapping("api/users")
@Slf4j
public class UserController {
	// Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
   

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDto> getUser(@PathVariable("id") Long userId){
    	log.info("***********I am in getUser UserController***************");
        ResponseDto responseDto = userService.getUser(userId);
        return ResponseEntity.ok(responseDto);
    }
}