package io.github.kraverekpl.TaskMate.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.jwt")
@Getter
@Setter
public class SecurityConfigurationProperties {
    String secretKey;
    Long tokenExpirationTime;
}
