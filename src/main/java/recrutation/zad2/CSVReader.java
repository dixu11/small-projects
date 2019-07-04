package recrutation.zad2;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {


    private List<String> lines;

    public CSVReader() {
        readCSVFromInputStream();
        verifyRecordCount();
    }

    private void readCSVFromInputStream() {
        Scanner scanner = new Scanner(System.in);
        lines = new LinkedList<>();

        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

    }

    private void verifyRecordCount() {
        if (lines.size() < 2) {
            Wilk.exitWithError();
        }
    }

    public double readFuelNeeded() {
        if (!isDigit(lines.get(0))) {
            Wilk.exitWithError();
        }
        return Double.parseDouble(lines.get(0));
    }

    public List<Tap> buildTaps() {
        List<Tap> taps = new LinkedList<>();

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] tapDataString = line.split("\\s+");

            if (tapDataString.length < 2) {
                Wilk.exitWithError();
            }

            String flowSpeed = tapDataString[0];
            String temperature = tapDataString[1];

            if (!isDigit(flowSpeed) || !isDigit(temperature)) {
                Wilk.exitWithError();
            }

            double flowSpedDig = Double.parseDouble(flowSpeed);
            double temperatureDig = Double.parseDouble(temperature);

            taps.add(new Tap(flowSpedDig, temperatureDig));
        }
        return taps;
    }

    private boolean isDigit(String content) {
        return content.matches("\\d+\\.\\d+");
    }


}
