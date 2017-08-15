package com.artem.usenko.security;

import com.artem.usenko.dto.User;
import com.artem.usenko.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

   private final UserManager userManager;

    @Autowired
    public UserDetailsServiceImpl(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User userByLogin = null;
        try {
            userByLogin = userManager.getUserByLogin(login);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(null == userByLogin){
            throw new UsernameNotFoundException("No present login: " + login);
        }else {

            return new UserDetailsImpl(userByLogin);
        }
    }
}
