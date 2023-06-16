package com.foxminded.universitycms.service;

import com.foxminded.universitycms.entity.User;

public interface UserService {

    User findByEmail(String email);
}
