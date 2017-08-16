package com.artem.usenko.service.impl;

import com.artem.usenko.dto.User;
import com.artem.usenko.manager.UserManager;
import com.artem.usenko.service.RegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RegServiceImpl implements RegService {
    private final UserManager userManager;

    @Autowired
    public RegServiceImpl(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public User registration(User user) throws IOException {
        User userByLogin = userManager.getUserByLogin(user.getLogin());
        if (userByLogin != null) return null;
        return userManager.addNewUser(user);
    }

    @Override
    public Boolean thisLoginAlreadyNotExists(String login) throws IOException {
        User userByLogin = userManager.getUserByLogin(login);
        return userByLogin != null;
    }
}
