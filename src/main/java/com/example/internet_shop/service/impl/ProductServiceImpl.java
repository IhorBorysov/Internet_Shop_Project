package com.example.internet_shop.service.impl;

import com.example.internet_shop.dao.ProductDao;
import com.example.internet_shop.dao.UserDao;
import com.example.internet_shop.entities.Product;
import com.example.internet_shop.entities.User;
import com.example.internet_shop.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j(topic = "ProductService")
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;


    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAll() {
        log.info("Get all product!");
        return productDao.findAll();
    }

    @Override
    public Product saveToDB(Product product) {
        log.info("Product save!");
        return productDao.save(product);
    }


    @Override
    public Product getById(int id) {
        log.info("Product ID!");
        return productDao.findById(id).orElse(new Product());
    }



    @Override
    public void transferFile(MultipartFile file) {
        String pathToFolder = System.getProperty("user.home") + File.separator + "imag" + File.separator;
        System.out.println(pathToFolder);

        try {
            log.info("Photo save to file images!");
            file.transferTo(new File(pathToFolder + file.getOriginalFilename()));
        } catch (IOException exception) {
            log.error("Photo not save to file images!");
            exception.printStackTrace();
        }
    }
}
