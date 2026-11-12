# Task 11 and Task 12 – Test Case Matrix

The executable regression runner is `SmartParkingTests.java`. Run it using the commands in the root README. The table below describes the automated scenarios; mark a test as passed in your report only after your own run.

| ID | Scenario | Expected result |
|---|---|---|
| T01 | Electric car construction | Category is `ELECTRIC_CAR` |
| T02 | Registration has spaces/lowercase | Trimmed and uppercased |
| T03 | Car stays 61 minutes | Two billable hours, Rs 60 |
| T04 | Zero-minute stay | Rs 0 |
| T05 | Exit precedes entry | Rejected |
| T06 | Null entry timestamp | Rejected; slot remains free |
| T07 | Duplicate active registration | Rejected |
| T08 | All slots occupied | Additional entry rejected |
| T09 | Unknown registration exits | Rejected |
| T10 | Null exit timestamp | Rejected; active ticket retained |
| T11 | Negative charging kWh | Rejected; active ticket retained |
| T12 | NaN charging kWh | Rejected |
| T13 | Student with valid monthly pass | Parking fee waived |
| T14 | Visitor with valid one-day pass | Parking fee waived |
| T15 | Visitor without pass | Normal parking fee |
| T16 | Electric car, 2 hours and 5 kWh | Rs 60 parking + Rs 60 charging |
| T17 | E-bike | Flat Rs 20 |
| T18 | Delivery van, hour begins 10:00 | Rs 60 |
| T19 | Delivery van, hour begins 18:00 | Rs 30 |
| T20 | Successful exit | Slot released and active ticket removed |
| T21 | EV with charging slot available | Charging slot preferred |

The test runner prints PASS/FAIL lines and stops on the first failed test. Keep the actual terminal output as evidence.
