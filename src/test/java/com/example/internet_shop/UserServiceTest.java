package com.example.internet_shop;

import com.example.internet_shop.dao.UserDao;
import com.example.internet_shop.entities.Bucket;
import com.example.internet_shop.entities.User;
import com.example.internet_shop.service.BucketService;
import com.example.internet_shop.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserDao userDao;

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    BucketService bucketService;

    @Before
    public void setup() throws Exception{
        User user = new User();

        user.setFirstname("test");
        user.setLastname("test");
        user.setAge(1);
        user.setEmail("test");
        user.setPassword("test");
        user.setAvatar("test");
        user.setNumber(123);
        Mockito.when(userDao.save(user)).thenReturn(user);
    }

    @Test
    public void addUser(){
        User user = new User();
        user.setFirstname("test");
        user.setLastname("test");
        user.setAge(1);
        user.setEmail("test");
        String encodedPassword = passwordEncoder.encode("test");
        user.setPassword(encodedPassword);
        user.setAvatar("test");
        user.setNumber(4335);


        User saved = userService.saveToDB(user);

        Bucket bucket = new Bucket();
        bucket.setUser(saved);
        bucketService.createBuk(bucket);

        Assertions.assertEquals(saved, null);

    }

}
