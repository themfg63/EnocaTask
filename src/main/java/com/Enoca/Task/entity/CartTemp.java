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
@Table(name = "cart_temp")
public class CartTemp extends BaseEntity implements Serializable {
    private int quantity;
    private double adetPrice;
    private int customerId;
    private int productId;
    private int cartId;
}
