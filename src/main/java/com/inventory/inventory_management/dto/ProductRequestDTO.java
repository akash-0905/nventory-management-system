package com.inventory.inventory_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ProductRequestDTO {

    @NotBlank(message = "This Field Shouldn't Blank")
    private String name;

    @Positive(message = "Quantity shouldn't be less than 0")
    private int quantity;

    @Positive(message = "Price shouldn't be less than 0")
    private int price;

    public String getName(){
        return name;
    }
    public int getQuantity(){
        return quantity;
    }
    public int getPrice(){
        return price;
    }
}
