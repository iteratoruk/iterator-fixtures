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

class FixturesRandomIntTest {

  static class RandomRange {

    private static final RandomDataGenerator RND = new RandomDataGenerator();

    private final int max;

    private final int min;

    RandomRange(int min) {
      this.min = min;
      max = RND.nextInt(min, min + RND.nextInt(0, Integer.MAX_VALUE - min));
    }

    int getMax() {
      return max;
    }

    int getMin() {
      return min;
    }
  }

  static List<RandomRange> ranges() {
    return IntStream.rangeClosed(0, 1000)
        .boxed()
        .map(RandomRange::new)
        .collect(Collectors.toList());
  }

  @ParameterizedTest
  @MethodSource("ranges")
  public void shouldProduceIntegerInRange(RandomRange range) {
    // when
    int actual = Fixtures.randomInt(range.getMin(), range.getMax());
    // then
    assertThat(
        actual, allOf(greaterThanOrEqualTo(range.getMin()), lessThanOrEqualTo(range.getMax())));
  }
}
