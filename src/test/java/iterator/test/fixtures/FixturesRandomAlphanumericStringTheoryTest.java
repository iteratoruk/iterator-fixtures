
package iterator.test.fixtures;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.IntStream;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class FixturesRandomAlphanumericStringTheoryTest {

    @DataPoints
    public static final int[] LENGTHS = IntStream.rangeClosed(0, 100).toArray();

    @Theory
    public void shouldOnlyContainAlpahumericCharacters(int length) throws Exception {
        // when
        String actual = Fixtures.randomAlphanumericString(length);
        // then
        assertThat(new String(actual).matches("^[a-zA-Z0-9]{" + length + "}$"), is(true));
    }

    @Theory
    public void shouldOnlyBeSpecifiedLength(int length) throws Exception {
        // when
        String actual = Fixtures.randomAlphanumericString(length);
        // then
        assertThat(actual.length(), is(length));
    }

}
