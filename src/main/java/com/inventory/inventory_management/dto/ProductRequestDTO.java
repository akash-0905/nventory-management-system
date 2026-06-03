package com.inventory.inventory_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ProductRequestDTO {

    @NotBlank(message = "This Field Shouldn't Blank")
    private String name;

    @Positive(message = "Quantity shouldn't be less than 0")
    private int Quantity;

    @Positive(message = "Price shouldn't be ess than 0")
    private int Price;

    public String getName(){
        return name;
    }
    public int getQuantity(){
        return Quantity;
    }
    public int getPrice(){
        return Price;
    }
}
