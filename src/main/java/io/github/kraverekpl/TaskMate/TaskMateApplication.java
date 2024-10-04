package io.github.kraverekpl.TaskMate;

import jakarta.validation.Validator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
@EnableAsync
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TaskMateApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskMateApplication.class, args);
    }

    @Bean
    protected Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}
