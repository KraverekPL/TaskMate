package io.github.kraverekpl.TaskMate.models.event;

import io.github.kraverekpl.TaskMate.models.Task;

import java.time.Clock;

public class TaskDone extends TaskEvent {
    public TaskDone(final Task source) {
        super(source.getId(), Clock.systemDefaultZone());
    }
}
