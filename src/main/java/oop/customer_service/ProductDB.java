package oop.customer_service;/*Write a oop.customer_service.ProductDB class which manages an ArrayList of Products. The class provides methods to add, remove and find a product.
        The find method returns a product with a matching productID.*/

import java.util.ArrayList;
import java.util.List;

public class ProductDB {
    private List<Product> products;

    public ProductDB() {
        products = new ArrayList<>();
    }

    public void add(Product product) {
        products.add(product);
    }

    public Product find(int ID) {
        for (Product product : products) {
            if (product.getProductID() == ID) {
                return product;
            }
        }
        System.out.println("No product with ID of " + ID + " found.");
        return null;
    }

    public void printAll() {
        for (Product product : products) {
           product.print();
        }
    }
}
