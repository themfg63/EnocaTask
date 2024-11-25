package com.Enoca.Task.service;

import com.Enoca.Task.dto.CartDTO;
import com.Enoca.Task.dto.CartTempDTO;
import com.Enoca.Task.dto.ProductDTO;
import com.Enoca.Task.entity.Cart;
import com.Enoca.Task.entity.CartTemp;
import com.Enoca.Task.entity.Product;
import com.Enoca.Task.repository.CartRepository;
import com.Enoca.Task.repository.CartTempRepository;
import com.Enoca.Task.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartTempRepository cartTempRepository;
    @Autowired
    private CartTempService cartService;
    @Autowired
    private ProductRepository productRepository;

    public CartDTO getCart(int customerId){
        Cart cart = cartRepository.findByCustomerId(customerId);
        CartDTO cartDTO=new CartDTO();
        cartDTO.setCustomerId(cart.getCustomerId());
        cartDTO.setCartTempId(cart.getCartTempId());
        List<CartTemp> cartTemp = cartService.getCartTemp(cart.getCartTempId());
        List<CartTempDTO> cartTempDtos=new ArrayList<>();
        CartTempDTO model=new CartTempDTO();
        double total=0;
        ProductDTO productDTO=new ProductDTO();
        for (CartTemp c:cartTemp){
            model=new CartTempDTO();
            model.setAdetPrice(c.getAdetPrice());
            model.setQuantity(c.getQuantity());
            model.setCustomerId(c.getCustomerId());
            model.setProductId(c.getProductId());
            Optional<Product> byId = productRepository.findById(c.getProductId());
            productDTO=new ProductDTO();
            productDTO.setName(byId.get().getName());
            productDTO.setPrice(byId.get().getPrice());
            model.setProductDTO(productDTO);
            total+=c.getAdetPrice();
            cartTempDtos.add(model);
        }
        cartDTO.setTotalPrice(total);
        cartDTO.setCartTemps(cartTempDtos);
        return cartDTO;
    }

    public CartDTO updateCart(CartDTO cartDTO){
        List<CartTempDTO> cartTempsDto = cartDTO.getCartTemps();

        Cart byCustomerId = cartRepository.findByCustomerId(cartDTO.getCustomerId());
        AtomicReference<Double> totalPrice= new AtomicReference<>((double) 0);
        CartTemp cartTemp;
       for(CartTempDTO f:cartTempsDto) {
            cartTemp=new CartTemp();
            cartTemp.setCartId(byCustomerId.getCartTempId());
            cartTemp.setQuantity(f.getQuantity());
            cartTemp.setCustomerId(f.getCustomerId());
            cartTempRepository.save(cartTemp);
           totalPrice.set(f.getAdetPrice() + totalPrice.get());
       }
        cartDTO.setTotalPrice(totalPrice.get());
        return cartDTO;
    }
    public void emptyCart(int customerId){
        Cart cart=cartRepository.findByCustomerId(customerId);

        List<CartTemp> byCartId = cartTempRepository.findByCartId(cart.getCartTempId());

        byCartId.stream().forEach(f->{
            cartTempRepository.delete(f);
        });
    }

    public void removeProductFromCart(int cartTempId,int cartId){
        CartTemp cartTemp=new CartTemp();
        cartTemp.setId(cartTempId);
        cartTemp.setCartId(cartId);
        cartTempRepository.delete(cartTemp);
    }

    public CartDTO addProductToCart(CartTempDTO cartTempDTO){
        CartDTO response=new CartDTO();

        Cart byCustomerId = cartRepository.findByCustomerId(cartTempDTO.getCustomerId());

        Product product = productRepository.findById(cartTempDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));
        if (product.getStock() < cartTempDTO.getQuantity()) {
            throw new RuntimeException("Yeterli stok bulunmuyor: " + product.getName());
        }


        product.setStock(product.getStock() - cartTempDTO.getQuantity());
        productRepository.save(product);

        CartTemp model=new CartTemp();
        model.setCartId(byCustomerId.getId());
        model.setProductId(cartTempDTO.getProductId());
        model.setQuantity(cartTempDTO.getQuantity());
        model.setAdetPrice(cartTempDTO.getAdetPrice());
        model.setCustomerId(cartTempDTO.getCustomerId());
        cartTempRepository.save(model);

        List<CartTemp> byCartId = cartTempRepository.findByCartId(byCustomerId.getCartTempId());
        List<CartTempDTO> cartTempDTOList=new ArrayList<>();
        CartTempDTO cartTempDto1=new CartTempDTO();
        double totalPrice=0;
        for(CartTemp ct:byCartId){
            cartTempDto1=new CartTempDTO();
            cartTempDto1.setProductId(ct.getProductId());
            cartTempDto1.setQuantity(ct.getQuantity());
            cartTempDto1.setCustomerId(ct.getCustomerId());
            cartTempDto1.setAdetPrice(ct.getAdetPrice());
            totalPrice+=ct.getAdetPrice();
            cartTempDTOList.add(cartTempDto1);
        }
        response.setCartTemps(cartTempDTOList);
        response.setTotalPrice(totalPrice);
        return response;
    }
}
