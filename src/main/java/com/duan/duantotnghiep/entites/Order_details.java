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
    private Long ID;
    private Integer quantity;
    private BigDecimal price;

    @ManyToOne @JoinColumn(name = "product_id")
    Products products;

    @ManyToOne @JoinColumn(name = "order_id")
    Orders orders;
}
