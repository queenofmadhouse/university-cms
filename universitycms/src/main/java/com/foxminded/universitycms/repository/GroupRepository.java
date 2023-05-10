package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

    public Optional<Group> findByGroupName(String firstName);

    public Optional<Group> findByGroupId(long id);

    public List<Group> findAllGroup();

    @Transactional
    @Modifying
    public void deleteByGroupId(long id);
}
