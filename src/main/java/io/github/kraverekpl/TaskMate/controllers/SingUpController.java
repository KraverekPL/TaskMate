package io.github.kraverekpl.TaskMate.controllers;

import io.github.kraverekpl.TaskMate.models.DTO.RegisterUserDto;
import io.github.kraverekpl.TaskMate.models.User;
import io.github.kraverekpl.TaskMate.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class SingUpController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> singUp(@RequestBody RegisterUserDto userToRegister) {
        var user = authenticationService.singUp(userToRegister);
        return ResponseEntity.ok(user);
    }
}
