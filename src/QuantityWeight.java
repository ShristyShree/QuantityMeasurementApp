import java.util.Objects;

public class QuantityWeight {

    private static final double EPSILON = 1e-5; // FIXED

    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
        this.value = value;
        this.unit = unit;
    }

    public QuantityWeight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new QuantityWeight(converted, targetUnit);
    }

    public QuantityWeight add(QuantityWeight other) {
        return add(other, this.unit);
    }

    public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        double baseSum =
                this.unit.convertToBaseUnit(this.value) +
                        other.unit.convertToBaseUnit(other.value);

        double result = targetUnit.convertFromBaseUnit(baseSum);

        return new QuantityWeight(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityWeight)) return false;

        QuantityWeight other = (QuantityWeight) obj;

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(unit.convertToBaseUnit(value) / EPSILON));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}