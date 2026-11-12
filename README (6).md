# Task 3 – Control Logic and Pseudocode

The standalone exercise is `ParkingFacility.java`. The integrated version is `src/main/java/parking/service/ParkingFacility.java`.

## Allocation pseudocode
```text
FOR each floor f
    FOR each slot s on floor f
        IF slot is empty
            store vehicle number in slot
            RETURN (f, s)
RETURN null
```

## Release pseudocode
```text
IF floor index is outside the array
    RETURN false
IF slot index is outside that floor
    RETURN false
IF slot is already empty
    RETURN false
clear the slot
RETURN true
```

## Complexity
Let S be the total number of slots across all floors.
- Allocation: O(S) worst case because each slot may be inspected once.
- Release: O(1) because the array position is accessed directly after bounds checks.
- Storage: O(S).

## Standalone expected output
```text
[0, 0]
[0, 1]
[1, 0]
null
true false false
[0, 1]
```

## Evidence
Compile and run `Task03/ParkingFacility.java` separately, then capture the terminal output. The integrated service adds charging-slot preference and ticket management.
