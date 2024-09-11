package io.github.kraverekpl.TaskMate.models.DTO;

import io.github.kraverekpl.TaskMate.models.Task;
import io.github.kraverekpl.TaskMate.models.TaskGroup;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GroupReadModelTest {

    @Test
    void constructor_noDeadlines_createsNullDeadline() {
        var source = new TaskGroup();
        source.setDescription("foo");
        source.setTasks(Set.of(new Task("bar", null), new Task("baz", null)));

        var result = new GroupReadModel(source);

        assertEquals("foo", result.getDescription());
        assertEquals(2, result.getTasks().size());
        assertNull(result.getDeadlineLatestTask());
    }
}