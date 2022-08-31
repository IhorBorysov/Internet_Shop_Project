package com.example.internet_shop.controllers;

import com.example.internet_shop.entities.Bucket;
import com.example.internet_shop.entities.Product;
import com.example.internet_shop.entities.User;
import com.example.internet_shop.service.BucketService;
import com.example.internet_shop.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/bucket")
public class BucketController {
    private final BucketService bucketService;
    private final ProductService productService;

    public BucketController(BucketService bucketService, ProductService productService) {
        this.bucketService = bucketService;
        this.productService = productService;
    }

    @GetMapping
    public String Bucket(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        System.out.println(user);
        int id = user.getId();
        Bucket byId = bucketService.getById(id);
        System.out.println(byId);

        List<Product>products = byId.getProducts();

        model.addAttribute("products", products);
        model.addAttribute("user", user);

        return "bucketPage";
    }

    @Operation(summary = "Product save from the Bucket")
    @GetMapping("/addProduct/{id}")
    public String addProductBucket(Model model, @PathVariable int id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Bucket byId = bucketService.getById(user.getId());
        Product product = productService.getById(id);
        List<Product> products = byId.getProducts();
        products.add(product);
        byId.setProducts(products);
        bucketService.createBuk(byId);

        model.addAttribute("products" , products);
        model.addAttribute("user", user);
        return "bucketPage";
    }

    @Operation(summary = "Product delete from the Bucket")
    @GetMapping("/delete/{id}")
    public String deleteProductBucket(Model model, @PathVariable int id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Bucket byId = bucketService.getById(user.getId());
        Product product = productService.getById(id);
        List<Product> products = byId.getProducts();
        products.remove(product);
        byId.setProducts(products);
        bucketService.createBuk(byId);

        model.addAttribute("products" , products);
        model.addAttribute("user", user);
        return "bucketPage";
    }

}
