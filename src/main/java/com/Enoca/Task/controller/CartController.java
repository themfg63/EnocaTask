package com.Enoca.Task.controller;

import com.Enoca.Task.service.CartService;
import com.Enoca.Task.dto.CartDTO;
import com.Enoca.Task.dto.CartTempDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.Cart.CART)
public class CartController {

    @Autowired
    private CartService cartService;



    @GetMapping(ApiPaths.Cart.GET_CART)
    public CartDTO getCart (@PathVariable("id") int customerId){
        return cartService.getCart(customerId);
    }

    @PutMapping(ApiPaths.Cart.UPDATE_CART)
    public ResponseEntity<?> updateCart(@RequestBody CartDTO cartDTO){
        CartDTO updatedCart = cartService.updateCart(cartDTO);
        if(cartDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartDTO);
    }

    @DeleteMapping(ApiPaths.Cart.EMPTY_CART)
    public ResponseEntity<?> emptyCart(@PathVariable("id") int id){
        try{
            cartService.emptyCart(id);
            return new ResponseEntity<>("Sepetteki Ürünler Silindi!",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(ApiPaths.Cart.REMOVE_PRODUCT_FROM_CART)
    public ResponseEntity<?> removeProductFromCart(@PathVariable("cartTempId") int cartTempId, @PathVariable("cartId") int cartId){
        try{
            cartService.removeProductFromCart(cartTempId,cartId);
            return new ResponseEntity<>("Ürün Sepetten Kaldırıldı!",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(ApiPaths.Cart.ADD_PRODUCT_TO_CART)
    public ResponseEntity<?> addProductToCart(@RequestBody CartTempDTO cartTempDTO){
        CartDTO updatedCart = cartService.addProductToCart(cartTempDTO);
        if(updatedCart == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCart);
    }
}
