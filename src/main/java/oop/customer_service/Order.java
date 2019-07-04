package oop.customer_service;

import java.util.*;

//Write an Order class which manages an ArrayList of Products.
public class Order {
    private List<Product> orderedProducts;

    public Order() {
        orderedProducts = new ArrayList<>();
    }

    public void add(Product product, int amound) {
        for (int i = 0; i < amound; i++) {
            orderedProducts.add(product);
        }
    }

    public void printProducts() {
        Map<Product,Integer> products = new HashMap<>();
        for (Product orderedProduct : orderedProducts) {
            if (!products.containsKey(orderedProduct)) {
                products.put(orderedProduct, 1);
            } else {
                products.put(orderedProduct, products.get(orderedProduct) + 1);
            }
        }
        for (Product product : products.keySet()) {
            System.out.println(products.get(product) + " " + product.toString());
        }
    }

    public List<Product> getOrderedProducts() {
        return orderedProducts;
    }
}
