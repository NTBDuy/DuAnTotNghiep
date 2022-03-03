package com.duan.duantotnghiep.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Accounts")
@Data
public class Accounts implements Serializable {
    @Id
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String image;
    private String phone;
    private Boolean gender;
    @Temporal(TemporalType.DATE)
    @Column(name = "[Register_date]")
    Date register_date = new Date();
    private Boolean status;

    @JsonIgnore
    @OneToMany(mappedBy = "accounts")
    private List<Authorities> authorities;

    @JsonIgnore
    @OneToMany(mappedBy = "accounts")
    private List<Orders> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "accounts")
    private List<Product_reviews> product_reviews;

//    @JsonIgnore
//    @OneToMany(mappedBy = "accounts")
//    private List<Customers> customers;
}
