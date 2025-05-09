package com.project.shopApp.service;

import com.project.shopApp.dtos.UserDto;
import com.project.shopApp.models.User;

public interface UserService {
    User createUser(UserDto userDto);

    String login(String phoneNumber, String password);
}
