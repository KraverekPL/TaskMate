package io.github.kraverekpl.TaskMate.configuration;

import io.github.kraverekpl.TaskMate.interceptors.SqlInjectionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new SqlInjectionInterceptor());
    }
}
