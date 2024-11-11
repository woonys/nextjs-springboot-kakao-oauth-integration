package org.example.nextjsspringbootkakaooauthintegration.user.controller;

import org.example.nextjsspringbootkakaooauthintegration.user.dto.UserResponseDto;
import org.example.nextjsspringbootkakaooauthintegration.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public UserResponseDto getCurrentUser() {
        return userService.getCurrentUser();
    }
}
