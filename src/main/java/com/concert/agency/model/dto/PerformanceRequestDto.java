package com.concert.agency.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PerformanceRequestDto {
    @NotNull(message = "Title cant be NULL")
    @Size(min = 1, message = "Title must be longer than 0")
    private String title;
    @NotNull(message = "Description cant be NULL")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
