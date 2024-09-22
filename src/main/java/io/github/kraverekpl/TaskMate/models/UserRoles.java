package io.github.kraverekpl.TaskMate.models;

public enum UserRoles {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    UserRoles(final String role) {
    }

    public String getRole() {
        return name();
    }
}
