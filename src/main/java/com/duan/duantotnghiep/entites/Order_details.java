package com.duan.duantotnghiep.entites;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
@Entity
@Table(name = "Order_Details")
@Data
public class Order_details implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID;
    Integer quantity;
    BigDecimal price;

    @ManyToOne @JoinColumn(name = "product_id")
    Products products;

    @ManyToOne @JoinColumn(name = "order_id")
    Orders orders;
}
