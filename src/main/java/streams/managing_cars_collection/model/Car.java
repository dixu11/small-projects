package streams.managing_cars_collection.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data                   // setter, getter, equals, hashCode, toString
@AllArgsConstructor     // argument constructor
@NoArgsConstructor      // non argument constructor
@Builder                // builder pattern
public class Car {
    private String model;
    private BigDecimal price;
    private Color color;
    private int mileage;
    private final String BRAND = "Mercedes";
    private List<String> components;
}




