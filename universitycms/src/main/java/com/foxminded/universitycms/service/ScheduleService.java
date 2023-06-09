package com.foxminded.universitycms.service;

import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.entity.dto.ScheduleDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface ScheduleService {

    Map<LocalDate, List<Schedule>> findScheduleByTeacher(Long teacherId, int days);

    Map<LocalDate, List<Schedule>> findScheduleByStudent(Long studentId, int days);

    Schedule findById(long id);

    void deleteById(long id);

    List<LocalTime> findFreeTimeForTeacherAndGroup(LocalDate date, Teacher teacher, Group group);

    void save(ScheduleDTO scheduleDTO);

    void saveAll(List<Schedule> schedules);
}
