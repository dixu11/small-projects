package oop.florist;

// malo kodu!
public class Box extends FlowerContainer {
    private static final String DEFAULT_NAME = "Pudełko";

    public Box(Customer owner) {
        super(owner,DEFAULT_NAME);
    }
}
