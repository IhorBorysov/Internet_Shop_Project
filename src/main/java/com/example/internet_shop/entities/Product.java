package com.example.internet_shop.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "product_description")
    private String description;
    @Column(name = "product_price")
    private double price;
    @Column(name = "product_image")
    private String image;

    @ManyToMany(mappedBy = "products")
    List<Bucket> bucket = new ArrayList<>();
//    private LocalDateTime dateOfCreated;
//
//    @PrePersist
//    private void onCreate() { dateOfCreated = LocalDateTime.now(); }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
