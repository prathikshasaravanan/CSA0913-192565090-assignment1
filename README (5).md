# Task 2 – Data Representation: Types and Arrays

## Data structures
- `ParkingSlot[][] floors`: a jagged two-dimensional array. Each floor can contain a different number of slots.
- `Map<String, ParkingTicket> activeTickets`: maps a normalised registration number to its active ticket.
- `Set<String> chargingPositions`: identifies charging-enabled positions such as `0:2`.
- `LocalDateTime`: stores entry and exit timestamps without manually splitting date/time values.
- `double`: stores example monetary and energy values; the sample tariff is illustrative.

## Why a jagged array?
For capacities `{3, 2, 4}`, the facility creates three rows with 3, 2 and 4 slots respectively. This avoids wasting positions when floors have unequal capacities.

## Complexity
- Building the slot array: O(S), where S is the total number of slots.
- Linear slot search: O(S) worst case.
- Active-ticket map lookup/insert/remove: expected O(1), subject to hash distribution.
- Counting all free slots: O(S).

## Type-safety note
The implementation uses `int` for floor/slot indices and `long` for ticket numbers. It does not rely on narrowing casts for these values. Monetary values are represented as `double` for this teaching example; a production billing system should consider `BigDecimal`.

## Evidence
Show the `ParkingSlot[][]` declaration, the constructor loop that creates each floor, and the active-ticket map.
