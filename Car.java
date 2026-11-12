package parking.model;

import java.time.LocalDateTime;

public class Car extends Vehicle {
    public static final double RATE_PER_HOUR = 30.0;

    public Car(String regNo) {
        this(regNo, VehicleCategory.CAR);
    }

    /** Protected constructor lets specialised cars retain their own category. */
    protected Car(String regNo, VehicleCategory category) {
        super(regNo, category);
    }

    @Override public double calculateParkingFee(LocalDateTime in, LocalDateTime out) {
        return billableHours(in, out) * RATE_PER_HOUR;
    }
}
