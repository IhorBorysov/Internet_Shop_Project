package com.example.internet_shop.service;
import com.example.internet_shop.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

public interface UserService {
    List<User> getAll();
    User saveToDB(User user);
    User getById(int id);
    void transferFile(MultipartFile file);
}
