package com.artem.usenko.manager.impl.dataBase;

import com.artem.usenko.dto.User;
import com.artem.usenko.dto.mapper.dataBase.UserMapper;
import com.artem.usenko.manager.UserManager;
import com.artem.usenko.model.dataBase.UserEntity;
import com.artem.usenko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Profile("dataBase")
@Repository
public class UserManagerImpl implements UserManager {

    private final UserRepository userRepository;

    @Autowired
    public UserManagerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByLogin(String login) {
        return UserMapper.fromAO(userRepository.getUserByLogin(login));
    }

    @Override
    public User addNewUser(User user) {
        UserEntity userEntity = UserMapper.toAO(user);
        return UserMapper.fromAO(userRepository.save(userEntity));
    }

    @Override
    public User getUserById(int id) throws IOException {
        return UserMapper.fromAO(userRepository.findOne(id));
    }

}
