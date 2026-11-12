# Task 4 – Classes, Objects and Constructors

## Classes and objects
The project defines `Vehicle`, `Car`, `TwoWheeler`, `ElectricCar`, `ParkingUser`, `ParkingSlot`, `ParkingTicket` and `ParkingFacility`. The `ParkingApp` creates objects and coordinates an example entry and exit.

## Constructor examples
- `Car(String)` calls `Vehicle` through `super(...)`.
- `ElectricCar(String)` calls the protected `Car` constructor with `VehicleCategory.ELECTRIC_CAR`.
- `ParkingFacility(int[])` delegates to the two-argument constructor using `this(...)`.
- `ParkingTicket(...)` initialises final references and rejects null arguments.

## Constructor chaining
`this(...)` reuses another constructor in the same class. `super(...)` invokes the parent constructor. Both calls must be the first statement in a Java constructor.

## Evidence
Show constructor declarations in `Car`, `ElectricCar` and `ParkingFacility`, then explain the object creation in `ParkingApp`.
