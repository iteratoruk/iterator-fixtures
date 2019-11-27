package iterator.test.fixtures;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.IntStream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class FixturesRandomBytesTest {

  static int[] lengths() {
    return IntStream.rangeClosed(0, 100).toArray();
  }

  @ParameterizedTest
  @MethodSource("lengths")
  void shouldOnlyContainAlpahumericCharacters(int length) {
    // when
    byte[] actual = Fixtures.randomBytes(length);
    // then
    assertThat(new String(actual).matches("^[a-zA-Z0-9]*$"), is(true));
  }

  @ParameterizedTest
  @MethodSource("lengths")
  void shouldOnlyBeSpecifiedLength(int length) {
    // when
    byte[] actual = Fixtures.randomBytes(length);
    // then
    assertThat(actual.length, is(length));
  }
}
