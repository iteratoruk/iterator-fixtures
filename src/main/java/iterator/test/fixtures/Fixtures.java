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

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.apache.commons.math3.random.RandomDataGenerator;

public final class Fixtures {

  private static final int[] LOWER_ALPHA_RANGE = {97, 122};

  private static final int[] NUMERIC_RANGE = {48, 57};

  private static final RandomDataGenerator RND = new RandomDataGenerator();

  private static final int[] UPPER_ALPHA_RANGE = {65, 90};

  public static BigDecimal randomBigDecimal(
      final double min,
      final double max,
      final int precision,
      final int scale,
      final RoundingMode mode) {
    return new BigDecimal("" + randomDouble(min, max), new MathContext(precision, mode))
        .setScale(scale, mode);
  }

  public static BigDecimal randomBigDecimal(
      final int precision, final int scale, final RoundingMode mode) {
    return randomBigDecimal(Double.MIN_VALUE, Double.MAX_VALUE, precision, scale, mode);
  }

  public static boolean randomBoolean() {
    return randomInt() % 2 == 0;
  }

  public static byte[] randomBytes(final int length) {
    byte[] bytes = new byte[length];
    for (int i = 0; i < length; i++) {
      // we just use byte values for alphanumeric characters at present as this helps with random
      // string/email/url generation etc
      bytes[i] = (byte) randomAlphanumericCharacterIndex();
    }
    return bytes;
  }

  public static Date randomDate() {
    return randomDate(new Date(0L), new Date(System.currentTimeMillis()));
  }

  public static Date randomDate(final Date start, final Date end) {
    return new Date(randomLong(start.getTime(), end.getTime()));
  }

  public static double randomDouble() {
    return randomDouble(Double.MIN_VALUE, Double.MAX_VALUE);
  }

  public static double randomDouble(final double min, final double max) {
    return min == max ? min : RND.nextUniform(min, max);
  }

  public static <E extends Enum<E>> E randomEnum(final Class<E> enumClass) {
    return randomEnum(enumClass, 0, enumClass.getEnumConstants().length - 1);
  }

  public static <E extends Enum<E>> E randomEnum(final Class<E> enumClass, int min, int max) {
    return enumClass.getEnumConstants()[randomInt(min, max)];
  }

  public static float randomFloat() {
    return randomFloat(Float.MIN_VALUE, Float.MAX_VALUE);
  }

  public static float randomFloat(float min, float max) {
    return (float) randomDouble(min, max);
  }

  public static int randomInt() {
    return randomInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  public static int randomInt(final int min, final int max) {
    // commons-math3 is inconsistent:
    // nextInt does not throw if min == max whereas nextLong, nextUniform etc do
    // therefore, we don't need to handle min == max here
    return RND.nextInt(min, max);
  }

  public static long randomLong() {
    return randomLong(Long.MIN_VALUE, Long.MAX_VALUE);
  }

  public static long randomLong(final long min, final long max) {
    return min == max ? min : RND.nextLong(min, max);
  }

  public static String randomAlphanumericString(final int length) {
    return new String(randomBytes(length), StandardCharsets.UTF_8);
  }

  private static int randomAlphanumericCharacterIndex() {
    int[] options = {
      randomInt(LOWER_ALPHA_RANGE[0], LOWER_ALPHA_RANGE[1]),
      randomInt(UPPER_ALPHA_RANGE[0], UPPER_ALPHA_RANGE[1]),
      randomInt(NUMERIC_RANGE[0], NUMERIC_RANGE[1])
    };
    return options[randomInt(0, 2)];
  }

  private Fixtures() {
    throw new IllegalStateException();
  }
}
