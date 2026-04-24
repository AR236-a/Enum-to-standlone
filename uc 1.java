public class QuantityMeasurementApp {

    // Inner class to represent Feet measurement
    static class Feet {
        private final double value;

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        // Getter (optional, good practice)
        public double getValue() {
            return value;
        }

        // Override equals method
        @Override
        public boolean equals(Object obj) {
            // Step 1: Check same reference
            if (this == obj) {
                return true;
            }

            // Step 2: Check null or different class
            if (obj == null || this.getClass() != obj.getClass()) {
                return false;
            }

            // Step 3: Cast safely
            Feet other = (Feet) obj;

            // Step 4: Compare using Double.compare()
            return Double.compare(this.value, other.value) == 0;
        }

        // Override hashCode (important when equals is overridden)
        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    // Main method
    public static void main(String[] args) {

        // Create Feet objects
        Feet value1 = new Feet(5.0);
        Feet value2 = new Feet(5.0);

        // Compare values
        boolean result = value1.equals(value2);

        // Output result
        System.out.println("Are the two values equal? " + result);
    }
}