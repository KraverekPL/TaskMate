package io.github.kraverekpl.TaskMate.reports;

import io.github.kraverekpl.TaskMate.models.event.TaskDone;
import io.github.kraverekpl.TaskMate.models.event.TaskEvent;
import io.github.kraverekpl.TaskMate.models.event.TaskUndone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ChangedTaskEventListener {
    private Logger logger = LoggerFactory.getLogger(ChangedTaskEventListener.class);
    private final PersistedTaskEventRepository repository;

    public ChangedTaskEventListener(PersistedTaskEventRepository repository) {
        this.repository = repository;
    }

    @Async
    @EventListener
    public void onTaskChanged(TaskDone event) {
        this.onChanged(event);
    }

    @Async
    @EventListener
    public void onTaskChanged(TaskUndone event) {
        this.onChanged(event);
    }

    private void onChanged(final TaskEvent event) {
        logger.info("Task changed: {}", event);
        repository.save(new PersistedTaskEvent(event));
    }
}
