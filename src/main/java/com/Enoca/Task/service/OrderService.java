package com.Enoca.Task.service;

import com.Enoca.Task.dto.CartTempDTO;
import com.Enoca.Task.dto.OrderDTO;
import com.Enoca.Task.entity.Order;
import com.Enoca.Task.entity.Product;
import com.Enoca.Task.repository.CartTempRepository;
import com.Enoca.Task.repository.OrderRepository;
import com.Enoca.Task.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {


    @Autowired
    CartTempRepository cartTempRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartService cartService;

    @Transactional
    public void placeOrder(List<CartTempDTO> cartTempDTOList){
        Order order ;
        int customerId=0;
        for(CartTempDTO cartTempDTO:cartTempDTOList){
            order=new Order();
            Optional<Product> byId = productRepository.findById(cartTempDTO.getProductId());
            customerId=cartTempDTO.getCustomerId();
            if (byId.isPresent()){
                order.setName(byId.get().getName());
                order.setPrice(cartTempDTO.getAdetPrice());
                order.setAdet(cartTempDTO.getQuantity());
                order.setCustomerId(cartTempDTO.getCustomerId());
                orderRepository.save(order);
            }
        }
        cartService.emptyCart(customerId);

    }

    public OrderDTO getOrderForCode(int id){
        OrderDTO response=new OrderDTO();
        Optional<Order> byId = orderRepository.findById(id);
        if (byId.isPresent()){
            response.setName(byId.get().getName());
            response.setPrice(byId.get().getPrice());
            response.setAdet(byId.get().getAdet());
            response.setCustomerId(byId.get().getCustomerId());
        }
        return response;
    }
    public List<OrderDTO> getAllOrderForCustomer(int customerId){
        OrderDTO orderDto=new OrderDTO();
        List<Order> orderList = orderRepository.findByCustomerId(customerId);
        List<OrderDTO> response=new ArrayList<>();
            for(Order order:orderList){
                orderDto.setName(order.getName());
                orderDto.setPrice(order.getPrice());
                orderDto.setAdet(order.getAdet());
                response.add(orderDto);
              }
        return response;
    }




}
