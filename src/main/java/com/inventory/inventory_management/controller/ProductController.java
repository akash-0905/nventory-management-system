package com.inventory.inventory_management.controller;

import com.inventory.inventory_management.entity.Product;
import com.inventory.inventory_management.service.ProductService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product){

        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){

        List<Product> gettingAllProduct = productService.getAllProducts();
        return ResponseEntity.ok(gettingAllProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable Long id){

        Product getById = productService.getProductById(id);
        return ResponseEntity.ok(getById);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product updateProduct){

        Product updatedProduct = productService.updateProduct(id, updateProduct);
        return ResponseEntity.ok(updatedProduct);
    }
}
