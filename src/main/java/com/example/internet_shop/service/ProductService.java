package com.example.internet_shop.service;

import com.example.internet_shop.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product saveToDB(Product product);
    Product getById(int id);
    void transferFile(MultipartFile file);
}
