package com.foxminded.universitycms.datagenerator.impl;

import com.foxminded.universitycms.datagenerator.GroupGenerator;
import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Group;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class GroupGeneratorImpl implements GroupGenerator {

    private static final Random random = new Random();
    private final Faker faker = new Faker();
    private final int amountOfGroups;

    @Override
    public List<Group> generateData(List<Course> courses) {

        List<Group> generatedGroups = new ArrayList<>();

        for (int i = 0; i < amountOfGroups; i++) {

            int amountOfCourses = random.nextInt(3) + 3;

            Group generatedGroup = Group.builder()
                            .groupName(faker.bothify("?#").toUpperCase())
                    .build();

            if (!courses.isEmpty()) {
                Set<Course> coursesForGroup = new HashSet<>();

                for (int j = 0; j < amountOfCourses; j++) {
                    coursesForGroup.add(getRandomCourse(courses));
                }

                generatedGroup.setCourses(coursesForGroup);
            }

            generatedGroups.add(generatedGroup);
        }

        return generatedGroups;

    }

    private Course getRandomCourse(List<Course> courses) {
        return courses.get(random.nextInt(courses.size()));
    }
}
