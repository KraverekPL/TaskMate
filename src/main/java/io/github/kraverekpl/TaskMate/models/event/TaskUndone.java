package io.github.kraverekpl.TaskMate.models.event;

import io.github.kraverekpl.TaskMate.models.Task;

import java.time.Clock;

public class TaskUndone extends TaskEvent {
    public TaskUndone(final Task source) {
        super(source.getId(), Clock.systemDefaultZone());
    }
}
