package com.example.autoshop;

public class Products {

    String name;

    int id;

    String description;

    String price;



    public Products(String name, int id, String description, String price) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.price = price;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
