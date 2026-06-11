package com.inventory.inventory_management.service;

import com.inventory.inventory_management.Repository.ProductRepository;
import com.inventory.inventory_management.dto.ProductRequestDTO;
import com.inventory.inventory_management.dto.ProductResponseDTO;
import com.inventory.inventory_management.entity.Product;
import com.inventory.inventory_management.exception.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDTO saveProduct(ProductRequestDTO dto){

        log.info("Saving product with name: {}", dto.getName());

        Product product = new Product();

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());

        Product savedProduct = productRepository.save(product);
        log.info("Product saved successfully with id: {}", savedProduct.getId());

        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setId(savedProduct.getId());
        responseDTO.setName(savedProduct.getName());
        responseDTO.setPrice(savedProduct.getPrice());

        return responseDTO;
    }

    public List<ProductResponseDTO> getAllProducts(){

        log.info("Fetching all Products");
        List<Product> products = productRepository.findAll();

        if(products.isEmpty()){
            log.warn("No products found in database");
            throw new ProductNotFoundException("No Products Available");
        }
        log.info("Total Product fetched: {}", products.size());
            List<ProductResponseDTO> responseList = new ArrayList<>();
        for(Product product : products) {
            ProductResponseDTO dto = new ProductResponseDTO();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setPrice(product.getPrice());

            responseList.add(dto);
        }
        return responseList;
    }

    public ProductResponseDTO getProductById(Long id){

        log.info("Fetching Product by id: {}", id);
        Product product = productRepository.findById(id).orElseThrow(() -> {
                    log.error("Product not found with id: {}", id);
                          return new ProductNotFoundException("Product not found");
                }
        );
        log.info("Product Found by id: {}", id);
        ProductResponseDTO dto = new ProductResponseDTO();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());

        return dto;
    }

    public String deleteProduct(Long id){

        log.info("Deleting product with id: {}", id);
        Product product = productRepository.findById(id).orElseThrow(() -> {
                    log.error("Product not found with id: {}", id);

                    return new ProductNotFoundException("Product not found");
                });
        productRepository.delete(product);
        log.info("Product deleted successfully with id: {}", id);
        return "Product Deleted Successfully";
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto){

        log.info("Update Product By ID: {}", id);
        Product existingProduct = productRepository.findById(id).orElseThrow(()-> {
                    log.error("Existing Product id is not Found: {}", id);
                    return new ProductNotFoundException("Product not Found");
                });

        log.info("Product found. Updating details for id: {}", id);
        existingProduct.setName(dto.getName());
        existingProduct.setQuantity(dto.getQuantity());
        existingProduct.setPrice(dto.getPrice());

        Product updatedProduct = productRepository.save(existingProduct);
        log.info("Product updated successfully with id: {}", id);

        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setId(updatedProduct.getId());
        responseDTO.setName(updatedProduct.getName());
        responseDTO.setPrice(updatedProduct.getPrice());

        return responseDTO;
    }
}
