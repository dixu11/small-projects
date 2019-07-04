package oop.florist;

public class Lilac extends Flower {
    public static final String FLOWER_NAME = "bez";
    public static final String FLOWER_COLOR = "bialy";

    public Lilac(int quantity) {
        super(quantity, FLOWER_NAME, FLOWER_COLOR);
    }
}
