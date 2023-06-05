package com.foxminded.universitycms.datagenerator.impl;

import com.foxminded.universitycms.datagenerator.TeacherGenerator;
import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Teacher;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class TeacherGeneratorImpl implements TeacherGenerator {

    private static final Random random = new Random();
    private static final Faker fakeValuesService = new Faker();

    @Value("${app.constants.generator.amount-of-teachers}")
    private int amountOfTeachers;

    @Override
    public List<Teacher> generateData(List<Course> courses) {
        List<Teacher> generatedTeachers = new ArrayList<>();

        for(int i = 0; i < amountOfTeachers; i++) {

            int amountOfCourses = random.nextInt(3) + 1;

            Teacher generatedTeacher = Teacher.builder()
                    .firstName(fakeValuesService.name().firstName())
                    .lastName(fakeValuesService.name().lastName())
                    .email(fakeValuesService.internet().emailAddress())
                    .password(fakeValuesService.internet().password(8, 20))
                    .build();

            if(!courses.isEmpty()) {
                Set<Course> coursesRelatedToTeacher = new HashSet<>();

                for (int j = 0; j < amountOfCourses; j++) {
                    coursesRelatedToTeacher.add(getRandomCourse(courses));
                }

                generatedTeacher.setCourses(coursesRelatedToTeacher);
            }

            generatedTeachers.add(generatedTeacher);
        }

        return generatedTeachers;
    }

    private Course getRandomCourse(List<Course> courses) {
        return courses.get(random.nextInt(courses.size()));
    }
}
