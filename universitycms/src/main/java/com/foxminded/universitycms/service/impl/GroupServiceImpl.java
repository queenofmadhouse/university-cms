package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.repository.GroupRepository;
import com.foxminded.universitycms.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Override
    public List<Group> findAllByCourse(Course course) {
        return groupRepository.findAllByCourse(course);
    }

    @Override
    public Group findById(long id) {
        return groupRepository.findById(id).orElseThrow(RuntimeException::new);
    }

}
