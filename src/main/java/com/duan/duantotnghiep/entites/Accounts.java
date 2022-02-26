package com.duan.duantotnghiep.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    private Double gender;
    private Date register_date;
    private Double status;

    @JsonIgnore
    @OneToMany(mappedBy = "accounts")
    private List<Authorities> authorities;

    @JsonIgnore
    @OneToMany(mappedBy = "accounts")
    private List<Customers> customers;
}
