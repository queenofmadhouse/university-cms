package com.foxminded.universitycms.datagenerator.impl;

import com.foxminded.universitycms.datagenerator.ScheduleGenerator;
import com.foxminded.universitycms.entity.Classroom;
import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Teacher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
public class ScheduleGeneratorImpl implements ScheduleGenerator {

    private static final Random random = new Random();

    @Transactional
    @Override
    public List<Schedule> generateData(List<Group> groups, List<Classroom> classrooms) {

        List<Schedule> generatedSchedule = new ArrayList<>();

        for (Group group : groups) {

            LocalDate date = LocalDate.now();

            for (int j = 0; j <= 30; j++) {

                int amountOfLessonsForDay = random.nextInt(3) + 2;

                for (int n = 0; n < amountOfLessonsForDay; n++) {

                    Course randomCourse = getRandomCourse(group.getCourses());
                    Teacher randomTeacher = getRandomTeacher(randomCourse);
                    Classroom randomClassroom = getRandomClassroom(classrooms);
                    LocalDateTime lessonStart = date.atStartOfDay().plusHours(8).plusHours(n);
                    LocalDateTime lessonEnd = lessonStart.plusMinutes(50);

                    Schedule lessonForStep = Schedule.builder()
                            .teacher(randomTeacher)
                            .course(randomCourse)
                            .group(group)
                            .classroomId(randomClassroom)
                            .lessonStart(lessonStart)
                            .lessonEnd(lessonEnd)
                            .lessonDescription("-")
                            .build();

                    generatedSchedule.add(lessonForStep);
                }

                date = date.plusDays(1);
            }
        }

        return generatedSchedule;
    }

    private Course getRandomCourse(Set<Course> courses) {
        List<Course> courseRelatedToGroup = new ArrayList<>(courses);

        return courseRelatedToGroup.get(random.nextInt(courses.size()));
    }

    private Teacher getRandomTeacher(Course course) {

        List<Teacher> teachersRelatedToCourse = new ArrayList<>(course.getTeachers());

        return teachersRelatedToCourse.get(random.nextInt(teachersRelatedToCourse.size()));
    }

    private Classroom getRandomClassroom(List<Classroom> classrooms) {

        return classrooms.get(random.nextInt(classrooms.size()));
    }
}
