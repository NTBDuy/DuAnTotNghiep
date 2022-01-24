package com.duan.duantotnghiep.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Customers")
@Data
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String username;

    @JsonIgnore
    @OneToMany(mappedBy = "customers")
    private List<Orders> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "customers")
    private List<Product_reviews> product_reviews;
}
