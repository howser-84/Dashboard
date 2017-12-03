package org.dashboard.main.data;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

public class Task implements Serializable{

    private static final long serialVersionUID = -3875258726204801153L;

    private Long id;
    private String name;
    private String description;

    @JsonSerialize
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonSerialize
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonSerialize
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
