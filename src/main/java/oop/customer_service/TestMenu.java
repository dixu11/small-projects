package oop.customer_service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestMenu {
/*
    Provide a menu in your oop.customer_service.Test class which has the following options:
            1. Create a new phone.
2. Create a new Customer.
3. Search for a product by supplying the productid.
            4. Display all products in the database.
            5. Allow a customer to order some products.
6. Display all orders that a customer has made and all the products that are in a given order.
            7. Display all orders for a given product by supplying the productid.
8. Quit
*/

    private static Scanner scanner = new Scanner(System.in);
    private static List<Customer> customers = new ArrayList<>();
    private static ProductDB database = new ProductDB();

    public static void main(String[] args) {
        Phone phone1 = new Phone("oop.customer_service.Phone",
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

        database.add(phone1);
        database.add(phone2);
        Customer mary = new Customer("Mary",
                "Mickiewicza 3/3 70-391 Warszawa Poland");
        customers.add(mary);
        Order order = new Order();
        order.add(phone1, 1);
        order.add(phone2, 12);
        mary.addOrder(order);


        String options = "1. Create a new phone.\n" +
                "2. Create a new oop.customer_service.Customer.\n" +
                "3. Search for a product by supplying the productid.\n" +
                "4. Display all products in the database.\n" +
                "5. Allow a customer to order some products.\n" +
                "6. Display all orders that a customer has made and all the products that are in a given order.\n" +
                "7. Display all orders for a given product by supplying the product id.\n" +
                "8. Quit";
        boolean programNotEnded = true;
        while (programNotEnded) {
            boolean isInputIncorrect = true;
            int chosenOption = 0;
            while (isInputIncorrect) {
                chosenOption = 0;
                System.out.println(options);
                System.out.println("\nWhich one do you choose? ");
                if (scanner.hasNextInt()) {
                    chosenOption = scanner.nextInt();
                    scanner.nextLine();
                    if (chosenOption > 0 && chosenOption < 9) {
                        isInputIncorrect = false;
                    }
                } else {
                    scanner.nextLine();
                    System.out.println("ERROR: Choose option from 1 to 8 using one number value\n");
                    System.out.println("Press ENTER to continue");
                    scanner.nextLine();
                }
            }
            switch (chosenOption) {
                case 1:
                    System.out.println("Option: Create a new phone - selected");
                    database.add(newPhoneOption());
                    System.out.println("New phone created and added to database!\n");
                    break;
                case 2:
                    System.out.println("Option: Create a new customer - selected");
                    customers.add(newCustomerOption());
                    System.out.println("New customer created and added to customers!\n");
                    break;
                case 3:
                    System.out.println("Search for a product by supplying the productid - selected");
                    System.out.println("Enter ID:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Product product = database.find(id);
                    if (product != null) {
                        System.out.println("Your product properties are:");
                        product.print();
                    } else {
                        System.out.println("oop.customer_service.Product not found");
                    }
                    System.out.println("Press ENTER to continue");
                    scanner.nextLine();
                    break;
                case 4:
                    System.out.println("Display all products in the database - selected");
                    database.printAll();
                    System.out.println("Press ENTER to continue");
                    scanner.nextLine();
                    break;
                case 5:
                    System.out.println("5. Allow a customer to order some products - selected");
                    System.out.println("Enter the customer’s name:");
                    String customerName = scanner.nextLine();
                    Order newOrder = newCustomOrder();
                    boolean customerNotFound = true;
                    for (Customer customer : customers) {
                        if (customer.getName().equals(customerName)) {
                            customer.addOrder(newOrder);
                            customerNotFound = false;
                        }
                    }
                    if (customerNotFound) {
                        System.out.println("Cant add order, customer not found. Please add new customer first.");
                    }
                    break;
                case 6:
                    System.out.println("Display all orders that a customer has made and all the products that are in a given order - selected");
                    printCustomerOrderOption();
                    break;
                case 7:
                    System.out.println("Display all orders for a given product by supplying the product id - selected");
                    displayOrdersByProductIdOption();
                    break;
                case 8:
                    System.out.println("Bye bye!");
                    programNotEnded = false;
                    break;
            }
        }
    }


    public static Phone newPhoneOption() {
        System.out.println("Please enter discription:");
        String discription = scanner.nextLine();
        System.out.println("Please enter price:");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Please enter company:");
        String company = scanner.nextLine();
        System.out.println("Please enter model:");
        String model = scanner.nextLine();
        System.out.println("Please enter storage in GB:");
        double storage = scanner.nextDouble();
        scanner.nextLine();
        Phone phone = new Phone("oop.customer_service.Phone", discription, price, company, model, storage);
        System.out.println("Your phone created! It has id of: " + phone.getProductID());
        System.out.println("Press ENTER to continue");
        scanner.nextLine();
        return phone;
    }

    private static Customer newCustomerOption() {
        System.out.println("Please enter customer's name:");
        String name = scanner.nextLine();
        System.out.println("Please enter adress:");
        String adress = scanner.nextLine();
        Customer newCustomer = new Customer(name, adress);
        System.out.println("Your oop.customer_service.Customer created: " + name);
        return newCustomer;
    }

    private static Order newCustomOrder() {
        Order order = new Order();
        String input = "";
        boolean putingValues = true;
        while (putingValues) {
            System.out.println("Enter a product id and a quantity. Enter -1 to finish");
            input = scanner.nextLine();
            if (!input.equals("-1")) {
                String[] idAndQuantity = input.split(" ");
                int[] idAndQuantityInt = {Integer.parseInt(idAndQuantity[0]), Integer.parseInt(idAndQuantity[1])};
                Product product = database.find(idAndQuantityInt[0]);
                int quantity = idAndQuantityInt[1];
                if (product != null) {
                    System.out.printf("You ordered %dx \n", quantity);
                    product.print();
                    order.add(product, quantity);
                }
            } else {
                putingValues = false;
            }
        }
        return order;
    }

    private static void printCustomerOrderOption() {
        System.out.println("Enter the customer’s name:");
        String customerName = scanner.nextLine();
        Customer customer = null;
        for (Customer customerTemp : customers) {
            if (customerTemp.getName().equals(customerName)) {
                customer = customerTemp;
            }
        }
        if (customer != null) {
            customer.printOrders();
        } else {
            System.out.println("oop.customer_service.Customer not found");
        }
        System.out.println("Press ENTER to continue");
        scanner.nextLine();
    }

    private static void displayOrdersByProductIdOption() {
        System.out.println("Please enter product ID:");
        int productID = scanner.nextInt();
        scanner.nextLine();
        int orderNr = 1;
        for (Customer customer : customers) {
            for (Order order : customer.getOrders()) {
                for (Product product : order.getOrderedProducts()) {
                    if (product.getProductID() == productID) {
                        System.out.printf("in order: %d\n" +
                                "ordered by customer: %s\n",orderNr, customer.getName());
                        System.out.println("Found product: ");
                        product.print();
                        System.out.println("------------");

                    }
                }
                orderNr++;
            }
        }
    }
}