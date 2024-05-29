package io.vehita.controller;

import io.vehita.domain.dto.AuthResponseData;
import io.vehita.domain.dto.SilentRefreshResponseData;
import io.vehita.domain.dto.UserHttpRequestData;
import io.vehita.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseData> login(@RequestBody UserHttpRequestData request, HttpServletResponse response) {
        return ResponseEntity.ok(authenticationService.login(request, response));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseData> read(@RequestBody UserHttpRequestData request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @GetMapping("/silent-refresh")
    public ResponseEntity<SilentRefreshResponseData> silentRefresh(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return ResponseEntity.ok(authenticationService.silentRefresh(request, response));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.status(HttpStatus.OK)
                .headers(authenticationService.clearAllCookies()).build();
    }
}
