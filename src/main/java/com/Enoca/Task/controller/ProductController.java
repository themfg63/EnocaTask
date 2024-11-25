package com.Enoca.Task.controller;

import com.Enoca.Task.dto.ProductDTO;
import com.Enoca.Task.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.Product.PRODUCT)
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(ApiPaths.Product.GET_PRODUCT)
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") int productId){
       return ResponseEntity.ok(productService.getProduct(productId));
    }

    @GetMapping(ApiPaths.Product.GET_ALL_PRODUCT)
    public ResponseEntity<List<ProductDTO>> getAllProduct(){
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @PostMapping(ApiPaths.Product.CREATE)
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        ProductDTO createdProduct =productService.createProduct(productDTO);
        return new ResponseEntity<>(createdProduct,HttpStatus.CREATED);
    }
    @PutMapping(ApiPaths.Product.UPDATE)
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("productId") int productId,@RequestBody ProductDTO productDTO){
        ProductDTO updateProduct =productService.updateProduct(productId,productDTO);
        return new ResponseEntity<>(updateProduct,HttpStatus.OK);
    }

    @DeleteMapping(ApiPaths.Product.DELETE)
    public void deleteProduct(@PathVariable("productId") int productId){
        productService.deleteProduct(productId);
    }
}
