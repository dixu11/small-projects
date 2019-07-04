package streams.managing_cars_collection;

import streams.managing_cars_collection.model.Car;
import streams.managing_cars_collection.model.Color;
import streams.managing_cars_collection.service.MenuService;

import java.io.FileNotFoundException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println(App.class.getResource(""));
        Car car = Car.builder().color(Color.BLUE).build();
        System.out.println(car.getMileage());
        car.setModel("XXX");

        new MenuService("cars").mainMenu();
       /* Car car2 = Car.getCar("Mazda").buil();
        Car car3 = Car.getEmptyCar();
        Car car4 = Car.getDefaultCar();


        Car car = Car.getCar("Fiat")
                .withPrice(new BigDecimal(10000))
                .withColor(Color.RED).withMilleage(3214)
                .withComponents(Arrays.asList("sprzeglo", "maska"))
                .buil();*/



        /*Car car = new Car("mazda", Color.BLUE, new BigDecimal(321000), 15, Arrays.asList("sprzeglo", "maska"));
        Car car1 = new Car("BMW",Color.RED, new BigDecimal(421000),345, Arrays.asList( "zabs" ,"maska"));
        Car car2 = new Car("ferrari",Color.RED, new BigDecimal(999000),326, Arrays.asList( "apteczka","silnik"));
        Car car3 = new Car("maserati",Color.SILVER, new BigDecimal(64300),32, Arrays.asList( "gasnica","lusterka"));
        Car car4 = new Car("maserati",Color.SILVER, new BigDecimal(6400),32, Arrays.asList( "gasnica","lusterka"));

        List<Car> listAut = Arrays.asList(car,car1,car3,car2);

        Gson gson = new Gson();

        String tablicaAsJSON   = gson.toJson(listAut);

        System.out.println(tablicaAsJSON);

        try ( FileWriter fileWriter = new FileWriter(new File("carService.json"))) {

            fileWriter.write(tablicaAsJSON);
        } catch (IOException e) {
            e.printStackTrace();
        }


        CarService carService = new CarService("carService");
        System.out.println(carService);

        System.out.println("-----------------------------------------------");


        List<Car> minimalMileage = carService.minimalMileage(40);

        for (Car auto : minimalMileage){
            System.out.println(auto);
        }
        System.out.println("to jest to");

        Map<String, Car> najdrozsze = carService.theHighestPrieceOfModel();
        for(String model: najdrozsze.keySet()){
            System.out.println(model + "  " + najdrozsze.get(model));
            System.out.println(najdrozsze.get(model).getPrice());
        }
        System.out.println(najdrozsze.size());

        System.out.println("*********************************************");

        carService.statisticsOfUsageOfCar();

        System.out.println("*********************************************");

        System.out.println(carService.calculateCarsWithTheSameColor());


        System.out.println("#############################################################");


        System.out.println(carService.sortingInAlphabeticalOrder());


        Map<String, List<Car>> wygenerowanaMapa = carService.carsContainingTheSameComponents();
        for (String s : wygenerowanaMapa.keySet()) {
            System.out.printf("Komponent - %s, samochody: \n",s);
            for (Car carZListy : wygenerowanaMapa.get(s)) {
                System.out.println(carZListy);
            }
        }
*/


    }
}
