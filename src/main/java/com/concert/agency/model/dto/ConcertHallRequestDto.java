package com.concert.agency.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ConcertHallRequestDto {
    @NotNull(message = "Capacity cant be NULL")
    @Min(value = 1, message = "Capacity must be greater than 0")
    private Integer capacity;
    @NotNull(message = "Description cant be NULL")
    private String description;

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
