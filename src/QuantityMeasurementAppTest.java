import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    @Test
    void testConversion_FeetToInches() {
        assertEquals(12.0, QuantityMeasurementApp.convert(1.0,
                QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.INCH), EPS);
    }

    @Test
    void testConversion_InchesToFeet() {
        assertEquals(2.0, QuantityMeasurementApp.convert(24.0,
                QuantityMeasurementApp.LengthUnit.INCH,
                QuantityMeasurementApp.LengthUnit.FEET), EPS);
    }

    @Test
    void testConversion_YardsToInches() {
        assertEquals(36.0, QuantityMeasurementApp.convert(1.0,
                QuantityMeasurementApp.LengthUnit.YARDS,
                QuantityMeasurementApp.LengthUnit.INCH), EPS);
    }

    @Test
    void testConversion_InchesToYards() {
        assertEquals(2.0, QuantityMeasurementApp.convert(72.0,
                QuantityMeasurementApp.LengthUnit.INCH,
                QuantityMeasurementApp.LengthUnit.YARDS), EPS);
    }

    @Test
    void testConversion_CentimetersToInches() {
        assertEquals(1.0, QuantityMeasurementApp.convert(2.54,
                QuantityMeasurementApp.LengthUnit.CENTIMETERS,
                QuantityMeasurementApp.LengthUnit.INCH), EPS);
    }

    @Test
    void testConversion_FeetToYard() {
        assertEquals(2.0, QuantityMeasurementApp.convert(6.0,
                QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.YARDS), EPS);
    }

    @Test
    void testConversion_RoundTrip_PreservesValue() {
        double v = 5.5;
        double result = QuantityMeasurementApp.convert(
                QuantityMeasurementApp.convert(v,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                QuantityMeasurementApp.LengthUnit.INCH,
                QuantityMeasurementApp.LengthUnit.FEET
        );
        assertEquals(v, result, EPS);
    }

    @Test
    void testConversion_ZeroValue() {
        assertEquals(0.0, QuantityMeasurementApp.convert(0.0,
                QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.INCH), EPS);
    }

    @Test
    void testConversion_NegativeValue() {
        assertEquals(-12.0, QuantityMeasurementApp.convert(-1.0,
                QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.INCH), EPS);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.convert(1.0, null,
                    QuantityMeasurementApp.LengthUnit.FEET);
        });
    }

    @Test
    void testConversion_NaNOrInfinite_Throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.convert(Double.NaN,
                    QuantityMeasurementApp.LengthUnit.FEET,
                    QuantityMeasurementApp.LengthUnit.INCH);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.convert(Double.POSITIVE_INFINITY,
                    QuantityMeasurementApp.LengthUnit.FEET,
                    QuantityMeasurementApp.LengthUnit.INCH);
        });
    }

    @Test
    void testConversion_SameUnit() {
        assertEquals(5.0, QuantityMeasurementApp.convert(5.0,
                QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.FEET), EPS);
    }
}