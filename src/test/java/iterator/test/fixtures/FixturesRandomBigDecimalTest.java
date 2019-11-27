package iterator.test.fixtures;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class FixturesRandomBigDecimalTest {

  static class RandomRange {

    private static final RoundingMode[] MODES = {
      RoundingMode.CEILING,
      RoundingMode.DOWN,
      RoundingMode.FLOOR,
      RoundingMode.HALF_DOWN,
      RoundingMode.HALF_EVEN,
      RoundingMode.HALF_UP,
      RoundingMode.UP
    };

    private static final RandomDataGenerator RND = new RandomDataGenerator();

    private final double max;

    private final double min;

    private final RoundingMode mode;

    private final int precision;

    private final int scale;

    RandomRange(double min) {
      this.min = min;
      max = RND.nextUniform(min, min + RND.nextUniform(0, Double.MAX_VALUE - min));
      precision = RND.nextInt(10, 20);
      scale = RND.nextInt(1, 8);
      mode = MODES[RND.nextInt(0, MODES.length - 1)];
    }

    double getMax() {
      return max;
    }

    double getMin() {
      return min;
    }

    RoundingMode getMode() {
      return mode;
    }

    int getPrecision() {
      return precision;
    }

    int getScale() {
      return scale;
    }
  }

  static List<RandomRange> ranges() {
    return IntStream.rangeClosed(0, 1000)
        .boxed()
        .map(i -> new RandomRange(i.doubleValue()))
        .collect(Collectors.toList());
  }

  @ParameterizedTest
  @MethodSource("ranges")
  void shouldProduceBigDecimalInRange(RandomRange range) {
    // when
    BigDecimal actual =
        Fixtures.randomBigDecimal(
            range.getMin(),
            range.getMax(),
            range.getPrecision(),
            range.getScale(),
            range.getMode());
    // then
    assertThat(
        actual.doubleValue(),
        allOf(greaterThanOrEqualTo(range.getMin()), lessThanOrEqualTo(range.getMax())));
  }
}
