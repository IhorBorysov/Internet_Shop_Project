package com.example.internet_shop.controllers;

import com.example.internet_shop.entities.Product;
import com.example.internet_shop.entities.User;
import com.example.internet_shop.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Access Denied"),
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping
    public String getProduct( Model model){
        List<Product> all = productService.getAll();
        model.addAttribute("products",all);
        return "index";
    }

    @Operation(summary = "Get products by id")
    @GetMapping("/{id}")
    public String getById(Model model,
                          @PathVariable int id){
        Product byId = productService.getById(id);
        model.addAttribute("product", byId);
        return "productPage";
    }

    @Operation(summary = "Post products save")
    @PostMapping("/save")
    public String saveProduct(Model model,
                           @RequestParam String name,
                           @RequestParam String description,
                           @RequestParam double price,
                           @RequestParam MultipartFile image){
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);

        if(image != null){
            productService.transferFile(image);
            product.setImage(image.getOriginalFilename());
        }

        Product saved = productService.saveToDB(product);
        model.addAttribute("product",saved);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userAntification = (User) authentication.getPrincipal();
        model.addAttribute("userAntification", userAntification);

        return "productPage";
    }
}
