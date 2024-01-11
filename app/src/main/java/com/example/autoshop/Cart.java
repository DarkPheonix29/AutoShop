package com.example.autoshop;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Products> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addItem(Products product) {
        items.add(product);
    }

    public List<Products> getItems() {
        return items;
    }
}
