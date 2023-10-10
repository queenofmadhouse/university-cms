package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Day;
import com.foxminded.universitycms.entity.Schedule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CalendarService {

    @Value("${app.constants.calendar.amount-of-days}")
    private int amountOfDays;

    @Value("${app.constants.calendar.week-size}")
    private int weekSize;

    private static final int ONE_DAY = 1;

    public List<List<Day>> prepareCalendar(Map<LocalDate, List<Schedule>> scheduleMap) {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(amountOfDays);

        List<List<Day>> weeks = new ArrayList<>();
        List<Day> week = new ArrayList<>();

        for (LocalDate date = startDate; !date.isEqual(endDate); date = date.plusDays(ONE_DAY)) {
            List<Schedule> lessons = scheduleMap.getOrDefault(date, new ArrayList<>());
            week.add(new Day(date, lessons));

            if (week.size() == weekSize) {
                weeks.add(week);
                week = new ArrayList<>();
            }
        }

        if (!week.isEmpty()) {
            weeks.add(week);
        }

        return weeks;
    }

    public List<LocalDate> prepareDates(int amountOfDays) {
        LocalDate today = LocalDate.now();

        return IntStream.range(0, amountOfDays)
                .mapToObj(today::plusDays)
                .collect(Collectors.toList());
    }
}
