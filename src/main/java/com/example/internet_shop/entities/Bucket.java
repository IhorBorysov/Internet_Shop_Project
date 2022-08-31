package com.example.internet_shop.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "bucket")
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bucket_id")
    private int id;
    @Column(name = "bucket_createDate")
    private Timestamp createDate;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
            @JoinTable (
                    name = "bucket_products",
                    joinColumns = {@JoinColumn (name = "bucket_id")},
                    inverseJoinColumns = {@JoinColumn (name = "product_id")}
            )
    List<Product> products = new ArrayList<>();

    @OneToOne(orphanRemoval = true)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", user=" + user +
                '}';
    }
}
