public class QuantityMeasurementApp {

    public static void demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {
        double result = QuantityLength.convert(value, from, to);
        System.out.println("convert(" + value + ", " + from + ", " + to + ") = " + result);
    }

    public static void demonstrateLengthConversion(QuantityLength length, LengthUnit to) {
        QuantityLength result = length.convertTo(to);
        System.out.println(length + " -> " + result);
    }

    public static void demonstrateLengthEquality(QuantityLength l1, QuantityLength l2) {
        System.out.println(l1 + " equals " + l2 + " = " + l1.equals(l2));
    }

    public static void demonstrateLengthAddition(QuantityLength l1, QuantityLength l2, LengthUnit target) {
        QuantityLength result = l1.add(l2, target);
        System.out.println(l1 + " + " + l2 + " = " + result);
    }

    public static void main(String[] args) {
        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET);

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        demonstrateLengthEquality(a, b);

        demonstrateLengthAddition(a, b, LengthUnit.FEET);
        demonstrateLengthAddition(a, b, LengthUnit.INCHES);
        demonstrateLengthAddition(a, b, LengthUnit.YARDS);

        demonstrateLengthConversion(a, LengthUnit.INCHES);
    }
}