package com.artem.usenko.manager;

import com.artem.usenko.dto.User;

import java.io.IOException;


public interface UserManager {

    User getUserByLogin(String login) throws IOException;

    User addNewUser(User userEntity) throws IOException;

    User getUserById(int id) throws IOException;

}
