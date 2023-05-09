package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
