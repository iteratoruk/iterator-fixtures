package iterator.test.fixtures;

import static iterator.test.fixtures.Answers.withParameter;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AnswersTest {

  public interface MockedForAnswers {

    Object methodWithParams(String str, Integer i);
  }

  @Mock private MockedForAnswers mock;

  @Test
  void shouldReturnFirstParameterGivenZeroWhenWithParameter() {
    // given
    String str = Fixtures.randomAlphanumericString(8);
    Integer i = Fixtures.randomInt();
    given(mock.methodWithParams(str, i)).willAnswer(withParameter(0));
    // then
    assertThat(mock.methodWithParams(str, i), is(str));
  }

  @Test
  void shouldReturnSecondParameterGivenOneWhenWithParameter() {
    // given
    String str = Fixtures.randomAlphanumericString(8);
    Integer i = Fixtures.randomInt();
    given(mock.methodWithParams(str, i)).willAnswer(withParameter(1));
    // then
    assertThat(mock.methodWithParams(str, i), is(i));
  }

  @Test
  void shouldThrowGivenNonExistentParameterIndexWhenWithParameter() {
    // given
    String str = Fixtures.randomAlphanumericString(8);
    Integer i = Fixtures.randomInt();
    given(mock.methodWithParams(str, i)).willAnswer(withParameter(2));
    // when
    assertThrows(
        ArrayIndexOutOfBoundsException.class,
        () -> {
          mock.methodWithParams(str, i);
        });
  }
}
