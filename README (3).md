# Task 11 – Integrated Implementation and Testing

## Main application
`src/main/java/parking/app/ParkingApp.java` demonstrates vehicle entry, ticket issue, slot allocation, exit, fee calculation and slot release.

## Automated regression checks
A dependency-free test runner is included at `tests/SmartParkingTests.java`. It checks:
- Vehicle category and registration normalisation.
- Started-hour billing and zero-duration billing.
- Invalid time and energy inputs.
- Null entry/exit handling.
- Duplicate registrations and full facilities.
- Unknown ticket exit.
- Student and visitor pass rules.
- EV charging, e-bike and delivery-van pricing.
- Slot release and EV charger preference.

## Run tests
See the root README. The test runner prints one `PASS:` line per check and a final count. Do not claim tests passed until you run them locally.

## Manual test evidence
Capture your own terminal output after running the main app, Task 3, Task 8 and regression test runner. Add screenshots to your report; they are not fabricated or pre-filled in this ZIP.
