package com.Enoca.Task.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product extends BaseEntity{
    @Column(name = "name" ,nullable = false)
    private String name;
    @Column(name = "price" ,nullable = false)
    private Double price;
    @Column(name = "stock" ,nullable = false)
    private Integer stock;
}
