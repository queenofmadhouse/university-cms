package com.foxminded.universitycms.service;

import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Teacher;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ScheduleService {

    Map<LocalDate, List<Schedule>> getScheduleByTeacher(Teacher teacher, int days);

    Map<LocalDate, List<Schedule>> getScheduleByGroup(Group group, int days);

    Schedule getScheduleById(long id);
}
