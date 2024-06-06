package org.example.clase;

public class Product {
    private String name;
    private double price;
    private int stockQuantity;

    public Product(String name, double price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void sell(int quantity) {
        if (stockQuantity >= quantity) {
            stockQuantity -= quantity;
        } else {
            System.out.println("Insufficient stock!");
        }
    }

    public void restock(int quantity) {
        stockQuantity += quantity;
    }

    public double calculateTotalPrice(int quantity) {
        return price * quantity;
    }
}
