package com.example.demo.entities;

public class Product {
    private Long id;
    private String title;
    private int price;

    public long getId() {
        return id;
    }

    public Product() {
    }

    public Product(long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
