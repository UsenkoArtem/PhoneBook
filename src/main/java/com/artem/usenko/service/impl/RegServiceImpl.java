package com.artem.usenko.service.impl;

import com.artem.usenko.dto.User;
import com.artem.usenko.manager.UserManager;
import com.artem.usenko.service.RegService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
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
    public User registration(User user) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        User userByLogin = userManager.getUserByLogin(user.getLogin());
        if (userByLogin != null) return null;
        User newUser = userManager.addNewUser(user);
        return newUser;
    }

    @Override
    public Boolean thisLoginAlreadyNotExists(String login) throws IOException {
        User userByLogin = userManager.getUserByLogin(login);
        return userByLogin != null;
    }
}
