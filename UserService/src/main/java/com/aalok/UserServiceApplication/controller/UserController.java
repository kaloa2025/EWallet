package com.aalok.UserServiceApplication.controller;

import com.aalok.UserServiceApplication.dto.UserRequestDTO;
import com.aalok.UserServiceApplication.model.Users;
import com.aalok.UserServiceApplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/addUpdate")
    private ResponseEntity<Users> addUpdate(@RequestBody @Valid UserRequestDTO dto)
    {
        Users user=userService.addUpdate(dto);
        if(user!=null)
        {
            ResponseEntity res=new ResponseEntity(user, HttpStatus.OK);
            return res;
        }
        return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
    }
}
