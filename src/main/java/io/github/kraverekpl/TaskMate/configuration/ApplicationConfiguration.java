package io.github.kraverekpl.TaskMate.configuration;

import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ApplicationConfiguration {

    public ExecutorService taskExecutor() {
        return Executors.newFixedThreadPool(5);
    }
}
