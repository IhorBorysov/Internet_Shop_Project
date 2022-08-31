package com.example.internet_shop.service.impl;

import com.example.internet_shop.dao.UserDao;
import com.example.internet_shop.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> byEmail = userDao.findByEmail(email);
        if(byEmail.isPresent()){
            return byEmail.get();
        }
        throw new UsernameNotFoundException("User with username " + email + " not found");
    }
}
