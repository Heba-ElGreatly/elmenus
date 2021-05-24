package com.elmenus.user.model;

import com.elmenus.order.model.Orders;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "User")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private Integer phone;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY , cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties
    private List<Orders> orders;

}
