package com.typeinfinity.events;

import java.time.LocalDate;

public class TrainingEvent {
    private String title;
    private String location;
    private LocalDate dateTime;

    public static TrainingEvent called(String title) {
        TrainingEvent event = new TrainingEvent();
        event.setTitle(title);
        return event;
    }

    public TrainingEvent at(String location) {
        this.setLocation(location);
        return this;
    }

    public TrainingEvent scheduledFor(LocalDate dateTime) {
        this.setDateTime(dateTime);
        return this;
    }

    // Getter / Setter crap

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }
}
