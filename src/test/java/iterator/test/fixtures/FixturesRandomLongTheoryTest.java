
package iterator.test.fixtures;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class FixturesRandomLongTheoryTest {

    public static class RandomRange {

        private static final RandomDataGenerator RND = new RandomDataGenerator();

        private final long max;

        private final long min;

        public RandomRange(long min) {
            this.min = min;
            max = RND.nextLong(min, min + RND.nextLong(0, Long.MAX_VALUE - min));
        }

        public long getMax() {
            return max;
        }

        public long getMin() {
            return min;
        }

    }

    @DataPoints
    public static final List<RandomRange> RANGES = LongStream.rangeClosed(0, 1000).boxed().map(i -> new RandomRange(i)).collect(Collectors.toList());

    @Theory
    public void shouldProduceLongInRange(RandomRange range) throws Exception {
        // when
        long actual = Fixtures.randomLong(range.getMin(), range.getMax());
        // then
        assertThat(actual, allOf(greaterThanOrEqualTo(range.getMin()), lessThanOrEqualTo(range.getMax())));
    }

}
