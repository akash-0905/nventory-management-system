package com.inventory.inventory_management.service;

import com.inventory.inventory_management.Repository.ProductRepository;
import com.inventory.inventory_management.entity.Product;
import com.inventory.inventory_management.exception.ProductNotFoundException;
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

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Id" + id + "Product not Found"));
    }

    public String deleteProduct(Long id){
        productRepository.deleteById(id);
        return "Product Deleted Successfully";
    }

    public Product updateProduct(Long id, Product updateProduct){

        Product existingProduct = productRepository.findById(id).orElse(null);

        if(existingProduct != null) {
            existingProduct.setName(updateProduct.getName());
            existingProduct.setQuantity(updateProduct.getQuantity());
            existingProduct.setPrice(updateProduct.getPrice());


            Product savedProduct = productRepository.save(existingProduct);
            System.out.println(savedProduct);
            return savedProduct;
        }
        return null;
    }
}
