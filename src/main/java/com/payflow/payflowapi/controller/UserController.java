package com.payflow.payflowapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payflow.payflowapi.entity.User;
import com.payflow.payflowapi.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        System.out.println("With @RequestBody   -> " + describe(user));
        return userService.registerUser(user);
    }

    // Demonstration-only endpoint: same signature but WITHOUT @RequestBody.
    // Spring has no instruction to deserialize the HTTP body into the User
    // parameter, so it falls back to binding from request parameters/path
    // variables only - every field on the incoming JSON body is ignored and
    // the User object comes out all-null.
    @PostMapping("/no-body-demo")
    public User createUserNoRequestBody(User user) {
        System.out.println("Without @RequestBody -> " + describe(user));
        return user;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/upi/{upiId}")
    public ResponseEntity<User> getUserByUpiId(@PathVariable String upiId) {
        return userService.findByUpiId(upiId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<User> getUsersWithBalanceAbove(@RequestParam Double minBalance) {
        return userService.findUsersWithBalanceAbove(minBalance);
    }

    private String describe(User user) {
        return "User{userId=" + user.getUserId() + ", name=" + user.getName()
                + ", upiId=" + user.getUpiId() + ", balance=" + user.getBalance()
                + ", phoneNumber=" + user.getPhoneNumber() + "}";
    }
}
