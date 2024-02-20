package com.nobroker.service.impl;

import com.nobroker.entity.User;
import com.nobroker.payload.UserDto;
import com.nobroker.repository.UserRepository;
import com.nobroker.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceIMPL implements UserService {
     @Autowired
     private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;


    @Override
    public long createUser(UserDto userDto) {
        User user =mapToEntity(userDto);
        User savedUser = userRepository.save(user);
        long id = savedUser.getId();
        return id;


    }

    User mapToEntity(UserDto userDto){
        User user = modelMapper.map(userDto, User.class);

        return user;
    }



    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public void verifyEmail(User user) {
        user.setEmailVerified(true);
        userRepository.save(user);
    }










    public boolean isEmailVerified(String email) {

        User user = userRepository.findByEmail(email);
        boolean emailVerified = user.isEmailVerified();   //just type user.  it will show you this boolean field as it is bollean in nature
                                                          // it doesnt  shows GetEmailVerified it shows isEmailVerified
                                                           //like SET , Get method of enitity object . it determines
                                                           //emailVerified  column in sql table if it is zero means  false , 1 means true


        return user != null && emailVerified;

    }
}
