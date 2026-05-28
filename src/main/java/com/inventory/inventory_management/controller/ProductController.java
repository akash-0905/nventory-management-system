package com.inventory.inventory_management.controller;

import com.inventory.inventory_management.Repository.ProductRepository;
import com.inventory.inventory_management.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    @GetMapping
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductByID(@PathVariable Long id){
        return productRepository.findById(id).orElse(null);
    }



}
