package com.example.internet_shop.dao;

import com.example.internet_shop.entities.Bucket;
import com.example.internet_shop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BucketDao extends JpaRepository<Bucket, Integer> {
    Optional<Bucket> findById(int id);
}
