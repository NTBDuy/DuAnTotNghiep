package com.duan.duantotnghiep.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Customers")
@Data
public class Customers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
//    private String username;

    @JsonIgnore
    @OneToMany(mappedBy = "customers")
    private List<Orders> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "customers")
    private List<Product_reviews> product_reviews;

    @ManyToOne @JoinColumn(name = "Username")
    Accounts accounts;
}
