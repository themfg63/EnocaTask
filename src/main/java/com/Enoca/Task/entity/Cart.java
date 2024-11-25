package com.Enoca.Task.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class Cart extends BaseEntity implements Serializable {
    @Column(name = "quantity")
    private int quantity;

    @Column(name = "customerId")
    private int customerId;

    @Column(name = "cartTempId")
    private Integer cartTempId;

}
