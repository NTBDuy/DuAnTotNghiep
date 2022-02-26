package com.duan.duantotnghiep.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Brands")
@Data
public class Brands implements Serializable {
    @Id
    private String ID;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "brands")
    private List<Product_details> product_details;
}
