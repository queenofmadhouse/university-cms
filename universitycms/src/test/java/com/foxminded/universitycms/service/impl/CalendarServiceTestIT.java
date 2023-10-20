package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Day;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.testconfiguration.IntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IntegrationTest
class CalendarServiceTestIT {

    @Autowired
    CalendarService calendarService;

    @MockBean
    private Schedule mockSchedule;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(calendarService, "amountOfDays", 7);
        ReflectionTestUtils.setField(calendarService, "weekSize", 7);
    }

    @Test
    void prepareCalendarShouldReturnEmptyLessonsWhenNoSchedulesProvided() {
        Map<LocalDate, List<Schedule>> scheduleMap = new HashMap<>();
        List<List<Day>> weeks = calendarService.prepareCalendar(scheduleMap);

        assertEquals(1, weeks.size());
        weeks.get(0).forEach(day -> assertTrue(day.getLessons().isEmpty()));
    }

    @Test
    void prepareCalendarShouldReturnScheduledLessonsWhenSchedulesProvided() {
        Map<LocalDate, List<Schedule>> scheduleMap = new HashMap<>();
        for (int i = 0; i < 7; i++) {
            scheduleMap.put(LocalDate.now().plusDays(i), Collections.singletonList(mockSchedule));
        }

        List<List<Day>> weeks = calendarService.prepareCalendar(scheduleMap);

        assertEquals(1, weeks.size());
        weeks.get(0).forEach(day -> assertEquals(1, day.getLessons().size()));
    }

    @Test
    void prepareDatesShouldReturnConsecutiveDatesStartingToday() {
        List<LocalDate> dates = calendarService.prepareDates(7);

        assertEquals(7, dates.size());
        for (int i = 0; i < 7; i++) {
            assertEquals(LocalDate.now().plusDays(i), dates.get(i));
        }
    }

    @Test
    void prepareCalendarShouldHandleIncompleteWeeksCorrectly() {
        ReflectionTestUtils.setField(calendarService, "amountOfDays", 10);
        ReflectionTestUtils.setField(calendarService, "weekSize", 7);

        Map<LocalDate, List<Schedule>> scheduleMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            scheduleMap.put(LocalDate.now().plusDays(i), Collections.singletonList(mockSchedule));
        }

        List<List<Day>> weeks = calendarService.prepareCalendar(scheduleMap);

        assertEquals(2, weeks.size());
        assertEquals(7, weeks.get(0).size());
        assertEquals(3, weeks.get(1).size());
    }
}
