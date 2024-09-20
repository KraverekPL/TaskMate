package io.github.kraverekpl.TaskMate.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SsoController {

    @GetMapping("/logout")
    public void logout(HttpServletRequest servletRequest) throws ServletException {
        servletRequest.logout();
    }
}
