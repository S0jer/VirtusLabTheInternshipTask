package com.virtuslab.internship.restapi.controller;


import java.util.ArrayList;
import java.util.List;

public class BasketInput {
    private final List<String> products = new ArrayList<>();

    public void addProduct(String product){
        products.add(product);
    }

    public List<String> getProducts() {
        return products;
    }
}
