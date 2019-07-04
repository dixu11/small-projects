package oop.customer_service;

import java.util.ArrayList;
import java.util.List;

/*Write a oop.customer_service.Customer class that has attributes name, address. A Customer object also has an ArrayList of Orders.*/

public class Customer {
    private String name;
    private String adress;
    private List<Order> orders;

    public Customer(String name, String adress) {
        this.name = name;
        this.adress = adress;
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void printOrders() {
        int counter = 1;
        for (Order order : orders) {
            System.out.println("oop.customer_service.Order: " + counter);
            counter++;
            order.printProducts();
            System.out.println("-----------------");
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public String getName() {
        return name;
    }
}
