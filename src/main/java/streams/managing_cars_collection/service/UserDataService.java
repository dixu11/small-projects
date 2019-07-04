package streams.managing_cars_collection.service;

import streams.managing_cars_collection.exceptions.MyException;

import java.util.Scanner;

public class UserDataService {

    private Scanner sc = new Scanner(System.in);



    public int getInt(String message) {
        System.out.println(message);

        String text = sc.nextLine();
        if (!text.matches("^\\d+")) {
            throw new MyException("INT VALUE IS NOT CORRECT: " + text);
        }

        return Integer.parseInt(text);
    }

    public double getDouble(String message){

        System.out.println(message);

        String text = sc.nextLine();
        if (!text.matches("^\\d+(\\.\\d+)?")) {
            throw new MyException("DOUBLE VALUE IS NOT CORRECT: " + text);
        }

        return Double.parseDouble(text);
    }

    public void close() {
        if (sc != null) {
            sc.close();
            sc = null;
        }
    }

}
