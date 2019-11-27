package iterator.test.fixtures;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class FixturesRandomDoubleTest {

  static class RandomRange {

    private static final RandomDataGenerator RND = new RandomDataGenerator();

    private final double max;

    private final double min;

    RandomRange(double min) {
      this.min = min;
      max = RND.nextUniform(min, min + RND.nextUniform(0, Double.MAX_VALUE - min));
    }

    double getMax() {
      return max;
    }

    double getMin() {
      return min;
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
  void shouldProduceDoubleInRange(RandomRange range) {
    // when
    double actual = Fixtures.randomDouble(range.getMin(), range.getMax());
    // then
    assertThat(
        actual, allOf(greaterThanOrEqualTo(range.getMin()), lessThanOrEqualTo(range.getMax())));
  }
}
