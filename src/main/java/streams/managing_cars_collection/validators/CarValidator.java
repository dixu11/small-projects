package streams.managing_cars_collection.validators;

import streams.managing_cars_collection.model.Car;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarValidator {

    private Map<String, List<String>> errors = new HashMap<>();

    public Map<String, List<String>> validate(Car car) {
        if (car == null) {
            errors.put("car", Arrays.asList("car object is null"));
        }

        if (!isModelValid(car)) {
            errors.put("model", Arrays.asList("car model is not valid"));
        }

        if (!isPriceValid(car)) {
            errors.put("price", Arrays.asList("car price is not valid"));
        }

        if (!isMileageValid(car)) {
            errors.put("mileage", Arrays.asList("car mileage is not valid"));
        }

        if (!areComponentsValid(car)) {
            errors.put("components", Arrays.asList("components list is not valid"));
        }

        return errors;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    private boolean isModelValid(Car car) {
        return car.getModel() != null && car.getModel().matches("[A-Z ]+");
    }

    private boolean isPriceValid(Car car) {
        return car.getPrice() != null && car.getPrice().compareTo(BigDecimal.ZERO) >= 0;
    }

    private boolean isMileageValid(Car car) {
        return car.getMileage() >= 0;
    }

    private boolean areComponentsValid(Car car) {
        return  car.getComponents() != null &&
                car.getComponents()
                        .stream()
                        .allMatch(component -> component != null && component.matches("[A-Z]+"));
    }

}
