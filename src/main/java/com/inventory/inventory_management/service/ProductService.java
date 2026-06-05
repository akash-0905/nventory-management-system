package com.inventory.inventory_management.service;

import com.inventory.inventory_management.Repository.ProductRepository;
import com.inventory.inventory_management.dto.ProductRequestDTO;
import com.inventory.inventory_management.dto.ProductResponseDTO;
import com.inventory.inventory_management.entity.Product;
import com.inventory.inventory_management.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDTO saveProduct(ProductRequestDTO dto){

        Product product = new Product();

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());

        Product savedProduct = productRepository.save(product);

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
