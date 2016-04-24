
package iterator.test.fixtures;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.RoundingMode;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class FixturesTest {

    public static enum MetasyntacticVariable {

        FOO, BAR, BAZ, QUIX;

    }

    @Test
    public void shouldNotBeAbleToInstantiateViaReflection() throws Exception {
        Constructor<Fixtures> constructor = Fixtures.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        try {
            constructor.newInstance(new Object[0]);
        } catch (InvocationTargetException e) {
            assertThat(e.getCause(), instanceOf(IllegalStateException.class));
        }
    }

    @Test(expected = NegativeArraySizeException.class)
    public void shouldThrowGivenNegativeIntegerWhenRandomBytes() throws Exception {
        Fixtures.randomBytes(-1);
    }

    @Test
    public void shouldReturnAnIntBetweenIntMinAndIntMaxValuesWhenRandomInt() throws Exception {
        assertThat(Fixtures.randomInt(), allOf(greaterThanOrEqualTo(Integer.MIN_VALUE), lessThanOrEqualTo(Integer.MAX_VALUE)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowGivenMinGreaterThanMaxWhenRandomIntWithRange() throws Exception {
        Fixtures.randomInt(42, 24);
    }

    @Test
    public void shouldReturnValueGivenMinEqualToMaxWhenRandomIntWithRange() throws Exception {
        assertThat(Fixtures.randomInt(42, 42), is(42));
    }

    @Test
    public void shouldProduceBothTrueAndFalseWhenRandomBoolean() throws Exception {
        // given
        int trues = 0;
        int falses = 0;
        // when ... the odds against all 1000 being even or odd are astronomical
        for (int i = 0; i < 1000; i++) {
            if (Fixtures.randomBoolean()) {
                trues++;
            } else {
                falses++;
            }
        }
        // then
        assertThat(trues > 0 && falses > 0, is(true));
    }

    @Test
    public void shouldReturnLongBetweenLongMinAndLongMaxValuesWhenRandomLong() throws Exception {
        assertThat(Fixtures.randomLong(), allOf(greaterThanOrEqualTo(Long.MIN_VALUE), lessThanOrEqualTo(Long.MAX_VALUE)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowGivenMinGreaterThanMaxWhenRandomLongWithRange() throws Exception {
        Fixtures.randomLong(42L, 24L);
    }

    @Test
    public void shouldReturnValueGivenMinEqualToMaxWhenRandomLongWithRange() throws Exception {
        assertThat(Fixtures.randomLong(42L, 42L), is(42L));
    }

    @Test
    public void shouldReturnDoubleBetweenDoubleMinAndDoubleMaxValuesWhenRandomDouble() throws Exception {
        assertThat(Fixtures.randomDouble(), allOf(greaterThanOrEqualTo(Double.MIN_VALUE), lessThanOrEqualTo(Double.MAX_VALUE)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowGivenMinGreaterThanMaxWhenRandomDoubleWithRange() throws Exception {
        Fixtures.randomDouble(42D, 24D);
    }

    @Test
    public void shouldReturnValueGivenMinEqualToMaxWhenRandomDoubleWithRange() throws Exception {
        assertThat(Fixtures.randomDouble(42.0D, 42.0D), is(42.0D));
    }

    @Test
    public void shouldSelectDifferentEnumValuesOverCourseOf1000CallsWhenRandomEnum() throws Exception {
        // given
        Set<MetasyntacticVariable> actual = new HashSet<>();
        // when
        for (int i = 0; i < 1000; i++) {
            actual.add(Fixtures.randomEnum(MetasyntacticVariable.class));
        }
        // then ... should have selected all from such a small set after 1000 calls
        Set<MetasyntacticVariable> expected = EnumSet.allOf(MetasyntacticVariable.class);
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldSelectSubsetGivenRangeWhenRandomEnum() throws Exception {
        // given
        Set<MetasyntacticVariable> actual = new HashSet<>();
        // when
        for (int i = 0; i < 1000; i++) {
            actual.add(Fixtures.randomEnum(MetasyntacticVariable.class, 1, 2));
        }
        // then ... should have selected all from such a small set after 1000 calls
        Set<MetasyntacticVariable> expected = EnumSet.of(MetasyntacticVariable.BAR, MetasyntacticVariable.BAZ);
        assertThat(actual, is(expected));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowGivenInvalidEnumRangeLowWhenRandomEnum() throws Exception {
        Fixtures.randomEnum(MetasyntacticVariable.class, -4, -1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowGivenInvalidEnumRangeHighWhenRandomEnum() throws Exception {
        Fixtures.randomEnum(MetasyntacticVariable.class, 4, 8);
    }

    @Test
    public void shouldReturnDateBetweenZeroAndNowWhenRandomDate() throws Exception {
        assertThat(Fixtures.randomDate(), allOf(greaterThanOrEqualTo(new Date(0L)), lessThanOrEqualTo(new Date(System.currentTimeMillis()))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowGivenMinGreaterThanMaxWhenRandomDateWithRange() throws Exception {
        Fixtures.randomDate(new Date(42L), new Date(24L));
    }

    @Test
    public void shouldReturnValueGivenMinEqualToMaxWhenRandomDateWithRange() throws Exception {
        assertThat(Fixtures.randomDate(new Date(42L), new Date(42L)), is(new Date(42L)));
    }

    @Test
    public void shouldReturnBigDecimalBetweenDoubleMinAndDoubleMaxValuesWhenRandomBigDecimal() throws Exception {
        assertThat(Fixtures.randomBigDecimal(10, 2, RoundingMode.DOWN).doubleValue(), allOf(greaterThanOrEqualTo(Double.MIN_VALUE), lessThanOrEqualTo(Double.MAX_VALUE)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowGivenMinGreaterThanMaxWhenRandomBigDecimalWithRange() throws Exception {
        Fixtures.randomBigDecimal(42D, 24D, 10, 2, RoundingMode.DOWN);
    }

    @Test
    public void shouldReturnValueGivenMinEqualToMaxWhenRandomBigDecimalWithRange() throws Exception {
        assertThat(Fixtures.randomBigDecimal(42.0D, 42.0D, 10, 2, RoundingMode.DOWN).doubleValue(), is(42.0D));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowGivenNegativePrecisionWhenRandomBigDecimal() throws Exception {
        Fixtures.randomBigDecimal(-1, 10, RoundingMode.DOWN);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowGivenNullRoundingModeWhenRandomBigDecimal() throws Exception {
        Fixtures.randomBigDecimal(10, 2, null);
    }

    @Test
    public void shouldReturnFloatBetweenFloatMinAndFloatMaxValuesWhenRandomFloat() throws Exception {
        assertThat(Fixtures.randomFloat(), allOf(greaterThanOrEqualTo(Float.MIN_VALUE), lessThanOrEqualTo(Float.MAX_VALUE)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowGivenMinGreaterThanMaxWhenRandomFloatWithRange() throws Exception {
        Fixtures.randomFloat(42F, 24F);
    }

    @Test
    public void shouldReturnValueGivenMinEqualToMaxWhenRandomFloatWithRange() throws Exception {
        assertThat(Fixtures.randomFloat(42.0F, 42.0F), is(42.0F));
    }

}
