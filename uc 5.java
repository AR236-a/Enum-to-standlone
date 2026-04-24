public class QuantityMeasurementApp {

    // =========================
    // ENUM: LENGTH UNITS
    // =========================
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    // =========================
    // VALUE OBJECT CLASS
    // =========================
    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            validate(value, unit);
            this.value = value;
            this.unit = unit;
        }

        // =========================
        // VALIDATION
        // =========================
        private void validate(double value, LengthUnit unit) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid numeric value");
            }
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
        }

        // =========================
        // CONVERSION (INSTANCE METHOD)
        // =========================
        public QuantityLength convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double valueInFeet = unit.toFeet(this.value);
            double convertedValue = targetUnit.fromFeet(valueInFeet);

            return new QuantityLength(convertedValue, targetUnit);
        }

        // =========================
        // STATIC CONVERSION METHOD
        // =========================
        public static double convert(double value,
                                     LengthUnit source,
                                     LengthUnit target) {

            if (!Double.isFinite(value) || source == null || target == null) {
                throw new IllegalArgumentException("Invalid input");
            }

            double feet = source.toFeet(value);
            double result = target.fromFeet(feet);

            return round(result);
        }

        // =========================
        // EQUALITY CHECK
        // =========================
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toFeet());
        }

        // =========================
        // toString() OVERRIDE
        // =========================
        @Override
        public String toString() {
            return round(value) + " " + unit;
        }

        // =========================
        // HELPER METHOD
        // =========================
        private static double round(double value) {
            return Math.round(value * 100.0) / 100.0;
        }
    }

    // =========================
    // METHOD OVERLOADING DEMO
    // =========================

    // Method 1: Raw values
    public static void demonstrateLengthConversion(double value,
                                                   LengthUnit from,
                                                   LengthUnit to) {

        double result = QuantityLength.convert(value, from, to);

        System.out.println(value + " " + from + " = " + result + " " + to);
    }

    // Method 2: Object-based
    public static void demonstrateLengthConversion(QuantityLength length,
                                                   LengthUnit to) {

        QuantityLength converted = length.convertTo(to);

        System.out.println(length + " = " + converted);
    }

    // =========================
    // EQUALITY DEMO
    // =========================
    public static void demonstrateLengthEquality(QuantityLength l1,
                                                 QuantityLength l2) {

        System.out.println(l1 + " == " + l2 + " ? " + l1.equals(l2));
    }

    // =========================
    // MAIN METHOD (TEST CASES)
    // =========================
    public static void main(String[] args) {

        System.out.println("=== UC5 Conversion Tests ===\n");

        // Raw conversion
        demonstrateLengthConversion(1, LengthUnit.FEET, LengthUnit.INCH);
        demonstrateLengthConversion(1, LengthUnit.YARD, LengthUnit.INCH);
        demonstrateLengthConversion(30.48, LengthUnit.CENTIMETER, LengthUnit.FEET);

        System.out.println("\n=== Object Conversion ===\n");

        QuantityLength length = new QuantityLength(2, LengthUnit.YARD);
        demonstrateLengthConversion(length, LengthUnit.FEET);

        System.out.println("\n=== Equality Tests ===\n");

        QuantityLength l1 = new QuantityLength(1, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12, LengthUnit.INCH);

        demonstrateLengthEquality(l1, l2); // true
    }
}