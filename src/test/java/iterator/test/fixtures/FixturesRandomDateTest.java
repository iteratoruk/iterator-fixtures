package iterator.test.fixtures;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class FixturesRandomDateTest {

  public static class RandomRange {

    private static final RandomDataGenerator RND = new RandomDataGenerator();

    private final Date max;

    private final Date min;

    RandomRange(Date min) {
      this.min = min;
      max =
          new Date(
              RND.nextLong(
                  min.getTime(), min.getTime() + RND.nextLong(0, Long.MAX_VALUE - min.getTime())));
    }

    Date getMax() {
      return max;
    }

    Date getMin() {
      return min;
    }
  }

  static List<RandomRange> ranges() {
    return LongStream.rangeClosed(0, 1000)
        .boxed()
        .map(i -> new RandomRange(new Date(i)))
        .collect(Collectors.toList());
  }

  @ParameterizedTest
  @MethodSource("ranges")
  void shouldProduceLongInRange(RandomRange range) {
    // when
    Date actual = Fixtures.randomDate(range.getMin(), range.getMax());
    // then
    assertThat(
        actual, allOf(greaterThanOrEqualTo(range.getMin()), lessThanOrEqualTo(range.getMax())));
  }
}
