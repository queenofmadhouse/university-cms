package com.foxminded.universitycms.configuration;

import com.foxminded.universitycms.calendar.CalendarCreater;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    private static final int CALENDAR_AMOUNT_OF_DAYS = 30;
    private static final int CALENDAR_WEEK_SIZE = 6;

    @Bean
    public int amountOfTeachers() {
        return 40;
    }

    @Bean
    public int amountOfStudents() {
        return 400;
    }

    @Bean
    public int amountOfGroups() {
        return 30;
    }

    @Bean
    public int amountOfCourses() {
        return 20;
    }

    @Bean
    public CalendarCreater calendarCreater() {
        return new CalendarCreater(CALENDAR_AMOUNT_OF_DAYS, CALENDAR_WEEK_SIZE);
    }
}
