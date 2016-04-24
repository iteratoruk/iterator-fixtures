
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
public class FixturesRandomDoubleTheoryTest {

    public static class RandomRange {

        private static final RandomDataGenerator RND = new RandomDataGenerator();

        private final double max;

        private final double min;

        public RandomRange(double min) {
            this.min = min;
            max = RND.nextUniform(min, min + RND.nextUniform(0, Double.MAX_VALUE - min));
        }

        public double getMax() {
            return max;
        }

        public double getMin() {
            return min;
        }

    }

    @DataPoints
    public static final List<RandomRange> RANGES = IntStream.rangeClosed(0, 1000).boxed().map(i -> new RandomRange(Integer.valueOf(i).doubleValue())).collect(Collectors.toList());

    @Theory
    public void shouldProduceDoubleInRange(RandomRange range) throws Exception {
        // when
        double actual = Fixtures.randomDouble(range.getMin(), range.getMax());
        // then
        assertThat(actual, allOf(greaterThanOrEqualTo(range.getMin()), lessThanOrEqualTo(range.getMax())));
    }

}
