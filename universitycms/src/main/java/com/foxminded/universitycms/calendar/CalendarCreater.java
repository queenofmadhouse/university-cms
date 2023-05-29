package com.foxminded.universitycms.calendar;

import com.foxminded.universitycms.entity.Schedule;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class CalendarCreater {

    private final int amountOfDays;
    private final int weekSize;

    public List<List<Day>> prepareCalendar(Map<LocalDate, List<Schedule>> scheduleMap) {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(amountOfDays);

        List<List<Day>> weeks = new ArrayList<>();
        List<Day> week = new ArrayList<>();

        for (LocalDate date = startDate; !date.isEqual(endDate); date = date.plusDays(1)) {
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
}
