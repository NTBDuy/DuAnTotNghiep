package com.duan.duantotnghiep.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@Entity
@Table(name = "Brands")
@Data
public class Brands implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID;
    String name;

    @JsonIgnore
    @OneToMany(mappedBy = "brands", cascade= CascadeType.ALL)
    List<Products> products;
}
