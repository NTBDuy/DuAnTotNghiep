package com.duan.duantotnghiep.entites;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Products")
@Data
public class Products implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String name;
    private Boolean active;
//    private Integer detail_id;
//    private Integer review_id;

    @ManyToOne @JoinColumn(name = "detail_id")
    Product_details product_details;

    @ManyToOne @JoinColumn(name = "review_id")
    Product_reviews product_reviews;
}
