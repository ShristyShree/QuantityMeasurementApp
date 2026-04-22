public class QuantityMeasurementApp {

    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.393701 / 12.0);

        private final double toFeet;

        LengthUnit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double toFeet(double value) {
            return value * toFeet;
        }

        public double fromFeet(double value) {
            return value / toFeet;
        }
    }

    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null || !Double.isFinite(value)) throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        private double toBase() {
            return unit.toFeet(value);
        }

        private static QuantityLength fromBase(double base, LengthUnit target) {
            if (target == null) throw new IllegalArgumentException();
            return new QuantityLength(target.fromFeet(base), target);
        }

        public QuantityLength add(QuantityLength other) {
            if (other == null) throw new IllegalArgumentException();
            double sum = this.toBase() + other.toBase();
            return fromBase(sum, this.unit);
        }

        public QuantityLength add(QuantityLength other, LengthUnit target) {
            if (other == null || target == null) throw new IllegalArgumentException();
            double sum = this.toBase() + other.toBase();
            return fromBase(sum, target);
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.toBase(), other.toBase()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toBase());
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static QuantityLength add(QuantityLength q1, QuantityLength q2, LengthUnit target) {
        if (q1 == null || q2 == null || target == null) throw new IllegalArgumentException();
        return q1.add(q2, target);
    }

    public static QuantityLength add(double v1, LengthUnit u1, double v2, LengthUnit u2, LengthUnit target) {
        return new QuantityLength(v1, u1).add(new QuantityLength(v2, u2), target);
    }

    public static void main(String[] args) {
        System.out.println(add(new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH), LengthUnit.FEET));

        System.out.println(add(new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH), LengthUnit.INCH));

        System.out.println(add(new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH), LengthUnit.YARDS));

        System.out.println(add(new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(3.0, LengthUnit.FEET), LengthUnit.YARDS));

        System.out.println(add(new QuantityLength(2.54, LengthUnit.CENTIMETERS),
                new QuantityLength(1.0, LengthUnit.INCH), LengthUnit.CENTIMETERS));
    }
}