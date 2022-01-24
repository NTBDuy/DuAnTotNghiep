package com.duan.duantotnghiep.entites;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Authorities")
@Data
public class Authorities {
    @Id
    private String ID;
    private String username;
    private String role_id;
}
