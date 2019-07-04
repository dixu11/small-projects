package oop.florist;

// malo kodu!
public class ShoppingCart extends FlowerContainer {
    private static final String DEFAULT_NAME = "Wózek";

    public ShoppingCart(Customer owner) {
        super(owner, DEFAULT_NAME);
    }
}
