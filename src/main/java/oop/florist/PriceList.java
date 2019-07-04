package oop.florist;

import java.util.HashMap;
import java.util.Map;

// singleton
//
public class PriceList {

    private static PriceList uniqueInstace;
    private Map<String, Double> prices;



    private PriceList() {
        prices =new HashMap<>();
    }

    public double getPrice(String flowerName) {
        if (prices.containsKey(flowerName)) {
            return prices.get(flowerName);
        }
        return -1;
    }


    public static PriceList getInstance() {
        if (uniqueInstace == null) {
            uniqueInstace = new PriceList();
        }
        return uniqueInstace;
    }

    public void put(String flowerName, double price) {
        prices.put(flowerName,price);
    }


}
