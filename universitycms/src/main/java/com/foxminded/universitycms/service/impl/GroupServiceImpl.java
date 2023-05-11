package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.repository.GroupRepository;
import com.foxminded.universitycms.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
}
