package oop.florist;

public class Rose extends Flower {
    public static final String FLOWER_NAME = "róża";
    public static final String FLOWER_COLOR = "czerwony";

    public Rose(int quantity) {
        super(quantity, FLOWER_NAME,FLOWER_COLOR);
    }
}
