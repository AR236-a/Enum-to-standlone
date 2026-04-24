public enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CENTIMETER(0.0328084);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    // Convert this unit → base unit (feet)
    public double convertToBaseUnit(double value) {
        return value * toFeetFactor;
    }

    // Convert base unit (feet) → this unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toFeetFactor;
    }

    public double getConversionFactor() {
        return toFeetFactor;
    }
}
public class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    // =========================
    // CONVERSION
    // =========================
    public QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);

        return new QuantityLength(round(converted), targetUnit);
    }

    // =========================
    // ADDITION (UC6 + UC7)
    // =========================
    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        double sumBase =
                this.unit.convertToBaseUnit(this.value) +
                        other.unit.convertToBaseUnit(other.value);

        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new QuantityLength(round(result), targetUnit);
    }

    // =========================
    // EQUALITY
    // =========================
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityLength other = (QuantityLength) obj;

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        return Double.compare(thisBase, otherBase) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }

    // =========================
    // HELPER
    // =========================
    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }
}
public class QuantityMeasurementApp {

    public static void main(String[] args) {

        System.out.println("=== UC8 Demo ===\n");

        QuantityLength q1 = new QuantityLength(1, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12, LengthUnit.INCH);

        // Conversion
        System.out.println(q1.convertTo(LengthUnit.INCH)); // 12 inch

        // Equality
        System.out.println(q1.equals(q2)); // true

        // Addition
        System.out.println(q1.add(q2, LengthUnit.FEET)); // 2 ft

        // Yard example
        QuantityLength q3 = new QuantityLength(1, LengthUnit.YARD);
        QuantityLength q4 = new QuantityLength(3, LengthUnit.FEET);

        System.out.println(q3.add(q4, LengthUnit.YARD)); // 2 yard

        // CM conversion
        QuantityLength q5 = new QuantityLength(2.54, LengthUnit.CENTIMETER);
        System.out.println(q5.convertTo(LengthUnit.INCH)); // ~1 inch
    }
}