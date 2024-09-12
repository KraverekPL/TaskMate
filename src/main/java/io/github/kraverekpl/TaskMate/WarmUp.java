package io.github.kraverekpl.TaskMate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class WarmUp implements ApplicationListener<ContextRefreshedEvent> {

    Logger logger = LoggerFactory.getLogger(WarmUp.class);
    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        logger.info("Warming up...");
    }
}
