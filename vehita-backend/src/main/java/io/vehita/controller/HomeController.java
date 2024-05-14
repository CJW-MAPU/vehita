package io.vehita.controller;

import io.vehita.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public void create(@RequestBody String request) {
    }

    @PostMapping("/sign-in")
    public void read(@RequestBody String request) {
    }
}
