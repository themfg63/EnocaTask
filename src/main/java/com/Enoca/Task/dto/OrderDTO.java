package com.Enoca.Task.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class OrderDTO {


    private int customerId;

    private double price;

    private int adet;

    private String name;


}
