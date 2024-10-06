package io.github.kraverekpl.TaskMate.controllers;

import io.github.kraverekpl.TaskMate.controllers.advice.GlobalExceptionHandlerProcessing;
import io.github.kraverekpl.TaskMate.models.DTO.LoginUserDto;
import io.github.kraverekpl.TaskMate.models.LoginResponse;
import io.github.kraverekpl.TaskMate.models.User;
import io.github.kraverekpl.TaskMate.security.JwtService;
import io.github.kraverekpl.TaskMate.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
@GlobalExceptionHandlerProcessing
public class LoginController {
    private final JwtService jwtService;
    private final AuthenticationService authentication;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authentication.login(loginUserDto);
        String token = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
