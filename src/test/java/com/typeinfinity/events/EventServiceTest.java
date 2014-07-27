package com.typeinfinity.events;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.project13.test.lambda.spec.set.ClassSpecSet;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.function.Predicate;

import static org.fest.assertions.Assertions.assertThat;
import static pl.project13.test.lambda.SpecDSL.describe;

public class EventServiceTest {
    private EventService eventService;

    @Before
    public void setUp() {
        eventService = new EventService();
    }

    @After
    public void tearDown() {
        eventService.unregisterAll();
    }

    private static void registerEvents(EventService service) {
        service.register(
            TrainingEvent.called("Java 8 Enlightenment")
                .at("Johannesburg")
                .scheduledFor(LocalDate.of(2014, Month.SEPTEMBER, 25))
        );

        service.register(
            TrainingEvent.called("Reactive Programming in Java")
                .at("Cape Town")
                .scheduledFor(LocalDate.of(2014, Month.DECEMBER, 1))
        );

        service.register(
            TrainingEvent.called("Java 8 Advanced Concepts")
                .at("Johannesburg")
                .scheduledFor(LocalDate.of(2014, Month.OCTOBER, 15))
        );
    }

    @Test
    public void testFindEventsIn_TheOldWay() {
        EventServiceTest.registerEvents(eventService);
        List<TrainingEvent> events = eventService.findEventsIn("Johannesburg");

        for(TrainingEvent event : events) {
            assertThat(event.getLocation()).isEqualTo("Johannesburg");
        }
    }

    @Test
    public void testFindEventsIn_Java8() {
        EventServiceTest.registerEvents(eventService);
        List<TrainingEvent> events = eventService.findEventsIn("Johannesburg");

        Predicate<TrainingEvent> inJohannesburg =
            event -> event.getLocation().equals("Johannesburg");

        assertThat(events.stream().allMatch(inJohannesburg)).isTrue();
    }


    // ----- Specification Based Testing ----- //

    ClassSpecSet<EventService> it = describe(EventService.class);

    {
        // Take 1
        it.should("find the events for the specific location", service -> {
                registerEvents(service);

                List<TrainingEvent> events = service.findEventsIn("Johannesburg");

                for(TrainingEvent event : events) {
                    assertThat(event.getLocation()).isEqualTo("Johannesburg");
                }
            }
        );

        // Take 2
        it.should("find the events for the specific location", service -> {
                registerEvents(service);

                List<TrainingEvent> events = service.findEventsIn("Johannesburg");

                Predicate<TrainingEvent> inJohannesburg =
                    event -> event.getLocation().equals("Johannesburg");

                assertThat(events.stream().allMatch(inJohannesburg)).isTrue();
            }
        );

        it.should("return an empty event list if no matching location is found", service -> {
                registerEvents(service);
                assertThat(service.findEventsIn("Durban").isEmpty()).isTrue();
            }
        );
    }

    @Test
    public void shouldPassAllTests() throws Exception {
        it.shouldPassAllTests();
    }
}
