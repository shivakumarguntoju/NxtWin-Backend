package com.NxtWinBackend.NxtWinBackend.controller;


import com.NxtWinBackend.NxtWinBackend.entity.LoginResponse;
import com.NxtWinBackend.NxtWinBackend.entity.User;
import com.NxtWinBackend.NxtWinBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody User user) {
        LoginResponse response = userService.loginUser(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> logoutUser(
            @RequestParam Long userId,
            @RequestParam String token) {
        boolean result = userService.logoutUser(userId, token);
        if (result) {
            return ResponseEntity.ok("Logged out successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user or token");
        }
    }

}
