package io.github.kraverekpl.TaskMate.aspects;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogicAspect {
    private final Timer timer;
    Logger logger = org.slf4j.LoggerFactory.getLogger(LogicAspect.class);

    public LogicAspect(final MeterRegistry meterRegistry) {
        this.timer = meterRegistry.timer("logic.project.create.group");
    }

    @Pointcut("execution(* io.github.kraverekpl.TaskMate.services.TaskGroupService.createGroup(..))")
    static void projectServiceCreatedGroup() { }

    @Before("projectServiceCreatedGroup()")
    void logMethodCallParameters(JoinPoint joinPoint) {
        logger.info("Before {} with {}", joinPoint.getSignature().getName(), Arrays.stream(joinPoint.getArgs()).toList());
    }

    @Around("projectServiceCreatedGroup()")
    Object aroundProjectsCreateGroup(ProceedingJoinPoint proceedingJoinPoint) {
        return timer.record(() -> {
            try {
                return proceedingJoinPoint.proceed();
            } catch (Throwable e) {
                if (e instanceof RuntimeException) {
                    throw (RuntimeException) e;
                } else {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
