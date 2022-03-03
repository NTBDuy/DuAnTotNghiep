package com.duan.duantotnghiep.entites;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Authorities")
@Data
public class Authorities implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne @JoinColumn(name = "Username")
    Accounts accounts;

    @ManyToOne @JoinColumn(name = "Role_id")
    Roles roles;
}
