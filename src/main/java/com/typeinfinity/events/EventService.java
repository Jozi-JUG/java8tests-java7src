package com.typeinfinity.events;

import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

public class EventService {
    private List<TrainingEvent> events;

    public EventService() {
        events = new ArrayList<>();
    }

    public void register(TrainingEvent event) {
        events.add(event);
    }

    public void unregisterAll() {
        events.clear();
    }

    public List<TrainingEvent> findEventsIn(String location) {
        List<TrainingEvent> filteredEvents = new ArrayList<>();
        for (TrainingEvent event : events) {
            if (event.getLocation().equals(location)) {
                filteredEvents.add(event);
            }
        }
        return filteredEvents;
    }

    /* -- Java 8
    public List<TrainingEvent> findEventsIn2(String location) {
        return com.typeinfinity.events.stream().filter(
            event -> event.getLocation().equals(location)
        ).collect(Collectors.toList());
    }
    */
}
