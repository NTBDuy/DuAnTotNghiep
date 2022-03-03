package com.duan.duantotnghiep.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Products")
@Data
public class Products implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String name;
    private BigDecimal price;
    private String images;
    private Boolean active;
    private Integer discount;
    private String description;

    @ManyToOne @JoinColumn(name = "review_id")
    Product_reviews product_reviews;

    @ManyToOne @JoinColumn(name = "brand_id")
    Brands brands;

    @ManyToOne @JoinColumn(name = "category_id")
    Categories categories;

    @JsonIgnore
    @OneToMany(mappedBy = "products")
    private List<Order_details> order_details;
}
