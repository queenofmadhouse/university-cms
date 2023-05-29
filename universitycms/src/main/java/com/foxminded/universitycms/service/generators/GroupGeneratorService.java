package com.foxminded.universitycms.service.generators;

import com.foxminded.universitycms.datagenerator.GroupGenerator;
import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.repository.CourseRepository;
import com.foxminded.universitycms.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GroupGeneratorService implements DataGeneratorService {

    private final GroupGenerator groupGenerator;
    private final CourseRepository courseRepository;
    private final GroupRepository groupRepository;

    @Override
    public void generateData() {
        List<Course> allCourses = courseRepository.findAll();
        List<Group> generatedGroups = groupGenerator.generateData(allCourses);

        groupRepository.saveAll(generatedGroups);
    }
}
