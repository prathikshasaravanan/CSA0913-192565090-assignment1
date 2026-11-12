package parking.model;

public class ElectricCar extends Car implements Chargeable {
    public static final double CHARGING_RATE_PER_KWH = 12.0;

    public ElectricCar(String regNo) {
        super(regNo, VehicleCategory.ELECTRIC_CAR);
    }

    @Override public double chargingFee(double kWh) {
        if (!Double.isFinite(kWh) || kWh < 0) {
            throw new IllegalArgumentException("kWh must be a finite, non-negative value.");
        }
        return kWh * CHARGING_RATE_PER_KWH;
    }
}
