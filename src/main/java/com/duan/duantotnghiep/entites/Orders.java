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
    private Long ID;
    private Integer amount;
    private Date orderDate;
    private String address;
    private Integer status;

    @JsonIgnore
    @OneToMany(mappedBy = "orders")
    private List<Order_details> orderDetails;

    @ManyToOne @JoinColumn(name = "Username")
    Accounts accounts;


}
