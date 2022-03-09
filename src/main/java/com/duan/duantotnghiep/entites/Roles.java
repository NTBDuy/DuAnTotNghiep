package com.duan.duantotnghiep.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@Entity
@Table(name = "Roles")
@Data
public class Roles implements Serializable {
    @Id
    String ID;
    String name;

    @JsonIgnore
    @OneToMany(mappedBy = "roles")
    List<Authorities> authorities;
}
