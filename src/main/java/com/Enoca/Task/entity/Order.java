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
@Table(name = "`order`")
public class Order extends BaseEntity implements Serializable {

    @Column(name = "customer_id",nullable = false)
    private int customerId;
    @Column(name = "price",nullable = false)
    private double price;
    @Column(name = "adet",nullable = false)
    private int adet;
    @Column(name = "name",nullable = false)
    private String name;



}
