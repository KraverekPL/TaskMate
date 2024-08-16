package io.github.kraverekpl.TaskMate;

import jakarta.validation.Validator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class TaskMateApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskMateApplication.class, args);
    }

    @Bean
    protected Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}
