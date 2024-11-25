package com.Enoca.Task.service;

import com.Enoca.Task.entity.CartTemp;
import com.Enoca.Task.repository.CartTempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartTempService {

    @Autowired
    CartTempRepository cartTempRepository;


    public List<CartTemp> getCartTemp(int cartId){
        return cartTempRepository.findByCartId(cartId);
    }
}
