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

        List<Product> products = productRepository.findAll();

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

        ProductResponseDTO dto = new ProductResponseDTO();
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Id" + id + "Product not Found"));

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());

        return dto;
    }

    public String deleteProduct(Long id){
        productRepository.deleteById(id);
        return "Product Deleted Successfully";
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto){

        Product existingProduct = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product not Found with id :" + id));

        existingProduct.setName(dto.getName());
        existingProduct.setQuantity(dto.getQuantity());
        existingProduct.setPrice(dto.getPrice());

        Product updatedProduct = productRepository.save(existingProduct);

        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setId(updatedProduct.getId());
        responseDTO.setName(updatedProduct.getName());
        responseDTO.setPrice(updatedProduct.getPrice());

        return responseDTO;
    }
}
