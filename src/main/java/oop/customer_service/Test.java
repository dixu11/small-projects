package oop.customer_service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        //use main in TestMenu!

        Phone phone1 = new Phone("Phone",
                "brand new phone",
                3999.99,
                "Samsung",
                "Galaxy8",
                16
                );
        Phone phone2 = new Phone("oop.customer_service.Phone",
                "brand new phone",
                4999.99,
                "Apple",
                "iPhone",
                32
        );
        ProductDB database = new ProductDB();
        database.add(phone1);
        database.add(phone2);
        Customer mary = new Customer("Mary",
                "Mickiewicza 3/3 70-391 Warszawa Poland");
        Order order = new Order();
        order.add(phone1, 1);
        order.add(phone2, 12);
        mary.addOrder(order);


    }
}
