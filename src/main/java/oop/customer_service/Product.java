package oop.customer_service;/*A oop.customer_service.Product class. This will be a generic class for Products that a company may sell. It will have
        attributes for the following: name, description, price and productID. Use appropriate data types.
        Write getters and setters for all attributes. Write a method called print() that prints the oop.customer_service.Product
        information to the screen. A unique productID is assigned to the product when it is created.
        You can use a static int to achieve this.*/


public class Product {
    private String name;
    private String description;
    private double price;
    private int productID;
    private static int nextID = 0;

    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
        productID = nextID;
        nextID++;
    }

    public void print() {
        System.out.printf("oop.customer_service.Product: %s\n",name);
        System.out.printf("Description: %s\n",description);
        System.out.printf("Price: %.2f\n",price);
        System.out.printf("ID: %d\n",productID);
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Product setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getProductID() {
        return productID;
    }

}
