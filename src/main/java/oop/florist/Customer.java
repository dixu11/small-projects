package oop.florist;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;
    private double money;
    private ShoppingCart cart;


    public Customer(String name, double money) {
        this.name = name;
        this.money = money;
        cart = new ShoppingCart(this);
    }


    public void get(Flower flower) {
        cart.addFlower(flower);
    }

    public ShoppingCart getShoppingCart() {
        return cart;
    }


    public void pack(Box box) {
        box.addFlowers(cart.getFlowers());
        cart.removeAllFlowers();
    }


    public void pay() {
        List<Flower> flowers = cart.getFlowers();
        PriceList prices = PriceList.getInstance();
        List<Flower> flowersToRemove = new ArrayList<>();
        for (Flower flower : flowers) {
            double totalPrice = prices.getPrice(flower.getFlowerName()) * flower.getQuantity();
            if (totalPrice > 0 && money >= totalPrice) {
                money -= totalPrice;
            } else {
                flowersToRemove.add(flower);
            }
        }
        if (!flowersToRemove.isEmpty()) {
            for (Flower flower : flowersToRemove) {
                flowers.remove(flower);
            }
        }
        // jesli wszystko sie zgadza to placimy za kwiaty i beda mogly isc do boxa
        // jesli sa kwiaty bez ceny to wywalamy te kwiaty bez oplaty za nie
        // jesli brak pieniedzy to pomijamy kwiaty na ktore zabraklo
    }

    public double getCash() {
        return money;
    }

    @Override
    public String toString() {
        return name;
    }
}
