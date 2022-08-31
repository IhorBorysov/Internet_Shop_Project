package com.example.internet_shop.service.impl;

import com.example.internet_shop.dao.UserDao;
import com.example.internet_shop.entities.User;
import com.example.internet_shop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j(topic = "UserService")
public class UserServiceImpl implements UserService {

    private final  UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAll() {
        log.info("Get all users!");
        List<User> all = userDao.findAll();
        return all;
    }

    @Override
    public User saveToDB(User user) {
        log.info("User save!" + user.getUsername());
        User saved = userDao.save(user);
        return saved;
    }

    @Override
    public User getById(int id) {
       log.info("User ID!");
       return userDao.findById(id).orElse(new User());
    }

    @Override
    public void transferFile(MultipartFile file){
        String pathToFolder = System.getProperty("user.home") + File.separator + "images" + File.separator;
        System.out.println(pathToFolder);

        try {
            log.info("Avatar save to file images!");
            file.transferTo(new File(pathToFolder + file.getOriginalFilename()));
        } catch (IOException exception) {
            log.error("Avatar not save to file images!");
            exception.printStackTrace();
        }
    }
}
