package streams.managing_cars_collection.service;

import streams.managing_cars_collection.exceptions.MyException;
import streams.managing_cars_collection.model.Car;
import streams.managing_cars_collection.model.Color;
import streams.managing_cars_collection.model.SortType;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenuService {

    private CarService carService;
    private UserDataService userDataService;


    public MenuService(String filename) {

        carService = new CarService(filename);
        userDataService = new UserDataService();
    }

    public void mainMenu() {
        while (true) {
            try {
                int option = 1;
                option = takeAnswerFromUser(9);
                switch (option) {
                    case 1:
                        option1();
                        break;
                    case 2:
                        option2();
                        break;
                    case 3:
                        dispalyResult(carService.calculateCarsWithTheSameColor());
                        break;
                    case 4:
                        dispalyResultOfAExpensiveCar(carService.theHighestPrieceOfModel());
                        break;
                    case 5:
                        carService.statisticsOfUsageOfCar();
                        break;
                    case 6:
                        dispalyResult(carService.najwiekszaCena());
                        break;
                    case 7:
                        dispalyResult(carService.sortingInAlphabeticalOrder());
                        break;
                    case 8:
                        dispalyResultTheSameComponents(carService.carsContainingTheSameComponents());
                        break;
                    case 9:
                        option9();
                        break;
                }
            } catch (MyException e) {
                System.out.println(e.getExceptionMessage());
            }
        }
    }


    public String displayOptions() {
       return   "Dostępne opcje: \n" +
        "1. Wyświetl wg kategorii \n" +
        "2. Wyświetl samochody z wysokim przebiegiem \n" +
        "3. Wyświetl ilość samochodów po kolorze \n" +
        "4. Wyświetl najdroższy samochód w każdym modelu \n" +
        "5. Wyświetl statystykę użytkowania aut \n" +
        "6. Wyświetl największą cenę auta \n" +
        "7. Wyświetl auta w kolejności alfabetycznej \n" +
        "8. Wyświetl auta zawierające te same komponenty \n";
    }


    public boolean option1a() {
        System.out.println("Wybierz sposób sortowania wpisując literę: r/m (rosnąco/malejąco)");
        Scanner scanner = new Scanner(System.in);
        boolean czyRosnacoBool;
        String userInput = scanner.nextLine();
        if (userInput.toLowerCase().equals("r")) {
            czyRosnacoBool = true;
        } else if (userInput.toLowerCase().equals("m")) {
            czyRosnacoBool = false;
        } else {
            throw new MyException("wpisano coś innego niż r lub m");
        }
        return czyRosnacoBool;
    }

    public SortType option1b() {
        Scanner scanner = new Scanner(System.in);
        String menu = "Wybierz kategorię sortowania wpisując odpowiadającą jej cyfrę: \n" +
        "1. Kolor \n" + "2. Nazwa modelu \n" + "3. Cena \n" +  "4. Przebieg ";
        int chosenOption = userDataService.getInt(menu);
        SortType chosenCategory;
        switch (chosenOption) {
            case 1:
                chosenCategory = SortType.COLOR;
                break;
            case 2:
                chosenCategory = SortType.MODEL;
                break;
            case 3:
                chosenCategory = SortType.PRICE;
                break;
            case 4:
                chosenCategory = SortType.MILEAGE;
                break;
            default:
                throw new MyException("Należy wybrać numer kategorii z dostępnej listy.");
        }
        return chosenCategory;
    }

    public void option1() {
      boolean czyRosnacoBool =  option1a();
       SortType chosenCategory = option1b();
        carService.sortingMethods(czyRosnacoBool,chosenCategory);
        dispalyResult(carService.sortingMethods(czyRosnacoBool,option1b()));    }

    public void option2() {

        double mileage = userDataService.getDouble("Podaj minimalny przebieg samochodów które chcesz zobaczyć:");
        List<Car> wynik = carService.minimalMileage(mileage);
        dispalyResult(wynik);
    }

    public void option9() {

        double a = userDataService.getDouble("Podaj pierwszą cenę z przedziału tj tę mniejszą:");

        double b = userDataService.getDouble("Podaj drugą cenę z przedziału tj tę większą:");

        if (a >= b) {
            throw new MyException("pierwsza podana cena musi być mniejsza!");
        }

        dispalyResult(carService.priceFromInterval(a, b));
    }

    public int takeAnswerFromUser(int nrOfOptions) {
       String menu = displayOptions();
        int optionSelected = userDataService.getInt(menu);
        if (optionSelected > nrOfOptions || optionSelected <= 0) {
            throw new MyException("Wybierz numer odpowiadający opcji. Nie mniejszy nie większy. ");
        }
        return optionSelected;
    }

    public void dispalyResult(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    public void dispalyResult(Map<Color, Integer> mapka) {

        for (Color color : mapka.keySet()) {
            System.out.println(color + " - " + mapka.get(color));
        }
    }

    public void dispalyResultOfAExpensiveCar(Map<String, Car> mapka) {

        for (String s : mapka.keySet()) {
            System.out.println("model: " + s + "\n\n" + mapka.get(s));
        }
    }

    public void dispalyResultTheSameComponents(Map<String, List<Car>> mapka) {

        for (String s : mapka.keySet()) {
            System.out.println("komponent : " + s + "\n\n" + mapka.get(s));
        }
    }
}
