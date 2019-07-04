package oop.florist;

import java.util.ArrayList;
import java.util.List;

public abstract class FlowerContainer {
    private Customer owner;
    protected List<Flower> flowers;
    private final String name;

    public FlowerContainer(Customer owner, String name) {
        this.owner = owner;
        this.name = name;
        flowers = new ArrayList<>();
    }

    public void addFlower(Flower flower) {
        flowers.add(flower);
    }


    public List<Flower> getFlowers() {
        return flowers;
    }

    public List<Flower> getFlowersByColor(String color) {
        List<Flower> result = new ArrayList<>();
        for (Flower flower : flowers) {
            if (flower.getColor().equalsIgnoreCase(color)) {
                result.add(flower);
            }
        }
        return result;
    }


    public void removeAllFlowers() {
        flowers.clear();
    }


    public void addFlowers(List<Flower> flowers) {
        this.flowers.addAll(flowers);
    }

    @Override
    public String toString() {
        StringBuilder shopingCartDescription = new StringBuilder();
        shopingCartDescription
                .append(name)
                .append(" właściciel ")
                .append(owner)
                .append("\n");
        for (Flower flower : flowers) {
            shopingCartDescription.append(flower)
                    .append("\n");
        }
        shopingCartDescription.setLength(shopingCartDescription.length() - 1); // zmniejszam o 1 zeby usunac ostatni enter
        if (flowers.isEmpty()) {
            shopingCartDescription.append(" -- pusto");
        }
        return shopingCartDescription.toString();
    }
}
