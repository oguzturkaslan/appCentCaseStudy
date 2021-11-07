package com.appcentcasestudy.api.controller;

import com.appcentcasestudy.business.abstracts.UserService;
import com.appcentcasestudy.core.dtos.UserUpdateDto;
import com.appcentcasestudy.core.utilities.results.Result;
import com.appcentcasestudy.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.login(user));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@Valid @PathVariable("id") Long id, @RequestBody UserUpdateDto userUpdateDto) {
        final Result result = userService.updateUser(id, userUpdateDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@Valid @PathVariable("id") Long id){
        final Result result = userService.deleteUser(id);
        return ResponseEntity.ok(result);
    }
}
