package com.example.internet_shop.service.impl;

import com.example.internet_shop.dao.BucketDao;
import com.example.internet_shop.entities.Bucket;
import com.example.internet_shop.service.BucketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j(topic = "UserService")
public class BucketServiceImpl implements BucketService {

    private final BucketDao bucketDao;

    public BucketServiceImpl(BucketDao bucketDao) {
        this.bucketDao = bucketDao;
    }

    @Override
    public List<Bucket> getAll() {
        List<Bucket> all = bucketDao.findAll();
        log.info("All bucket" + all);
        return all;
    }

    @Override
    public Bucket getById(int id) {
        log.info("Product ID!" + id);
        return bucketDao.findById(id).get();
    }

    @Override
    public void createBuk(Bucket bucket) {
        log.info("Save buket" + bucket);
        bucketDao.save(bucket);
    }

    @Override
    public void delete(Bucket bucket) {
        log.info("Delete buket" + bucket);
        bucketDao.delete(bucket);
    }
}
