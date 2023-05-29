package com.foxminded.universitycms.datagenerator.impl;

import com.foxminded.universitycms.datagenerator.GroupGenerator;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.repository.GroupRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class GroupGeneratorImpl implements GroupGenerator {

    private final static Faker faker = new Faker();
    private final GroupRepository groupRepository;

    @Override
    public List<Group> generateData(int amountOfGroups) {

        List<Group> generatedGroup = new ArrayList<>();

        for (int i = 0; i < amountOfGroups; i++) {
            generatedGroup.add(Group.builder()
                            .groupName(faker.bothify("?#").toUpperCase())
                    .build());
        }

        groupRepository.saveAll(generatedGroup);

        return generatedGroup;

    }
}
