public QuantityLength add(QuantityLength other) {

    validateOther(other);

    // Convert both to base unit (feet)
    double sumInFeet = this.toFeet() + other.toFeet();

    // Convert back to unit of first operand (this.unit)
    double resultValue = this.unit.fromFeet(sumInFeet);

    return new QuantityLength(resultValue, this.unit);
}
// =========================
// STATIC ADD (OVERLOADED)
// =========================
public static QuantityLength add(double v1, LengthUnit u1,
                                 double v2, LengthUnit u2) {

    if (!Double.isFinite(v1) || !Double.isFinite(v2) ||
            u1 == null || u2 == null) {
        throw new IllegalArgumentException("Invalid input");
    }

    QuantityLength q1 = new QuantityLength(v1, u1);
    QuantityLength q2 = new QuantityLength(v2, u2);

    return q1.add(q2);
}
private void validateOther(QuantityLength other) {
    if (other == null) {
        throw new IllegalArgumentException("Other length cannot be null");
    }
}
public static void main(String[] args) {

    System.out.println("=== UC6 Addition Tests ===\n");

    QuantityLength l1 = new QuantityLength(1, LengthUnit.FEET);
    QuantityLength l2 = new QuantityLength(12, LengthUnit.INCH);

    // Instance method
    QuantityLength result1 = l1.add(l2);
    System.out.println("1 ft + 12 in = " + result1); // 2 ft

    // Reverse order (commutativity check)
    QuantityLength result2 = l2.add(l1);
    System.out.println("12 in + 1 ft = " + result2); // 24 in

    // Yard + Feet
    QuantityLength l3 = new QuantityLength(1, LengthUnit.YARD);
    QuantityLength l4 = new QuantityLength(1, LengthUnit.FEET);

    System.out.println("1 yard + 1 ft = " + l3.add(l4)); // 1.33 yard approx

    // CM + Inches
    QuantityLength l5 = new QuantityLength(2.54, LengthUnit.CENTIMETER);
    QuantityLength l6 = new QuantityLength(1, LengthUnit.INCH);

    System.out.println("2.54 cm + 1 in = " + l5.add(l6)); // 5.08 cm

    // Static method
    QuantityLength result3 =
            QuantityLength.add(1, LengthUnit.FEET, 12, LengthUnit.INCH);

    System.out.println("Static: 1 ft + 12 in = " + result3);

    // Edge case: zero
    QuantityLength zero = new QuantityLength(0, LengthUnit.FEET);
    System.out.println("0 ft + 1 ft = " + zero.add(l1));

    // Negative values
    QuantityLength neg = new QuantityLength(-1, LengthUnit.FEET);
    System.out.println("-1 ft + 2 ft = " + neg.add(new QuantityLength(2, LengthUnit.FEET)));
}