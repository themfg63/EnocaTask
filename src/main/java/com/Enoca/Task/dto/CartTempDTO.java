package com.Enoca.Task.dto;

import lombok.Data;

@Data
public class CartTempDTO {
    private int quantity;
    private double adetPrice;
    private int customerId;
    private ProductDTO productDTO;
    private int productId;
}
