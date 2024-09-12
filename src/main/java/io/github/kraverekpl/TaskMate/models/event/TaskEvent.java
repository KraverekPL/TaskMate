package io.github.kraverekpl.TaskMate.models.event;

import io.github.kraverekpl.TaskMate.models.Task;
import lombok.Getter;
import lombok.ToString;

import java.time.Clock;
import java.time.Instant;

@Getter
@ToString
public abstract class TaskEvent {
    public static  TaskEvent changed(Task source) {
        return source.isDone() ? new TaskDone(source) : new TaskUndone(source);
    }

    private int taskId;
    private Instant ocurrence;

    TaskEvent(int taskId, Clock clock) {
        this.taskId = taskId;
        this.ocurrence = Instant.now(clock);
    }

}
