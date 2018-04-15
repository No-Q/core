package com.noq.api.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;

public class DayAvailabilityRequest {

    @NotNull
    private DayOfWeek dayOfWeek;
    @NotNull()
    private Integer openHour;
    @NotNull()
    private Integer closeHour;
    @NotNull()
    private Integer breakStartHour;
    @NotNull()
    private Integer breakEndHour;

    @JsonCreator
    public DayAvailabilityRequest(
            @JsonProperty("dayOfWeek") DayOfWeek dayOfWeek,
            @JsonProperty("openHour") Integer openHour,
            @JsonProperty("closeHour") Integer closeHour,
            @JsonProperty("breakStartHour") Integer breakStartHour,
            @JsonProperty("breakEndHour") Integer breakEndHour){

        this.dayOfWeek = dayOfWeek;
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.breakStartHour = breakStartHour;
        this.breakEndHour = breakEndHour;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public Integer getOpenHour() {
        return openHour;
    }

    public Integer getCloseHour() {
        return closeHour;
    }

    public Integer getBreakStartHour() {
        return breakStartHour;
    }

    public Integer getBreakEndHour() {
        return breakEndHour;
    }

    @Override
    public String toString() {
        return "DayAvailabilityRequest{" +
                "openHour=" + openHour +
                ", closeHour=" + closeHour +
                ", breakStartHour=" + breakStartHour +
                ", breakEndHour=" + breakEndHour +
                '}';
    }
}
