package com.duan.duantotnghiep.entites;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Table(name = "Authorities", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"Username", "Role_id"})
})
@Data
public class Authorities implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID;

    @ManyToOne @JoinColumn(name = "Username")
    Accounts accounts;

    @ManyToOne @JoinColumn(name = "Role_id")
    Roles roles;
}
