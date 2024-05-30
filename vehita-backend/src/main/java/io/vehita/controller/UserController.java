package io.vehita.controller;

import io.vehita.domain.dto.UserRequestData;
import io.vehita.domain.dto.UserResponseData;
import io.vehita.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseData> register(@RequestBody UserRequestData request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseData> login(@RequestBody UserRequestData request) {
        return ResponseEntity.ok(userService.login(request));
    }
}
