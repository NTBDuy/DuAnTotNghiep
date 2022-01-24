package com.duan.duantotnghiep.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Product_Details")
@Data
public class Product_details implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String description;
    private BigDecimal price;
    private String image;
    private Integer discount;
    private String category_id;
    private String brand_id;

    @JsonIgnore
    @OneToMany(mappedBy = "product_details")
    private List<Order_details> order_details;

    @JsonIgnore
    @OneToMany(mappedBy = "product_details")
    private List<Products> products;
}
