package com.foxminded.universitycms.datagenerator.impl;

import com.foxminded.universitycms.datagenerator.CourseGenerator;
import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Component
public class CourseGeneratorImpl implements CourseGenerator {

    private static final String[] COURSE_NAMES = {
            "Mathematics", "Physics",  "Biology", "Chemistry",
            "History", "Geography", "Literature", "Computer Science",
            "Physical Education", "Art"
    };
    private static final String[] DESCRIPTION_WORDS = {
            "fundamental", "concepts", "principles", "theories", "applications",
            "analysis", "techniques", "methods", "practice", "skills",
            "knowledge", "understanding", "critical", "thinking", "problem-solving",
            "collaboration", "communication", "innovation", "creativity", "research"
    };
//    private final int amountOfCourses;
    private static final Random random = new Random();

    @Override
    public List<Course> generateData() {
        List<Course> generatedCourses = new ArrayList<>();
        int amountOfCourses = 10;
        for (int i = 0; i < amountOfCourses; i++) {

            long courseId = (long) i + 1;
            String courseName = generateCourseName(i);
            String courseDescription = generateCourseDescription();

            generatedCourses.add(Course.builder()
                    .courseId(courseId)
                    .courseName(courseName)
                    .courseDescription(courseDescription)
                    .build());
        }

        return generatedCourses;
    }

    private String generateCourseName(int index) {
        return COURSE_NAMES[index];
    }

    private String generateCourseDescription() {

        int wordCount = random.nextInt(5) + 5;
        StringBuilder description = new StringBuilder();

        for (int i = 0; i < wordCount; i++) {
            String word = DESCRIPTION_WORDS[random.nextInt(DESCRIPTION_WORDS.length)];
            description.append(word);

            if (i < wordCount - 1) {
                description.append(" ");
            }
        }

        return description.toString();
    }
}
