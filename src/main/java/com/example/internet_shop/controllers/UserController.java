package com.example.internet_shop.controllers;

import com.example.internet_shop.entities.Bucket;
import com.example.internet_shop.entities.User;
import com.example.internet_shop.service.BucketService;
import com.example.internet_shop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j(topic = "UserController")
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final BucketService bucketService;
    private final PasswordEncoder passwordEncoder;


    public UserController(UserService userService, BucketService bucketService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.bucketService = bucketService;
        this.passwordEncoder = passwordEncoder;
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Access Denied"),
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping
    public String getUser(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        List<User> all = userService.getAll();
        model.addAttribute("users",all);


        model.addAttribute("userTake", user);
        log.info("All User!");
        return "allUsers";
    }

    @Operation(summary = "Get users by id")
    @GetMapping("/user")
    public String getById(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        System.out.println(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("userTake", user);
        log.info("User ID!");
        return "userPage";
    }

    @Operation(summary = "Post users save")
    @PostMapping("/save")
    public String saveUser(Model model,
                           @RequestParam String firstname,
                           @RequestParam String lastname,
                           @RequestParam int age,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam int number,
                           @RequestParam MultipartFile avatar){
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setAge(age);
        user.setEmail(email);
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setNumber(number);

        if(avatar != null){
            userService.transferFile(avatar);
            user.setAvatar(avatar.getOriginalFilename());
        }

        User saved = userService.saveToDB(user);
        model.addAttribute("user",saved);

        log.info("User save!");

        Bucket bucket = new Bucket();
        bucket.setUser(saved);
        bucketService.createBuk(bucket);


        model.addAttribute("userTake",saved);
        log.info("Bucket user save!");

        return "userPage";
    }

}
