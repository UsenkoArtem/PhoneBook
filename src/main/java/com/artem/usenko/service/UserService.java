package com.artem.usenko.service;

import com.artem.usenko.dto.User;

import java.io.IOException;

public interface UserService {
    User getUserByLogin(String login) throws IOException;

}
