package com.noq.api.model.response;

public class NextAvailable {
    int day;
    int hour;

    public NextAvailable(int day, int hour) {
        this.day = day;
        this.hour = hour;
    }

    public NextAvailable() {
        this.day = -1;
        this.hour = -1;
    }
}
