package recrutation.zad2;

import java.util.LinkedList;
import java.util.List;

public class RocketfuelRefilingService {

    private CSVReader reader;
    private double fuelNeeded;
    private List<Tap> taps;

    public RocketfuelRefilingService(CSVReader reader) {
        this.reader = reader;
        readData();
        validateData();
    }


    private void readData() {
        fuelNeeded = reader.readFuelNeeded();
        taps = reader.buildTaps();
    }

    private void prepareTaps() {
        taps = new LinkedList<>();

    }

    private void validateData() {
        if (fuelNeeded > 100_000_000) {
            Wilk.exitWithError();
        }
    }

    public void printAllStartingData() {
        System.out.printf("Fuel needed: %.5f\n", fuelNeeded);
        for (Tap tap : taps) {
            System.out.println(tap);
        }
    }
}
