package com.nobroker.service;

import com.nobroker.entity.User;
import com.nobroker.payload.UserDto;

import java.util.List;

public interface UserService {



    public long createUser(UserDto userDto);

    User getUserByEmail(String email);

    void verifyEmail(User user);

    public boolean isEmailVerified(String email) ;


}
