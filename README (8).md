# Task 5 – Static Members and Object Lifecycle

## Static ticket counter
`ParkingTicket` declares:
```java
private static final AtomicLong NEXT_ID = new AtomicLong(1000);
```
Each ticket constructor calls `incrementAndGet()`, so ticket numbers increase across ticket objects in the same running JVM. `AtomicLong` makes increment operations atomic if multiple threads issue tickets concurrently.

## Object lifecycle
1. `ParkingApp` creates the facility, user and vehicle objects.
2. `vehicleEntry` creates a ticket and stores it in `activeTickets`.
3. The ticket references its vehicle, user, slot and entry time.
4. `vehicleExit` calculates the charge, releases the slot and removes the active-ticket map entry.
5. Once no live references remain, objects become eligible for garbage collection. Java does not guarantee the exact time garbage collection occurs.

## Evidence
Show the static counter declaration and the ticket constructor. Run the main application twice in separate JVM launches; the sample counter starts again at 1001 because it is process-local, not persisted.
