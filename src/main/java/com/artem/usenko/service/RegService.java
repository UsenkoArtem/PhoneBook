package com.artem.usenko.service;


import com.artem.usenko.dto.User;

import java.io.IOException;

public interface RegService {

    User registration(User user) throws IOException;

    Boolean thisLoginAlreadyNotExists(String login) throws IOException;
}
