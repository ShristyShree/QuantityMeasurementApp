import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        var r = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH),
                QuantityMeasurementApp.LengthUnit.FEET);
        assertEquals(2.0, r.getValue(), EPS);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        var r = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH),
                QuantityMeasurementApp.LengthUnit.INCH);
        assertEquals(24.0, r.getValue(), EPS);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        var r = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH),
                QuantityMeasurementApp.LengthUnit.YARDS);
        assertEquals(0.666666, r.getValue(), 1e-3);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {
        var r = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCH),
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCH),
                QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        assertEquals(5.08, r.getValue(), 1e-2);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {
        var a = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH),
                QuantityMeasurementApp.LengthUnit.YARDS);

        var b = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH),
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(a.getValue(), b.getValue(), EPS);
    }

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {
        var r = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(0.0, QuantityMeasurementApp.LengthUnit.INCH),
                QuantityMeasurementApp.LengthUnit.YARDS);
        assertEquals(1.666666, r.getValue(), 1e-3);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {
        var r = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(-2.0, QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.INCH);
        assertEquals(36.0, r.getValue(), EPS);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.add(
                    new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                    new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH),
                    null);
        });
    }

    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        var r = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1000.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(500.0, QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.INCH);
        assertEquals(18000.0, r.getValue(), EPS);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
        var r = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH),
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH),
                QuantityMeasurementApp.LengthUnit.YARDS);
        assertEquals(0.666666, r.getValue(), 1e-3);
    }
}