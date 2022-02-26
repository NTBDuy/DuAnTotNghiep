package com.duan.duantotnghiep.entites;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "Order_Details")
@Data
public class Order_details implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
//    private Integer product_detail_id;
    private Integer quantity;
    private BigDecimal price;
//    private Integer order_id;

    @ManyToOne @JoinColumn(name = "product_detail_id")
    Product_details product_details;

    @ManyToOne @JoinColumn(name = "order_id")
    Orders orders;
}
