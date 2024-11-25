package com.Enoca.Task.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private Double totalPrice;
    private int customerId;
    private int cartTempId;
    private List<CartTempDTO> cartTemps;
}
