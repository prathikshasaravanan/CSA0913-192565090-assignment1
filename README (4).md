# Task 12 – Extension and Design Challenge

## Implemented extension vehicle types
- `EBike`: charges a flat Rs 20 per visit, independent of parking duration.
- `DeliveryVan`: charges twice the car rate for billable hours whose hour-start time is between 09:00 and 16:59; otherwise it uses the normal car rate.
- `ElectricCar`: uses the `Chargeable` capability and adds Rs 12 per kWh to its parking fee.

## Pass and facility extensions
`ParkingUser` supports student/staff monthly passes and visitor one-day passes. `ParkingFacility` supports multiple floors with unequal slot capacities and optional charging-enabled positions.

## Design rationale
The extension types reuse the `Vehicle` abstraction and override the fee operation. New pricing behaviour is added in a subtype rather than by adding a growing chain of vehicle-type conditions to the main fee calculation.

## Assumptions
Rates and pass conditions are sample rules for this implementation. If the faculty brief specifies different values or delivery-van time boundaries, use the faculty values and update the tests and report.

## Evidence
Run the regression suite and capture the E-bike and delivery-van test results. In the viva, explain the chosen assumptions and how another vehicle subtype could be added.
