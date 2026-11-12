# Task 7 – Method Overriding and Object Methods

## Overriding
Each concrete vehicle class provides its own `calculateParkingFee(...)` implementation. This lets the same method call produce a vehicle-specific fee.

`@Override` asks the compiler to verify that a method really overrides an inherited method.

## Object methods
`Vehicle` overrides:
- `toString()` to display the class name and registration.
- `equals(Object)` to compare vehicles by normalised registration number.
- `hashCode()` using the same registration number, consistent with `equals()`.

## final usage
The registration number and category fields in `Vehicle` are `final`, so they cannot be reassigned after construction.

## Evidence
Compare `Car.calculateParkingFee`, `TwoWheeler.calculateParkingFee`, and `EBike.calculateParkingFee`. Show `equals` and `hashCode` together.
