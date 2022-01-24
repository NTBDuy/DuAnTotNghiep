package com.duan.duantotnghiep.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Product_Reviews")
@Data
public class Product_reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String comment;
    private Date date;
    private Double rating;
    private Integer customer_id;

    @JsonIgnore
    @OneToMany(mappedBy = "product_reviews")
    private List<Products> products;
}
