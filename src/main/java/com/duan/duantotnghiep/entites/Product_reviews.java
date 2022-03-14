package com.duan.duantotnghiep.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
@Entity
@Table(name = "Product_Reviews")
@Data
public class Product_reviews implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID;
    String comment;
    Date date;
    Double rating;
//    private Integer customer_id;

    @JsonIgnore
    @OneToMany(mappedBy = "product_reviews")
    List<Products> products;

    @ManyToOne @JoinColumn(name = "Username")
    Accounts accounts;
}
