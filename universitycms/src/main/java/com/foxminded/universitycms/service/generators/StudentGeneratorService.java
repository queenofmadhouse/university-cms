package com.foxminded.universitycms.service.generators;

import com.foxminded.universitycms.datagenerator.StudentsGenerator;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Student;
import com.foxminded.universitycms.repository.GroupRepository;
import com.foxminded.universitycms.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentGeneratorService implements DataGeneratorService {

    private final StudentsGenerator studentsGenerator;
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    @Override
    public void generateData() {
        List<Group> allGroups = groupRepository.findAll();
        List<Student> generatedStudents = studentsGenerator.generateData(allGroups);

        studentRepository.saveAll(generatedStudents);
    }
}
