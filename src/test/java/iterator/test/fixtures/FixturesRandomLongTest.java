package iterator.test.fixtures;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class FixturesRandomLongTest {

  static class RandomRange {

    private static final RandomDataGenerator RND = new RandomDataGenerator();

    private final long max;

    private final long min;

    RandomRange(long min) {
      this.min = min;
      max = RND.nextLong(min, min + RND.nextLong(0, Long.MAX_VALUE - min));
    }

    long getMax() {
      return max;
    }

    long getMin() {
      return min;
    }
  }

  static List<RandomRange> ranges() {
    return LongStream.rangeClosed(0, 1000)
        .boxed()
        .map(RandomRange::new)
        .collect(Collectors.toList());
  }

  @ParameterizedTest
  @MethodSource("ranges")
  void shouldProduceLongInRange(RandomRange range) {
    // when
    long actual = Fixtures.randomLong(range.getMin(), range.getMax());
    // then
    assertThat(
        actual, allOf(greaterThanOrEqualTo(range.getMin()), lessThanOrEqualTo(range.getMax())));
  }
}
