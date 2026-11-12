# Task 8 – Polymorphism and Interface-Based Behaviour

The standalone demonstration is `FeeDemo.java`. It creates a `Vehicle[]` containing `TwoWheeler`, `Car` and `ElectricCar` objects.

## Runtime polymorphism
The loop calls `v.calculateFee(130)` through a `Vehicle` reference. The actual object type determines `ratePerHour()` at runtime. `ElectricCar` is also checked for the `Chargeable` interface and receives an additional charging fee.

## Constructor overloading in the integrated system
`ParkingFacility` has two constructors: one accepts floor capacities and charging positions; the one-argument constructor delegates to it with an empty charger set.

## Standalone expected output
```text
TwoWheeler TN01A1 -> Rs 30.0
Car TN02B2 -> Rs 90.0
ElectricCar TN03C3 -> Rs 210.0
true
```

## Evidence
Capture `FeeDemo.java` and the actual output. Explain why the reference type `Vehicle` does not prevent subclass-specific behaviour.
