# Improvements from the original ZIP

This is a separate improved copy. The original ZIP and its screenshots are not modified.

## Code fixes
- Corrected the `ElectricCar` category to `ELECTRIC_CAR`.
- Reject null entry timestamps before a parking slot can be occupied.
- Reject invalid exit registration values and null exit timestamps.
- Reject negative, NaN and infinite kWh values before processing exit.
- Ensure an invalid exit request does not release the slot or remove its active ticket.
- Add required-reference checks to `ParkingTicket`.
- Reject a null user type and negative parking-slot coordinates.
- Add input validation to the standalone Task 3 exercise.

## Assignment support
- Replaced placeholder task notes with task-specific explanations for Tasks 1–12.
- Added a text UML class diagram and task 3 pseudocode.
- Added a dependency-free Java regression runner with 21 checks.
- Updated run instructions for macOS/Linux and Windows PowerShell.
- Added a test matrix and a verification record.

## What was verified during preparation
- Main application: compiled and ran successfully.
- Task 3 standalone: compiled and ran successfully.
- Task 8 standalone: compiled and ran successfully.
- Regression runner: 21 passed, 0 failed.

## Still required from the student
- Compare all sample business rules and tariffs with the exact faculty brief.
- Capture fresh screenshots on the student's Mac for the improved version.
- Complete any report sections, personal details, diagrams or rubric-specific evidence required by the faculty.
- Create/maintain the required GitHub repository and progressive commits.
- Read and understand the code before the viva.
