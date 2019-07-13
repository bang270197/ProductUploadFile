package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class Product {
    private int id;
    private String name;
    private double price;
    private String description;
    private String avatar;
    private MultipartFile images;

    public Product() {
    }

    public Product(int id, String name, double price, String description, String avatar) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.avatar = avatar;
    }

    public Product(int id, String name, double price, String description, MultipartFile images) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public MultipartFile getImages() {
        return images;
    }

    public void setImages(MultipartFile images) {
        this.images = images;
    }


}
