package com.inventory.inventory_management.service;

import com.inventory.inventory_management.Repository.ProductRepository;
import com.inventory.inventory_management.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(Product product){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public String deleteProduct(Long id){
        productRepository.deleteById(id);
        return "Product Deleted Successfully";
    }
}
