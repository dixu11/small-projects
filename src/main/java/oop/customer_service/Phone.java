package oop.customer_service;/*A oop.customer_service.Phone class. It will Inherit from the oop.customer_service.Product class.
        It will have attributes for make (Apple, Motorola, Samsung, etc), model (iPhone 6, Moto X, Galaxy S5, etc),
        and storage space (in gigabytes). Write getters and setters and Overload the print() method that's inherited
        from the oop.customer_service.Product class. Make use of the super.print() call.*/

public class Phone extends Product {
    private String company;
    private String model;
    private double storage;

    public Phone(String name, String description, double price, String company, String model, double storage) {
        super(name, description, price);
        this.company = company;
        this.model = model;
        this.storage = storage;
    }

    public void print() {
        super.print();
        System.out.printf("Company: %s\n",company);
        System.out.printf("Model: %s\n",model);
        System.out.printf("Storage: %.1fGB\n",storage);
    }

    public String getCompany() {
        return company;
    }

    public Phone setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Phone setModel(String model) {
        this.model = model;
        return this;
    }

    public double getStorage() {
        return storage;
    }

    public Phone setStorage(double storage) {
        this.storage = storage;
        return this;
    }

    @Override
    public String toString() {
        return model;
    }
}
