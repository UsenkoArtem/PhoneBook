package com.artem.usenko.service.impl;


import com.artem.usenko.dto.User;
import com.artem.usenko.manager.UserManager;
import com.artem.usenko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserServiceTest implements UserService {
    private final UserManager userManager;

    @Autowired
    public UserServiceTest(UserManager userManager) {
        this.userManager = userManager;
    }


    @Override
    public User getUserByLogin(String login) throws IOException {
        return userManager.getUserByLogin(login);
    }

}
