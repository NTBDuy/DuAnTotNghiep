package com.duan.duantotnghiep.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("serial")
@Entity
@Table(name = "Products")
@Data
public class Products implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID;
    String name;
    BigDecimal price;
    String images;
    Boolean active;
    Integer discount;
    String description;

    @ManyToOne @JoinColumn(name = "review_id")
    Product_reviews product_reviews;

    @ManyToOne @JoinColumn(name = "brand_id")
    Brands brands;

    @ManyToOne @JoinColumn(name = "category_id")
    Categories categories;

    @JsonIgnore
    @OneToMany(mappedBy = "products")
    List<Order_details> order_details;
}
