package com.Enoca.Task.dto;

import com.Enoca.Task.entity.Cart;
import lombok.Data;

@Data
public class CustomerDTO {
    private String email;
    private String firstName;
    private String lastName;
    private Cart cart;
}
