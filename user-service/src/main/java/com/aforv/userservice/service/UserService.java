package com.aforv.userservice.service;

import com.aforv.userservice.dto.ResponseDto;
import com.aforv.userservice.entity.User;

public interface UserService {
    User saveUser(User user);

    ResponseDto getUser(Long userId);
}