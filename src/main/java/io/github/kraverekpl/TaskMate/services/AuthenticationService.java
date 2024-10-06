package io.github.kraverekpl.TaskMate.services;

import io.github.kraverekpl.TaskMate.models.DTO.LoginUserDto;
import io.github.kraverekpl.TaskMate.models.DTO.RegisterUserDto;
import io.github.kraverekpl.TaskMate.models.User;
import io.github.kraverekpl.TaskMate.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public User singUp(RegisterUserDto user) {
        User newUser = new User();
        newUser.setFullName(user.getFullName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }

    public User login(LoginUserDto user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
        return userRepository.findByEmail(user.getEmail()).orElseThrow();
    }

}
