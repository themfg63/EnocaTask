package com.Enoca.Task.service;

import com.Enoca.Task.dto.CustomerDTO;
import com.Enoca.Task.entity.Cart;
import com.Enoca.Task.entity.Customer;
import com.Enoca.Task.repository.CartRepository;
import com.Enoca.Task.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    public CustomerDTO addCustomer(CustomerDTO customerDTO){
       if(customerRepository.findByEmail(customerDTO.getEmail()).isPresent()){
            throw new IllegalArgumentException("Müşteri Kaydı Bulunmaktadır!");
       }

       Customer newCustomer = new Customer();
       newCustomer.setFirstName(customerDTO.getFirstName());
       newCustomer.setLastName(customerDTO.getLastName());
       newCustomer.setEmail(customerDTO.getEmail());

       Customer savedCustomer = customerRepository.save(newCustomer);

       Cart cart = new Cart();
       cart.setCustomerId(savedCustomer.getId());
       cartRepository.save(cart);

       customerDTO.setCart(cart);
       return customerDTO;
    }
}
