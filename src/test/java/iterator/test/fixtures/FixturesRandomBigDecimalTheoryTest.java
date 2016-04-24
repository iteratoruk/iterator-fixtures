
package iterator.test.fixtures;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class FixturesRandomBigDecimalTheoryTest {

    public static class RandomRange {

        private static final RoundingMode[] MODES = { RoundingMode.CEILING, RoundingMode.DOWN, RoundingMode.FLOOR, RoundingMode.HALF_DOWN, RoundingMode.HALF_EVEN, RoundingMode.HALF_UP, RoundingMode.UP };

        private static final RandomDataGenerator RND = new RandomDataGenerator();

        private final double max;

        private final double min;

        private final RoundingMode mode;

        private final int precision;

        private final int scale;

        public RandomRange(double min) {
            this.min = min;
            max = RND.nextUniform(min, min + RND.nextUniform(0, Double.MAX_VALUE - min));
            precision = RND.nextInt(10, 20);
            scale = RND.nextInt(1, 8);
            mode = MODES[RND.nextInt(0, MODES.length - 1)];
        }

        public double getMax() {
            return max;
        }

        public double getMin() {
            return min;
        }

        public RoundingMode getMode() {
            return mode;
        }

        public int getPrecision() {
            return precision;
        }

        public int getScale() {
            return scale;
        }

    }

    @DataPoints
    public static final List<RandomRange> RANGES = IntStream.rangeClosed(0, 1000).boxed().map(i -> new RandomRange(Integer.valueOf(i).doubleValue())).collect(Collectors.toList());

    @Theory
    public void shouldProduceBigDecimalInRange(RandomRange range) throws Exception {
        // when
        BigDecimal actual = Fixtures.randomBigDecimal(range.getMin(), range.getMax(), range.getPrecision(), range.getScale(), range.getMode());
        // then
        assertThat(actual.doubleValue(), allOf(greaterThanOrEqualTo(range.getMin()), lessThanOrEqualTo(range.getMax())));
    }

}
