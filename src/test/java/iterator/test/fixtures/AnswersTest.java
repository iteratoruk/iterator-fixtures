
package iterator.test.fixtures;

import static iterator.test.fixtures.Answers.withParameter;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class AnswersTest {

    public static interface MockedForAnswers {

        Object methodWithParams(String str, Integer i);

    }

    @Mock
    private MockedForAnswers mock;

    @Before
    public void setup() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldReturnFirstParameterGivenZeroWhenWithParameter() throws Exception {
        // given
        String str = Fixtures.randomAlphanumericString(8);
        Integer i = Fixtures.randomInt();
        given(mock.methodWithParams(str, i)).willAnswer(withParameter(0));
        // then
        assertThat(mock.methodWithParams(str, i), is(str));
    }

    @Test
    public void shouldReturnSecondParameterGivenOneWhenWithParameter() throws Exception {
        // given
        String str = Fixtures.randomAlphanumericString(8);
        Integer i = Fixtures.randomInt();
        given(mock.methodWithParams(str, i)).willAnswer(withParameter(1));
        // then
        assertThat(mock.methodWithParams(str, i), is(i));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowGivenNonExistentParameterIndexWhenWithParameter() throws Exception {
        // given
        String str = Fixtures.randomAlphanumericString(8);
        Integer i = Fixtures.randomInt();
        given(mock.methodWithParams(str, i)).willAnswer(withParameter(2));
        // when
        mock.methodWithParams(str, i);
    }

}
