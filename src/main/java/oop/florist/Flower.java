package oop.florist;

public abstract class Flower {
    private String flowerName;
    private int quantity;
    private String color;

    public Flower(int quantity, String flowerName,  String color) {
        this.quantity = quantity;
        this.flowerName = flowerName;
        this.color = color;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public String getColor() {
        return color;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        StringBuilder flowerDescription = new StringBuilder();

        double price = PriceList.getInstance().getPrice(flowerName);

        flowerDescription.append(flowerName)
                .append(", kolor: ")
                .append(color)
                .append(", ilość ")
                .append(quantity)
                .append(", cena ")
                .append(price);

        return flowerDescription.toString();
    }
}
