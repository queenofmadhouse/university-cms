package com.foxminded.universitycms.datagenerator.impl;

import com.foxminded.universitycms.datagenerator.ClassroomGenerator;
import com.foxminded.universitycms.entity.Classroom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClassroomGeneratorImpl implements ClassroomGenerator {

    @Value("${app.constants.generator.amount-of-classrooms}")
    private int amountOfClassrooms;

    @Value("${app.constants.generator.classroom-capacity}")
    private int classroomCapacity;

    @Override
    public List<Classroom> generateData() {

        List<Classroom> generatedClassrooms = new ArrayList<>();

        for (int i = 0; i < amountOfClassrooms; i++) {

            generatedClassrooms.add(Classroom.builder()
                    .classroomId(i)
                    .capacity(classroomCapacity)
                    .build());
        }

        return generatedClassrooms;
    }
}
