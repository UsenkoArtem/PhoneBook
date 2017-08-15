package com.artem.usenko.dto.mapper.csv;

import com.artem.usenko.dto.User;
import com.artem.usenko.model.csv.UserCSV;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Profile(value = "csvFile")
public class UserMapper {
    public static List<User> fromAO(Iterable<UserCSV> userCSVS) {
        List<User> userDAOS = new ArrayList<>();
        if (userCSVS == null) return null;
        for (UserCSV userCSV : userCSVS) {
            userDAOS.add(fromAO(userCSV));
        }
        return userDAOS;
    }

    public static User fromAO(UserCSV user) {
        if (user == null) return null;
        else
            return new User(parse(user.getId()), user.getLogin(), user.getFirstName(), user.getLastName(), user.getPatronymic(),
                    user.getPassword());
    }


    public static UserCSV toAO(User user) {
        UserCSV userCSV = new UserCSV();
        if (user != null) {
            userCSV.setId(String.valueOf(user.getId()));
            userCSV.setLogin(user.getLogin());
            userCSV.setFirstName(user.getFirstName());
            userCSV.setLastName(user.getLastName());
            userCSV.setPatronymic(user.getPatronymic());
            userCSV.setPassword(user.getPassword());
        } else return null;
        return userCSV;
    }
    private static int parse(String id) {
        int idInt = 0;
        char[] chars = id.toCharArray();
        for (int i = 0; i<chars.length;++i) {
            char digit = chars[i];
            if  (Character.isDigit(digit)) {
                idInt *= 10;
                idInt += (int) (digit - 48);
            }
        }
        return idInt;
    }
}
