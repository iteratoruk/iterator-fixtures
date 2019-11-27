package iterator.test.fixtures;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.IntStream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class FixturesRandomAlphanumericStringTest {

  static int[] lengths() {
    return IntStream.rangeClosed(0, 100).toArray();
  }

  @ParameterizedTest
  @MethodSource("lengths")
  void shouldOnlyContainAlpahumericCharacters(int length) {
    // when
    String actual = Fixtures.randomAlphanumericString(length);
    // then
    assertThat(new String(actual).matches("^[a-zA-Z0-9]{" + length + "}$"), is(true));
  }

  @ParameterizedTest
  @MethodSource("lengths")
  void shouldOnlyBeSpecifiedLength(int length) {
    // when
    String actual = Fixtures.randomAlphanumericString(length);
    // then
    assertThat(actual.length(), is(length));
  }
}
