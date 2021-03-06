/**
 * Copyright © 2016 Iterator Ltd. (iteratoruk@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package iterator.test.fixtures;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public final class Answers {

  private static class ReturnsParameterIndexAnswer<T> implements Answer<T> {

    private final int i;

    ReturnsParameterIndexAnswer(int i) {
      this.i = i;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T answer(InvocationOnMock invocation) throws Throwable {
      return (T) invocation.getArguments()[i];
    }
  }

  public static <T> Answer<T> withParameter(int i) {
    return new ReturnsParameterIndexAnswer<>(i);
  }

  private Answers() {
    throw new IllegalStateException();
  }
}
