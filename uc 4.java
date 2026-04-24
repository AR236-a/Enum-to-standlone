public class QuantityMeasurementApp {

    // =========================
    // ENUM: LENGTH UNITS
    // =========================
    enum LengthUnit {
        FEET(1.0),

        INCH(1.0 / 12.0),          // 1 inch = 1/12 feet

        YARD(3.0),                 // 1 yard = 3 feet

        CENTIMETER(0.0328084);     // 1 cm = 0.0328084 feet
        // (since 1 cm = 0.393701 inch → /12)

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    // =========================
    // GENERIC QUANTITY CLASS
    // =========================
    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toFeet());
        }
    }

    // =========================
    // STATIC HELPER METHOD
    // =========================
    public static boolean areEqual(double v1, LengthUnit u1,
                                   double v2, LengthUnit u2) {

        QuantityLength q1 = new QuantityLength(v1, u1);
        QuantityLength q2 = new QuantityLength(v2, u2);

        return q1.equals(q2);
    }

    // =========================
    // MAIN METHOD (TEST CASES)
    // =========================
    public static void main(String[] args) {

        System.out.println("UC4 Equality Tests:\n");

        // ---- SAME UNIT TESTS ----
        System.out.println("3 yard == 3 yard : " +
                areEqual(3, LengthUnit.YARD, 3, LengthUnit.YARD)); // true

        System.out.println("100 cm == 100 cm : " +
                areEqual(100, LengthUnit.CENTIMETER, 100, LengthUnit.CENTIMETER)); // true


        // ---- CROSS UNIT TESTS ----

        // Yard ↔ Feet
        System.out.println("1 yard == 3 ft : " +
                areEqual(1, LengthUnit.YARD, 3, LengthUnit.FEET)); // true

        // Feet ↔ Inches
        System.out.println("1 ft == 12 in : " +
                areEqual(1, LengthUnit.FEET, 12, LengthUnit.INCH)); // true

        // Yard ↔ Inches
        System.out.println("1 yard == 36 in : " +
                areEqual(1, LengthUnit.YARD, 36, LengthUnit.INCH)); // true

        // CM ↔ Inches
        System.out.println("2.54 cm == 1 in : " +
                areEqual(2.54, LengthUnit.CENTIMETER, 1, LengthUnit.INCH)); // true

        // CM ↔ Feet
        System.out.println("30.48 cm == 1 ft : " +
                areEqual(30.48, LengthUnit.CENTIMETER, 1, LengthUnit.FEET)); // true

        // CM ↔ Yard
        System.out.println("91.44 cm == 1 yard : " +
                areEqual(91.44, LengthUnit.CENTIMETER, 1, LengthUnit.YARD)); // true


        // ---- NEGATIVE TEST ----
        System.out.println("1 yard == 40 in : " +
                areEqual(1, LengthUnit.YARD, 40, LengthUnit.INCH)); // false
    }
}