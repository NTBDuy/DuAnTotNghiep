package com.duan.duantotnghiep.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
@Entity
@Table(name = "Orders")
@Data
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID;
    BigDecimal amount;
    Date orderDate;
    String address;
    String phone;
    Integer status;

    @JsonIgnore
    @OneToMany(mappedBy = "orders")
    List<Order_details> orderDetails;

    @ManyToOne @JoinColumn(name = "Username")
    Accounts accounts;


}
