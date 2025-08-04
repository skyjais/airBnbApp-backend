package com.codingakash.projects.airBnbApp.service;

import com.codingakash.projects.airBnbApp.dto.ProfileUpdateRequestDto;
import com.codingakash.projects.airBnbApp.dto.UserDto;
import com.codingakash.projects.airBnbApp.entity.User;

public interface UserService {

    User getUserById(Long id);

    void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);

    UserDto getMyProfile();
}
