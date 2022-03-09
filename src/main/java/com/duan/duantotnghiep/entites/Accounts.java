package com.duan.duantotnghiep.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
@Entity
@Table(name = "Accounts")
@Data
public class Accounts implements Serializable {
    @Id
    String username;
    String password;
    String fullname;
    String email;
    String image;
    String phone;
    Boolean gender;
    @Temporal(TemporalType.DATE)
    @Column(name = "[Register_date]")
    Date register_date = new Date();
    Boolean status;

    @JsonIgnore
    @OneToMany(mappedBy = "accounts", fetch = FetchType.EAGER)
    List<Authorities> authorities;

    @JsonIgnore
    @OneToMany(mappedBy = "accounts")
    List<Orders> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "accounts")
    List<Product_reviews> product_reviews;
}
