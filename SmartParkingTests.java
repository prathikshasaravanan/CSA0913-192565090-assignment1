import parking.model.*;
import parking.service.ParkingFacility;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Dependency-free regression tests. Run with:
 *   javac --release 21 -d out $(find src/main/java -name "*.java") tests/SmartParkingTests.java
 *   java -cp out SmartParkingTests
 */
public class SmartParkingTests {
    private static int passed = 0;
    private static final LocalDateTime T0 = LocalDateTime.of(2026, 11, 12, 10, 0);

    public static void main(String[] args) {
        test("electric car has ELECTRIC_CAR category", () -> {
            check(new ElectricCar("tn01ab1234").getCategory() == VehicleCategory.ELECTRIC_CAR,
                    "ElectricCar category must be ELECTRIC_CAR");
        });
        test("registration normalisation", () -> {
            check("TN01AB1234".equals(new Car(" tn01ab1234 ").getRegistrationNumber()),
                    "registration must be trimmed and uppercased");
        });
        test("61 minutes rounds up to two billable hours", () -> {
            double fee = new Car("CAR1").calculateParkingFee(T0, T0.plusMinutes(61));
            check(fee == 60.0, "expected Rs 60, got " + fee);
        });
        test("zero-minute stay costs zero", () -> {
            check(new Car("CAR2").calculateParkingFee(T0, T0) == 0.0, "expected zero fee");
        });
        test("invalid time interval rejected", () -> {
            expectThrows(IllegalArgumentException.class,
                    () -> new Car("CAR3").calculateParkingFee(T0, T0.minusMinutes(1)));
        });
        test("null entry rejected without occupying slot", () -> {
            ParkingFacility f = new ParkingFacility(new int[]{1});
            expectThrows(NullPointerException.class,
                    () -> f.vehicleEntry(new Car("CAR4"), visitor(), null));
            check(f.getAvailableSlotCount() == 1, "slot must remain free");
        });
        test("duplicate active registration rejected", () -> {
            ParkingFacility f = new ParkingFacility(new int[]{2});
            f.vehicleEntry(new Car("DUP1"), visitor(), T0);
            expectThrows(IllegalArgumentException.class,
                    () -> f.vehicleEntry(new Car("dup1"), visitor(), T0));
            check(f.getActiveVehicleCount() == 1, "only one active ticket expected");
        });
        test("full facility rejects additional entry", () -> {
            ParkingFacility f = new ParkingFacility(new int[]{1});
            f.vehicleEntry(new Car("FULL1"), visitor(), T0);
            expectThrows(IllegalStateException.class,
                    () -> f.vehicleEntry(new Car("FULL2"), visitor(), T0));
        });
        test("unknown registration cannot exit", () -> {
            ParkingFacility f = new ParkingFacility(new int[]{1});
            expectThrows(IllegalArgumentException.class,
                    () -> f.vehicleExit("UNKNOWN", T0.plusHours(1), 0));
        });
        test("null exit time rejected and ticket retained", () -> {
            ParkingFacility f = new ParkingFacility(new int[]{1});
            f.vehicleEntry(new Car("NULLX"), visitor(), T0);
            expectThrows(NullPointerException.class, () -> f.vehicleExit("NULLX", null, 0));
            check(f.getActiveVehicleCount() == 1, "ticket must remain active");
        });
        test("negative charging input rejected and ticket retained", () -> {
            ParkingFacility f = new ParkingFacility(new int[]{1});
            f.vehicleEntry(new ElectricCar("EVNEG"), visitor(), T0);
            expectThrows(IllegalArgumentException.class,
                    () -> f.vehicleExit("EVNEG", T0.plusHours(1), -1));
            check(f.getActiveVehicleCount() == 1, "ticket must remain active after invalid input");
        });
        test("NaN charging input rejected", () -> {
            ParkingFacility f = new ParkingFacility(new int[]{1});
            f.vehicleEntry(new ElectricCar("EVNAN"), visitor(), T0);
            expectThrows(IllegalArgumentException.class,
                    () -> f.vehicleExit("EVNAN", T0.plusHours(1), Double.NaN));
        });
        test("valid student monthly pass waives parking fee", () -> {
            ParkingFacility f = new ParkingFacility(new int[]{1});
            ParkingUser student = new ParkingUser("S1", "Student", UserType.STUDENT, true, false);
            f.vehicleEntry(new Car("PASS1"), student, T0);
            check(f.vehicleExit("PASS1", T0.plusHours(2), 0) == 0.0, "expected free parking");
        });
        test("visitor one-day pass waives parking fee", () -> {
            ParkingFacility f = new ParkingFacility(new int[]{1});
            ParkingUser visitorWithPass = new ParkingUser("V2", "Visitor", UserType.VISITOR, false, true);
            f.vehicleEntry(new Car("PASS2"), visitorWithPass, T0);
            check(f.vehicleExit("PASS2", T0.plusHours(2), 0) == 0.0, "expected free parking");
        });
        test("visitor without pass pays normal fee", () -> {
            ParkingFacility f = new ParkingFacility(new int[]{1});
            f.vehicleEntry(new Car("PAY1"), visitor(), T0);
            check(f.vehicleExit("PAY1", T0.plusMinutes(61), 0) == 60.0, "expected Rs 60");
        });
        test("EV parking plus charging fee", () -> {
            ParkingFacility f = new ParkingFacility(new int[]{1}, Set.of("0:0"));
            f.vehicleEntry(new ElectricCar("EV1"), visitor(), T0);
            check(f.vehicleExit("EV1", T0.plusHours(2), 5) == 120.0,
                    "expected Rs 60 parking + Rs 60 charging");
        });
        test("e-bike flat fee", () -> {
            ParkingFacility f = new ParkingFacility(new int[]{1});
            f.vehicleEntry(new EBike("EB1"), visitor(), T0);
            check(f.vehicleExit("EB1", T0.plusHours(4), 0) == EBike.FLAT_FEE,
                    "expected flat e-bike fee");
        });
        test("delivery van daytime hourly rate", () -> {
            ParkingFacility f = new ParkingFacility(new int[]{1});
            f.vehicleEntry(new DeliveryVan("DV1"), visitor(), T0);
            check(f.vehicleExit("DV1", T0.plusHours(1), 0) == 60.0,
                    "expected double-car daytime rate");
        });
        test("delivery van evening hourly rate", () -> {
            ParkingFacility f = new ParkingFacility(new int[]{1});
            LocalDateTime evening = T0.withHour(18);
            f.vehicleEntry(new DeliveryVan("DV2"), visitor(), evening);
            check(f.vehicleExit("DV2", evening.plusHours(1), 0) == 30.0,
                    "expected normal car rate");
        });
        test("slot becomes available after successful exit", () -> {
            ParkingFacility f = new ParkingFacility(new int[]{1});
            f.vehicleEntry(new Car("REL1"), visitor(), T0);
            f.vehicleExit("REL1", T0.plusHours(1), 0);
            check(f.getAvailableSlotCount() == 1, "slot should be released");
            check(f.getActiveVehicleCount() == 0, "active ticket should be removed");
        });
        test("charging slot is preferred for EV", () -> {
            ParkingFacility f = new ParkingFacility(new int[]{2}, Set.of("0:1"));
            ParkingTicket ticket = f.vehicleEntry(new ElectricCar("EVPREF"), visitor(), T0);
            check(ticket.getSlot().getSlotNumber() == 1, "EV should prefer charger slot");
        });

        System.out.println("RESULT: " + passed + " tests passed; 0 failed.");
    }

    private static ParkingUser visitor() {
        return new ParkingUser("VIS", "Test Visitor", UserType.VISITOR, false, false);
    }

    private static void test(String name, Runnable body) {
        try {
            body.run();
            passed++;
            System.out.println("PASS: " + name);
        } catch (Throwable error) {
            System.out.println("FAIL: " + name + " -> " + error);
            throw new AssertionError("Test failed: " + name, error);
        }
    }

    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    private static void expectThrows(Class<? extends Throwable> type, Runnable action) {
        try {
            action.run();
        } catch (Throwable actual) {
            if (type.isInstance(actual)) return;
            throw new AssertionError("Expected " + type.getSimpleName()
                    + " but got " + actual.getClass().getSimpleName(), actual);
        }
        throw new AssertionError("Expected " + type.getSimpleName() + " but nothing was thrown.");
    }
}
