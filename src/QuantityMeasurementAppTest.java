import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ✅ Equality
    @Test
    void testKgEqualsGram() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight g = new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertEquals(kg, g);
    }

    @Test
    void testKgEqualsPound() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight lb = new QuantityWeight(2.20462, WeightUnit.POUND);

        assertEquals(kg, lb);
    }

    // ✅ Conversion
    @Test
    void testConvertKgToGram() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight result = kg.convertTo(WeightUnit.GRAM);

        assertEquals(new QuantityWeight(1000.0, WeightUnit.GRAM), result);
    }

    @Test
    void testConvertPoundToKg() {
        QuantityWeight lb = new QuantityWeight(2.20462, WeightUnit.POUND);
        QuantityWeight result = lb.convertTo(WeightUnit.KILOGRAM);

        assertEquals(new QuantityWeight(1.0, WeightUnit.KILOGRAM), result);
    }

    // ✅ Addition
    @Test
    void testAddKgAndGram() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight g = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = kg.add(g);

        assertEquals(new QuantityWeight(2.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testAddWithTargetUnit() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight g = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = kg.add(g, WeightUnit.GRAM);

        assertEquals(new QuantityWeight(2000.0, WeightUnit.GRAM), result);
    }

    // ✅ Edge Cases
    @Test
    void testZero() {
        QuantityWeight kg = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
        QuantityWeight zero = new QuantityWeight(0.0, WeightUnit.GRAM);

        assertEquals(kg, kg.add(zero));
    }

    @Test
    void testNegative() {
        QuantityWeight kg = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
        QuantityWeight neg = new QuantityWeight(-2000.0, WeightUnit.GRAM);

        QuantityWeight result = kg.add(neg);

        assertEquals(new QuantityWeight(3.0, WeightUnit.KILOGRAM), result);
    }

    // ❌ Category safety (VERY IMPORTANT)
    @Test
    void testWeightNotEqualsLength() {
        QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityLength length = new QuantityLength(1.0, LengthUnit.FEET);

        assertNotEquals(weight, length);
    }
}