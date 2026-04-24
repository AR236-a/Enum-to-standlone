public class QuantityMeasurementApp {

    // =========================
    // FEET CLASS (same as UC1)
    // =========================
    static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;

            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    // =========================
    // INCHES CLASS (NEW)
    // =========================
    static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;

            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    // =========================
    // STATIC METHODS (CORE LOGIC)
    // =========================

    // Feet equality check
    public static boolean areFeetEqual(double value1, double value2) {
        Feet f1 = new Feet(value1);
        Feet f2 = new Feet(value2);
        return f1.equals(f2);
    }

    // Inches equality check
    public static boolean areInchesEqual(double value1, double value2) {
        Inches i1 = new Inches(value1);
        Inches i2 = new Inches(value2);
        return i1.equals(i2);
    }

    // =========================
    // MAIN METHOD (TEST CASES)
    // =========================
    public static void main(String[] args) {

        // --- Feet Tests ---
        System.out.println("Feet Equality Tests:");
        System.out.println("5.0 == 5.0 : " + areFeetEqual(5.0, 5.0));   // true
        System.out.println("5.0 == 6.0 : " + areFeetEqual(5.0, 6.0));   // false

        // --- Inches Tests ---
        System.out.println("\nInches Equality Tests:");
        System.out.println("12.0 == 12.0 : " + areInchesEqual(12.0, 12.0)); // true
        System.out.println("12.0 == 10.0 : " + areInchesEqual(12.0, 10.0)); // false
    }
}