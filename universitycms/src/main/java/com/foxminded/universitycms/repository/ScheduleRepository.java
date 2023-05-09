package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
