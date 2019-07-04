package streams.managing_cars_collection.service;

import streams.managing_cars_collection.model.Car;
import streams.managing_cars_collection.model.Color;
import streams.managing_cars_collection.model.SortType;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

public class CarService {

    private final List<Car> cars = new ArrayList<>();

    public CarService(String fileName) {
        Gson gson = new Gson();
        Scanner scanner = null;
        String odczytany = "";
        try {
            scanner = new Scanner(new File(fileName + ".json"));
            while (scanner.hasNext()) {
                odczytany += scanner.next();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Car[] carsFromJson = gson.fromJson(odczytany, Car[].class);
        cars.addAll(Arrays.asList(carsFromJson));
    }

    // sortowanie na 4 sposoby rosnaco lub malejaco
    public List<Car> sortingMethods(boolean czyRosnaca, SortType kr) {

        List<Car> nowaLista = new ArrayList<>(cars);


        switch (kr) {
            case COLOR:
                nowaLista.sort((car1, car2) -> (car1.getColor().toString().compareTo(car2.getColor().toString())));
                break;
            case MODEL:
                nowaLista.sort((car1, car2) -> car1.getModel().compareTo(car2.getModel()));
                break;
            case PRICE:
                nowaLista.sort((car1, car2) -> car1.getPrice().compareTo(car2.getPrice()));
                break;
            case MILEAGE:
                nowaLista.sort((car1, car2) -> Double.compare(car1.getMileage(), car2.getMileage()));
                break;
        }

        if (!czyRosnaca) {
            Collections.reverse(nowaLista);
        }
        return nowaLista;

    }


    public List<Car> minimalMileage(double przebieg) {

        return cars.stream().filter(car -> car.getMileage() > przebieg).collect(Collectors.toList());
    }

    // wyswietla ile mamy aut w danym kolorze
    public Map<Color, Integer> calculateCarsWithTheSameColor() {
        Map<Color, Integer> mapaAut = new ConcurrentSkipListMap<>();

        for (Color color : Color.values()) {

            long iloscAut = cars.stream()
                    .filter(car -> car.getColor() == color)
                    .count();

            mapaAut.put(color, (int) iloscAut);
        }
        return mapaAut;
    }

    // po jednym najdrozszym samochodzie kazdego modelu
    // dodac sortowanie mapy po wartosciach
    public Map<String, Car> theHighestPrieceOfModel() {

        Map<String, Car> mapaNajdrozszychAut = new LinkedHashMap<>();

        Set<String> modeleAut = new HashSet<>();

        cars.forEach(car -> modeleAut.add(car.getModel()));

        for (String model : modeleAut) {

            Car najdrozszeAuto = cars.stream()
                    .filter(car -> car.getModel().equals(model))
                    .sorted((car1, car2) -> car1.getPrice().compareTo(car2.getPrice()))
                    .findFirst()
                    .orElse(null);
            mapaNajdrozszychAut.put(model, najdrozszeAuto);
        }

        Map<String, Car> posortowana = mapaNajdrozszychAut.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Car>comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


        return posortowana;
    }

    // wyswietl statystyke obejmujaca: srednia cena aut, najwieksza i najmniejsza cena auta, to samo z przebiegiem
    public void statisticsOfUsageOfCar() {

        BigDecimal calkowitaCena = new BigDecimal(0);
        BigDecimal najwiekszaCena = new BigDecimal(0);
        BigDecimal najmniejszaCena = new BigDecimal(Integer.MAX_VALUE);
        double przebieg = 0;
        double maxPrzebieg = 0;
        double minPrzebieg = Integer.MAX_VALUE;

        int iloscAut = 0;


        for (Car car : cars) {

            if (najwiekszaCena.compareTo(car.getPrice()) < 0) {
                najwiekszaCena = car.getPrice();
            }
            if (najmniejszaCena.compareTo(car.getPrice()) > 0) {
                najmniejszaCena = car.getPrice();
            }

            if (maxPrzebieg < car.getMileage()) {
                maxPrzebieg = car.getMileage();
            }

            if (minPrzebieg > car.getMileage()) {
                minPrzebieg = car.getMileage();
            }


            przebieg += car.getMileage();
            calkowitaCena = calkowitaCena.add(car.getPrice());
            iloscAut++;
        }
        System.out.println("Calkowita cena:" + calkowitaCena);
        System.out.println("Srednia cena auta wynosi:" + calkowitaCena.divide(BigDecimal.valueOf(iloscAut)));
        System.out.println("Największa cena auta wynosi:" + najwiekszaCena);
        System.out.println("Najmniejsza cena auta wynosi:" + najmniejszaCena);

        System.out.println("Sredni przebieg wynosi: " + przebieg / iloscAut);
        System.out.println("Najmniejszy przebieg wynosi: " + minPrzebieg);
        System.out.println("Największy przebieg wynosi: " + maxPrzebieg);

    }

    // dajemy samochod lub samochody z najwieksza cena - dodac sortowanie
    public List<Car> najwiekszaCena() {
        BigDecimal maxCena = new BigDecimal(0);
        List<Car> listaAuto = new ArrayList<>();

        for (Car car : cars) {
            if (maxCena.compareTo(car.getPrice()) < 0) {
                listaAuto.clear();
                maxCena = car.getPrice();
                listaAuto.add(car);
            } else if (maxCena.compareTo(car.getPrice()) == 0) {
                listaAuto.add(car);
            }
        }
        /*listaAuto.sort(Comparator.naturalOrder());
        Collections.sort(listaAuto);*/
        return listaAuto;

    }

    // posortowana alfabetycznie lista komponentow
    public List<Car> sortingInAlphabeticalOrder() {

        for (Car car : cars) {
            car.getComponents().sort(Comparator.naturalOrder());
//            Collections.sort(car.getComponents());
        }

        return cars;
    }


    public Map<String, List<Car>> carsContainingTheSameComponents() {
        /*Map<String,List<Car>> mapa = new HashMap<>();
        for (Car car : cars) {
            for (String component : car.getComponents()) {
                allComponents.add(component);
            }
        }


        List<Car> carsWithComponent = new ArrayList<>();
        for (String component : allComponents) {
            for (Car car : cars) {
                for (String carComponent : car.getComponents()) {
                    if (carComponent.equals(component)) {
                        carsWithComponent.add(car);
                    }
                }
            }
            mapa.put(component, carsWithComponent);
            carsWithComponent = new ArrayList<>();
        }
        return mapa;*/

        return cars
                .stream()
                .flatMap(car -> car.getComponents().stream())
                .distinct()
                .collect(Collectors.toMap(
                        component -> component,
                        component -> cars.stream().filter(car -> car.getComponents().contains(component)).collect(Collectors.toList())
                        )
                );

    }

    public List<Car> priceFromInterval(double a, double b) {

      return cars.stream().filter(car -> ((car.getPrice().doubleValue() >= a) && (car.getPrice().doubleValue() <= b)))
                .sorted()
                .collect(Collectors.toList());

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // ZASTOSUJ STRUMIEN
      /*  for (Car auto : cars) {
            sb.append(auto.toString());
            sb.append("\n");
        }*/
        cars.stream().forEach(auto -> sb.append(auto.toString()).append("\n"));
        return sb.toString();
    }
}
