package com.foxminded.universitycms.entity;

import com.foxminded.universitycms.entity.Schedule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class Day {

    private final LocalDate date;
    private final List<Schedule> lessons;
}
