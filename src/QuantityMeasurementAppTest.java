import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        var r = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.FEET));
        assertEquals(3.0, r.getValue(), EPS);
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {
        var r = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(6.0, QuantityMeasurementApp.LengthUnit.INCH),
                new QuantityMeasurementApp.QuantityLength(6.0, QuantityMeasurementApp.LengthUnit.INCH));
        assertEquals(12.0, r.getValue(), EPS);
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        var r = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH));
        assertEquals(2.0, r.getValue(), EPS);
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {
        var r = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH),
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET));
        assertEquals(24.0, r.getValue(), EPS);
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet() {
        var r = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS),
                new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET));
        assertEquals(2.0, r.getValue(), EPS);
    }

    @Test
    void testAddition_CrossUnit_CentimeterPlusInch() {
        var r = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(2.54, QuantityMeasurementApp.LengthUnit.CENTIMETERS),
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCH));
        assertEquals(5.08, r.getValue(), 1e-2);
    }

    @Test
    void testAddition_Commutativity() {
        var a = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH));

        var b = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH),
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET));

        assertEquals(a.getValue(), b.getValue(), EPS);
    }

    @Test
    void testAddition_WithZero() {
        var r = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(0.0, QuantityMeasurementApp.LengthUnit.INCH));
        assertEquals(5.0, r.getValue(), EPS);
    }

    @Test
    void testAddition_NegativeValues() {
        var r = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(-2.0, QuantityMeasurementApp.LengthUnit.FEET));
        assertEquals(3.0, r.getValue(), EPS);
    }

    @Test
    void testAddition_NullSecondOperand() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.add(
                    new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                    null);
        });
    }

    @Test
    void testAddition_LargeValues() {
        var r = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1e6, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(1e6, QuantityMeasurementApp.LengthUnit.FEET));
        assertEquals(2e6, r.getValue(), EPS);
    }

    @Test
    void testAddition_SmallValues() {
        var r = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(0.001, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(0.002, QuantityMeasurementApp.LengthUnit.FEET));
        assertEquals(0.003, r.getValue(), EPS);
    }
}