package iterator.test.fixtures;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.RoundingMode;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class FixturesTest {

  public enum MetasyntacticVariable {
    FOO,
    BAR,
    BAZ,
    QUIX;
  }

  @Test
  void shouldNotBeAbleToInstantiateViaReflection() throws Exception {
    Constructor<Fixtures> constructor = Fixtures.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    assertThrows(InvocationTargetException.class, constructor::newInstance);
  }

  @Test
  void shouldThrowGivenNegativeIntegerWhenRandomBytes() {
    assertThrows(
        NegativeArraySizeException.class,
        () -> {
          Fixtures.randomBytes(-1);
        });
  }

  @Test
  void shouldReturnAnIntBetweenIntMinAndIntMaxValuesWhenRandomInt() {
    assertThat(
        Fixtures.randomInt(),
        allOf(greaterThanOrEqualTo(Integer.MIN_VALUE), lessThanOrEqualTo(Integer.MAX_VALUE)));
  }

  @Test
  void shouldThrowGivenMinGreaterThanMaxWhenRandomIntWithRange() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          Fixtures.randomInt(42, 24);
        });
  }

  @Test
  void shouldReturnValueGivenMinEqualToMaxWhenRandomIntWithRange() {
    assertThat(Fixtures.randomInt(42, 42), is(42));
  }

  @Test
  void shouldProduceBothTrueAndFalseWhenRandomBoolean() {
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
  void shouldReturnLongBetweenLongMinAndLongMaxValuesWhenRandomLong() {
    assertThat(
        Fixtures.randomLong(),
        allOf(greaterThanOrEqualTo(Long.MIN_VALUE), lessThanOrEqualTo(Long.MAX_VALUE)));
  }

  @Test
  void shouldThrowGivenMinGreaterThanMaxWhenRandomLongWithRange() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          Fixtures.randomLong(42L, 24L);
        });
  }

  @Test
  void shouldReturnValueGivenMinEqualToMaxWhenRandomLongWithRange() {
    assertThat(Fixtures.randomLong(42L, 42L), is(42L));
  }

  @Test
  void shouldReturnDoubleBetweenDoubleMinAndDoubleMaxValuesWhenRandomDouble() {
    assertThat(
        Fixtures.randomDouble(),
        allOf(greaterThanOrEqualTo(Double.MIN_VALUE), lessThanOrEqualTo(Double.MAX_VALUE)));
  }

  @Test
  void shouldThrowGivenMinGreaterThanMaxWhenRandomDoubleWithRange() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          Fixtures.randomDouble(42D, 24D);
        });
  }

  @Test
  void shouldReturnValueGivenMinEqualToMaxWhenRandomDoubleWithRange() {
    assertThat(Fixtures.randomDouble(42.0D, 42.0D), is(42.0D));
  }

  @Test
  void shouldSelectDifferentEnumValuesOverCourseOf1000CallsWhenRandomEnum() {
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
  void shouldSelectSubsetGivenRangeWhenRandomEnum() {
    // given
    Set<MetasyntacticVariable> actual = new HashSet<>();
    // when
    for (int i = 0; i < 1000; i++) {
      actual.add(Fixtures.randomEnum(MetasyntacticVariable.class, 1, 2));
    }
    // then ... should have selected all from such a small set after 1000 calls
    Set<MetasyntacticVariable> expected =
        EnumSet.of(MetasyntacticVariable.BAR, MetasyntacticVariable.BAZ);
    assertThat(actual, is(expected));
  }

  @Test
  void shouldThrowGivenInvalidEnumRangeLowWhenRandomEnum() {
    assertThrows(
        ArrayIndexOutOfBoundsException.class,
        () -> {
          Fixtures.randomEnum(MetasyntacticVariable.class, -4, -1);
        });
  }

  @Test
  void shouldThrowGivenInvalidEnumRangeHighWhenRandomEnum() {
    assertThrows(
        ArrayIndexOutOfBoundsException.class,
        () -> {
          Fixtures.randomEnum(MetasyntacticVariable.class, 4, 8);
        });
  }

  @Test
  void shouldReturnDateBetweenZeroAndNowWhenRandomDate() {
    assertThat(
        Fixtures.randomDate(),
        allOf(
            greaterThanOrEqualTo(new Date(0L)),
            lessThanOrEqualTo(new Date(System.currentTimeMillis()))));
  }

  @Test
  void shouldThrowGivenMinGreaterThanMaxWhenRandomDateWithRange() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          Fixtures.randomDate(new Date(42L), new Date(24L));
        });
  }

  @Test
  void shouldReturnValueGivenMinEqualToMaxWhenRandomDateWithRange() {
    assertThat(Fixtures.randomDate(new Date(42L), new Date(42L)), is(new Date(42L)));
  }

  @Test
  void shouldReturnBigDecimalBetweenDoubleMinAndDoubleMaxValuesWhenRandomBigDecimal() {
    assertThat(
        Fixtures.randomBigDecimal(10, 2, RoundingMode.DOWN).doubleValue(),
        allOf(greaterThanOrEqualTo(Double.MIN_VALUE), lessThanOrEqualTo(Double.MAX_VALUE)));
  }

  @Test
  void shouldThrowGivenMinGreaterThanMaxWhenRandomBigDecimalWithRange() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          Fixtures.randomBigDecimal(42D, 24D, 10, 2, RoundingMode.DOWN);
        });
  }

  @Test
  void shouldReturnValueGivenMinEqualToMaxWhenRandomBigDecimalWithRange() {
    assertThat(
        Fixtures.randomBigDecimal(42.0D, 42.0D, 10, 2, RoundingMode.DOWN).doubleValue(), is(42.0D));
  }

  @Test
  void shouldThrowGivenNegativePrecisionWhenRandomBigDecimal() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          Fixtures.randomBigDecimal(-1, 10, RoundingMode.DOWN);
        });
  }

  @Test
  void shouldThrowGivenNullRoundingModeWhenRandomBigDecimal() {
    assertThrows(
        NullPointerException.class,
        () -> {
          Fixtures.randomBigDecimal(10, 2, null);
        });
  }

  @Test
  void shouldReturnFloatBetweenFloatMinAndFloatMaxValuesWhenRandomFloat() {
    assertThat(
        Fixtures.randomFloat(),
        allOf(greaterThanOrEqualTo(Float.MIN_VALUE), lessThanOrEqualTo(Float.MAX_VALUE)));
  }

  @Test
  void shouldThrowGivenMinGreaterThanMaxWhenRandomFloatWithRange() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          Fixtures.randomFloat(42F, 24F);
        });
  }

  @Test
  void shouldReturnValueGivenMinEqualToMaxWhenRandomFloatWithRange() {
    assertThat(Fixtures.randomFloat(42.0F, 42.0F), is(42.0F));
  }
}
