package com.example.internet_shop.service;



import com.example.internet_shop.entities.Bucket;

import java.util.List;

public interface BucketService {
    List<Bucket> getAll();
    Bucket getById(int id) throws RuntimeException;
    void createBuk(Bucket bucket);
    void delete(Bucket bucket);
}
