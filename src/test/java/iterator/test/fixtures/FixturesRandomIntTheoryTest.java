
package iterator.test.fixtures;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class FixturesRandomIntTheoryTest {

    public static class RandomRange {

        private static final RandomDataGenerator RND = new RandomDataGenerator();

        private final int max;

        private final int min;

        public RandomRange(int min) {
            this.min = min;
            max = RND.nextInt(min, min + RND.nextInt(0, Integer.MAX_VALUE - min));
        }

        public int getMax() {
            return max;
        }

        public int getMin() {
            return min;
        }

    }

    @DataPoints
    public static final List<RandomRange> RANGES = IntStream.rangeClosed(0, 1000).boxed().map(i -> new RandomRange(i)).collect(Collectors.toList());

    @Theory
    public void shouldProduceIntegerInRange(RandomRange range) throws Exception {
        // when
        int actual = Fixtures.randomInt(range.getMin(), range.getMax());
        // then
        assertThat(actual, allOf(greaterThanOrEqualTo(range.getMin()), lessThanOrEqualTo(range.getMax())));
    }

}
