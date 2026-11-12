# Task 10 – Packages, Access Modifiers and Encapsulation

## Package organisation
- `parking.model`: domain objects and vehicle types.
- `parking.service`: parking workflow.
- `parking.app`: executable demonstration.

## Encapsulation
Fields such as `registrationNumber`, `ticketNumber`, `vehicle` and `entryTime` are private or final. Callers interact through public methods rather than directly changing internal state.

## Access modifiers used
- `private`: implementation details and object state.
- `protected`: constructor/helper access for subclasses.
- `public`: operations intended for other packages.
- package-private: standalone `FeeDemo` helper classes remain local to their source file/package.

## Validation
Constructors reject blank registration numbers, invalid user identity, null required references and invalid slot coordinates. Entry/exit operations validate timestamps and energy input before changing the active ticket/slot state.

## Evidence
Show the package declarations, private fields, public getters and validation branches.
