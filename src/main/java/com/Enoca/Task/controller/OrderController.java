package com.Enoca.Task.controller;

import com.Enoca.Task.dto.CartTempDTO;
import com.Enoca.Task.dto.OrderDTO;
import com.Enoca.Task.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.Order.ORDER)
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(ApiPaths.Order.PLACE_ORDER)
    public void placeOrder(@RequestBody List<CartTempDTO> cartTempDTOList){
         orderService.placeOrder(cartTempDTOList);
    }

    @GetMapping(ApiPaths.Order.GET_ORDER_FOR_CODE)
    public ResponseEntity<OrderDTO> getOrderForCode(@PathVariable("orderId") int orderId){
        return ResponseEntity.ok(orderService.getOrderForCode(orderId));
    }
    @GetMapping(ApiPaths.Order.GET_ALL_ORDER_FOR_CUSTOMER)
    public ResponseEntity<List<OrderDTO>> getAllOrderForCustomer(@PathVariable("customerId") int customerId){
        return ResponseEntity.ok(orderService.getAllOrderForCustomer(customerId));
    }

}
