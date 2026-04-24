public class QuantityMeasurementApp {

    // =========================
    // ENUM: LENGTH UNITS
    // =========================
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0);  // 1 inch = 1/12 feet

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

        // Convert to base unit (feet)
        private double toFeet() {
            return unit.toFeet(value);
        }

        // Equality check
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            // Compare after conversion
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toFeet());
        }
    }

    // =========================
    // STATIC METHOD (OPTIONAL)
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

        System.out.println("UC3 Equality Tests:\n");

        // Same unit (Feet)
        System.out.println("5 ft == 5 ft : " +
                areEqual(5, LengthUnit.FEET, 5, LengthUnit.FEET)); // true

        // Same unit (Inches)
        System.out.println("12 in == 12 in : " +
                areEqual(12, LengthUnit.INCH, 12, LengthUnit.INCH)); // true

        // Cross unit comparison
        System.out.println("1 ft == 12 in : " +
                areEqual(1, LengthUnit.FEET, 12, LengthUnit.INCH)); // true

        // Not equal case
        System.out.println("2 ft == 24 in : " +
                areEqual(2, LengthUnit.FEET, 24, LengthUnit.INCH)); // true

        System.out.println("2 ft == 25 in : " +
                areEqual(2, LengthUnit.FEET, 25, LengthUnit.INCH)); // false
    }
}