package com.nobroker.service;

import com.nobroker.entity.User;
import com.nobroker.payload.UserDto;

public interface UserService {



    public long createUser(UserDto userDto);

    User getUserByEmail(String email);

    void verifyEmail(User user);
}
