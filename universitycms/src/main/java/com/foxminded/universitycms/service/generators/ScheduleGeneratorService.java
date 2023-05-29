package com.foxminded.universitycms.service.generators;

import com.foxminded.universitycms.datagenerator.ScheduleGenerator;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.repository.GroupRepository;
import com.foxminded.universitycms.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleGeneratorService implements DataGeneratorService {

    private final ScheduleGenerator scheduleGenerator;
    private final GroupRepository groupRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public void generateData() {
        List<Group> allGroups = groupRepository.findAll();
        List<Schedule> generatedSchedule = scheduleGenerator.generateData(allGroups);

        scheduleRepository.saveAll(generatedSchedule);
    }
}
