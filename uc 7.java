public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {

    validateOther(other);

    if (targetUnit == null) {
        throw new IllegalArgumentException("Target unit cannot be null");
    }

    // Convert both to base unit (feet)
    double sumInFeet = this.toFeet() + other.toFeet();

    // Convert to target unit
    double resultValue = targetUnit.fromFeet(sumInFeet);

    return new QuantityLength(resultValue, targetUnit);
}
// =========================
// STATIC ADD WITH TARGET
// =========================
public static QuantityLength add(double v1, LengthUnit u1,
                                 double v2, LengthUnit u2,
                                 LengthUnit targetUnit) {

    if (!Double.isFinite(v1) || !Double.isFinite(v2) ||
            u1 == null || u2 == null || targetUnit == null) {
        throw new IllegalArgumentException("Invalid input");
    }

    QuantityLength q1 = new QuantityLength(v1, u1);
    QuantityLength q2 = new QuantityLength(v2, u2);

    return q1.add(q2, targetUnit);
}
private double sumInBase(QuantityLength other) {
    return this.toFeet() + other.toFeet();
}
double sumInFeet = sumInBase(other);
public static void main(String[] args) {

    System.out.println("=== UC7 Tests ===\n");

    QuantityLength a = new QuantityLength(1, LengthUnit.FEET);
    QuantityLength b = new QuantityLength(12, LengthUnit.INCH);

    // Target = FEET
    System.out.println(a.add(b, LengthUnit.FEET)); // 2 ft

    // Target = INCHES
    System.out.println(a.add(b, LengthUnit.INCH)); // 24 in

    // Target = YARDS
    System.out.println(a.add(b, LengthUnit.YARD)); // ~0.667 yard

    // Target = CM
    System.out.println(a.add(b, LengthUnit.CENTIMETER)); // ~60.96 cm

    // Reverse order (commutativity)
    System.out.println(b.add(a, LengthUnit.FEET)); // same result

    // Mixed units
    QuantityLength c = new QuantityLength(1, LengthUnit.YARD);
    QuantityLength d = new QuantityLength(3, LengthUnit.FEET);

    System.out.println(c.add(d, LengthUnit.YARD)); // 2 yard

    // Edge cases
    QuantityLength zero = new QuantityLength(0, LengthUnit.INCH);
    System.out.println(a.add(zero, LengthUnit.YARD));

    QuantityLength negative = new QuantityLength(-2, LengthUnit.FEET);
    System.out.println(a.add(negative, LengthUnit.INCH)); //  -12 in
}