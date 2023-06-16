package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import com.foxminded.universitycms.entity.User;

import javax.jws.soap.SOAPBinding;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
