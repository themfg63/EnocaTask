package com.Enoca.Task.service;

import com.Enoca.Task.entity.Product;
import com.Enoca.Task.entity.Stock;
import com.Enoca.Task.dto.ProductDTO;
import com.Enoca.Task.repository.ProductRepository;
import com.Enoca.Task.repository.StockRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private  ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;


    public ProductDTO getProduct(int productId){
        Optional<Product> byId = productRepository.findById(productId);
        ProductDTO productDTO=new ProductDTO();
        if (byId.isPresent()){
            productDTO.setName(byId.get().getName());
            productDTO.setPrice(byId.get().getPrice());
            productDTO.setStock(byId.get().getStock());
        }
        return productDTO;
    }

    public List<ProductDTO> getAllProduct(){
        List<Product> allProduct = productRepository.findAll();
        List<ProductDTO> productList= new ArrayList<>();

        if (!ObjectUtils.isEmpty(allProduct)){
            for(Product pr:allProduct){
                ProductDTO productDTO = new ProductDTO();
                productDTO.setName(pr.getName());
                productDTO.setPrice(pr.getPrice());
                productDTO.setStock(pr.getStock());
                productList.add(productDTO);
            }

        }
        return productList;
    }

    @Transactional
    public ProductDTO createProduct(ProductDTO productDTO){
        Stock stock=new Stock();
        stock.setProductName(productDTO.getName());
        stock.setQuantity(productDTO.getStock());
        Stock save = stockRepository.save(stock);

        Product product= new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(save.getId());
        productRepository.save(product);
        return productDTO;

    }
    @Transactional
    public ProductDTO updateProduct(int productId,ProductDTO productDTO){

        Optional<Product> product= productRepository.findById(productId);
        if (product.isPresent()){
            product.get().setName(productDTO.getName());
            product.get().setPrice(productDTO.getPrice());
            productRepository.save(product.get());
        }


        Optional<Stock> stock=stockRepository.findById(product.get().getStock());
        if(stock.isPresent()){
            stock.get().setProductName(productDTO.getName());
            stock.get().setQuantity(productDTO.getStock());
             stockRepository.save(stock.get());
        }

        return productDTO;
    }
    @Transactional
    public void deleteProduct(int productId){
        Optional<Product> product= productRepository.findById(productId);
        productRepository.deleteById(productId);

        stockRepository.deleteById(product.get().getStock());
    }




}
