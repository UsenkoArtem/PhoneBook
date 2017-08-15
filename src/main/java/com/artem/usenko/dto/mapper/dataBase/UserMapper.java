package com.artem.usenko.dto.mapper.dataBase;

import com.artem.usenko.dto.User;
import com.artem.usenko.model.dataBase.UserEntity;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Profile(value = "dataBase")
public class UserMapper {
    public static List<User> fromAO(Iterable<UserEntity> users) {
        List<User> userDAOS = new ArrayList<>();
        if (users == null) return null;
        for (UserEntity userEntity : users) {
            userDAOS.add(fromAO(userEntity));
        }
        return userDAOS;
    }

    public static User fromAO(UserEntity user) {
        if (user == null) return null;
        else
            return new User(user.getId(), user.getLogin(), user.getFirstName(), user.getLastName(), user.getPatronymic(),
                    user.getPassword());
    }


    public static UserEntity toAO(User user) {
        UserEntity userEntity = new UserEntity();
        if (user != null) {
            userEntity.setId(user.getId());
            userEntity.setLogin(user.getLogin());
            userEntity.setFirstName(user.getFirstName());
            userEntity.setLastName(user.getLastName());
            userEntity.setPatronymic(user.getPatronymic());
            userEntity.setPassword(user.getPassword());
        } else return null;
        return userEntity;
    }
}
