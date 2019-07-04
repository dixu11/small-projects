package streams.managing_cars_collection.converters;


import streams.managing_cars_collection.model.Car;

import java.util.List;

public class CarsJsonConverter extends JsonConverter<List<Car>> {
    public CarsJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
