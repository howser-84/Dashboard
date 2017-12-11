package org.dashboard.main.data;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class TaskDTO {

    private String username;
    private String description;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
