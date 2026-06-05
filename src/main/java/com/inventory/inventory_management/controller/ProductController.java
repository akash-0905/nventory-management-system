package com.inventory.inventory_management.controller;

import com.inventory.inventory_management.dto.ProductRequestDTO;
import com.inventory.inventory_management.dto.ProductResponseDTO;
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
    public ResponseEntity<ProductResponseDTO> saveProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO){

        ProductResponseDTO savedProduct = productService.saveProduct(productRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){

        List<ProductResponseDTO> gettingAllProduct = productService.getAllProducts();
        return ResponseEntity.ok(gettingAllProduct);
    }

    @GetMapping("/{id}")
        public ResponseEntity<ProductResponseDTO> getProductByID(@PathVariable Long id){

            ProductResponseDTO getById = productService.getProductById(id);
            return ResponseEntity.ok(getById);
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){

        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO updateProduct){

        ProductResponseDTO updatedProduct = productService.updateProduct(id, updateProduct);
        return ResponseEntity.ok(updatedProduct);
    }
}
