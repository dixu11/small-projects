package oop.customer_service;/*A oop.customer_service.TV class. It will Inherit from the Product class.It will have attributes for make, screen size, type (LCD, LED, Plasma)
        and whether or not it is 3D capable. Write getters and setters and overload the print() method.*/

public class TV extends Product {
    private double screenSize;
    private String type;
    private  boolean is3DCapable;

    public TV(String name, String description, double price, double screenSize, String type, boolean capable3D) {
        super(name, description, price);
        this.screenSize = screenSize;
        this.type = type;
        this.is3DCapable = capable3D;
    }

    @Override
    public void print() {
        super.print();
        System.out.printf("Screen: %.1f'\n",screenSize);
        System.out.printf("Screen type: %s\n",type);
        System.out.printf("Is 3D capable: %b\n", is3DCapable);
    }

    public double getScreenSize() {
        return screenSize;
    }

    public TV setScreenSize(double screenSize) {
        this.screenSize = screenSize;
        return this;
    }

    public String getType() {
        return type;
    }

    public TV setType(String type) {
        this.type = type;
        return this;
    }

    public boolean isIs3DCapable() {
        return is3DCapable;
    }

    public TV setIs3DCapable(boolean is3DCapable) {
        this.is3DCapable = is3DCapable;
        return this;
    }
}
