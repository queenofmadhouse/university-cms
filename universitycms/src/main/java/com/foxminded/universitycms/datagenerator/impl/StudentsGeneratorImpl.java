package com.foxminded.universitycms.datagenerator.impl;

import com.foxminded.universitycms.datagenerator.StudentsGenerator;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Student;
import com.foxminded.universitycms.repository.GroupRepository;
import com.foxminded.universitycms.repository.StudentRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@RequiredArgsConstructor
@Component
public class StudentsGeneratorImpl implements StudentsGenerator {

    private static final Random random = new Random();
    private static final Faker fakeValuesService = new Faker();

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public List<Student> generateData(long amountOfStudents) {

        List<Student> studentList = new ArrayList<>();
        List<Group> groups = groupRepository.findAll();

        for (int i = 0; i < amountOfStudents; i++) {
            Student student = Student.builder()
                    .firstName(fakeValuesService.name().firstName())
                    .lastName(fakeValuesService.name().lastName())
                    .email(fakeValuesService.internet().emailAddress())
                    .password(fakeValuesService.internet().password(8, 20))
                    .build();

            if(!groups.isEmpty()) {
                Group randomGroup = getRandomGroup(groups);
                student.setGroup(randomGroup);
            }

            studentList.add(student);
        }

        studentRepository.saveAll(studentList);

        return studentList;
    }

    private Group getRandomGroup(List<Group> groups) {
        return groups.get(random.nextInt(groups.size()));
    }
}
