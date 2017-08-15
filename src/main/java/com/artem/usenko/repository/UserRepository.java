package com.artem.usenko.repository;

import com.artem.usenko.model.dataBase.UserEntity;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity getUserByLogin(String login);
    UserEntity deleteByLogin(String login);
}
