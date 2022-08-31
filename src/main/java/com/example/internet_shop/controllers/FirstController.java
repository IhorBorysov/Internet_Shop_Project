package com.example.internet_shop.controllers;

import com.example.internet_shop.entities.Product;
import com.example.internet_shop.entities.User;
import com.example.internet_shop.service.ProductService;
import com.example.internet_shop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class FirstController {

    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public FirstController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/registration")
    public String registration(){
        return "registration";
    }

    @Operation(summary = "Output of the first page")
    @RequestMapping({"", "/"})
    public String index(Model model){
        List<Product> products = productService.getAll();
        Product product = productService.getById(3);
        model.addAttribute("product", product);
        model.addAttribute("products", products);
        return "index";
    }

}
