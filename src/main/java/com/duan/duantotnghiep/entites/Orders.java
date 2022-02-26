package com.duan.duantotnghiep.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
@Data
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private BigDecimal amount;
    private Date orderDate;
    private Integer status;
//    private Integer customer_id;

    @JsonIgnore
    @OneToMany(mappedBy = "orders")
    private List<Order_details> order_details;

    @ManyToOne @JoinColumn(name = "customer_id")
    Customers customers;


}
