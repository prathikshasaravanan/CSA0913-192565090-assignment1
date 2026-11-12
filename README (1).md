# Task 9 – Abstraction: Abstract Classes and Interfaces

## Abstract class
`Vehicle` is abstract because a generic vehicle does not have one universal parking tariff. It contains common state (`registrationNumber`, `category`) and declares the abstract method `calculateParkingFee(...)`.

## Interface
`Chargeable` declares `chargingFee(double kWh)`. `ElectricCar` implements it because not every vehicle supports charging.

## Why both?
The abstract class shares implementation and state among related vehicle types. The interface describes an optional capability that can be implemented by a class in addition to its parent class.

## Evidence
Show the `abstract class Vehicle`, abstract fee method, `interface Chargeable`, and `ElectricCar implements Chargeable`.
