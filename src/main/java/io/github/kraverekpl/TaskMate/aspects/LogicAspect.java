package io.github.kraverekpl.TaskMate.aspects;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.NoArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogicAspect {
    private final Timer timer;

    public LogicAspect(final MeterRegistry meterRegistry) {
        this.timer = meterRegistry.timer("logic.project.create.group");
    }

    @Around("execution(* io.github.kraverekpl.TaskMate.services.TaskGroupService.createGroup(..))")
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
