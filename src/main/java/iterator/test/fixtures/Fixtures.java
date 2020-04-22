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
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.math3.random.RandomDataGenerator;

public final class Fixtures {

  public static final List<String> ISO_4217_CODES =
      List.of(
          "AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN", "BAM", "BBD", "BDT",
          "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BOV", "BRL", "BSD", "BTN", "BWP", "BYN", "BZD",
          "CAD", "CDF", "CHE", "CHF", "CHW", "CLF", "CLP", "CNY", "COP", "COU", "CRC", "CUC", "CUP",
          "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EGP", "ERN", "ETB", "EUR", "FJD", "FKP", "GBP",
          "GEL", "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "IDR",
          "ILS", "INR", "IQD", "IRR", "ISK", "JMD", "JOD", "JPY", "KES", "KGS", "KHR", "KMF", "KPW",
          "KRW", "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LYD", "MAD", "MDL", "MGA",
          "MKD", "MMK", "MNT", "MOP", "MRU", "MUR", "MVR", "MWK", "MXN", "MXV", "MYR", "MZN", "NAD",
          "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP", "PKR", "PLN", "PYG",
          "QAR", "RON", "RSD", "RUB", "RWF", "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLL",
          "SOS", "SRD", "SSP", "STN", "SVC", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY",
          "TTD", "TWD", "TZS", "UAH", "UGX", "USD", "USN", "UYI", "UYU", "UZS", "VEF", "VND", "VUV",
          "WST", "XAF", "XAG", "XAU", "XBA", "XBB", "XBC", "XBD", "XCD", "XDR", "XOF", "XPD", "XPF",
          "XPT", "XSU", "XTS", "XUA", "XXX", "YER", "ZAR", "ZMW", "ZWL");

  public static final List<String> ISO_3216_CODES =
      List.of(
          "AF", "AX", "AL", "DZ", "AS", "AD", "AO", "AI", "AQ", "AG", "AR", "AM", "AW", "AU", "AT",
          "AZ", "BS", "BH", "BD", "BB", "BY", "BE", "BZ", "BJ", "BM", "BT", "BO", "BA", "BW", "BV",
          "BR", "IO", "BN", "BG", "BF", "BI", "KH", "CM", "CA", "CV", "KY", "CF", "TD", "CL", "CN",
          "CX", "CC", "CO", "KM", "CG", "CD", "CK", "CR", "CI", "HR", "CU", "CY", "CZ", "DK", "DJ",
          "DM", "DO", "EC", "EG", "SV", "GQ", "ER", "EE", "ET", "FK", "FO", "FJ", "FI", "FR", "GF",
          "PF", "TF", "GA", "GM", "GE", "DE", "GH", "GI", "GR", "GL", "GD", "GP", "GU", "GT", "GG",
          "GN", "GW", "GY", "HT", "HM", "VA", "HN", "HK", "HU", "IS", "IN", "ID", "IR", "IQ", "IE",
          "IM", "IL", "IT", "JM", "JP", "JE", "JO", "KZ", "KE", "KI", "KR", "KW", "KG", "LA", "LV",
          "LB", "LS", "LR", "LY", "LI", "LT", "LU", "MO", "MK", "MG", "MW", "MY", "MV", "ML", "MT",
          "MH", "MQ", "MR", "MU", "YT", "MX", "FM", "MD", "MC", "MN", "ME", "MS", "MA", "MZ", "MM",
          "NA", "NR", "NP", "NL", "AN", "NC", "NZ", "NI", "NE", "NG", "NU", "NF", "MP", "NO", "OM",
          "PK", "PW", "PS", "PA", "PG", "PY", "PE", "PH", "PN", "PL", "PT", "PR", "QA", "RE", "RO",
          "RU", "RW", "BL", "SH", "KN", "LC", "MF", "PM", "VC", "WS", "SM", "ST", "SA", "SN", "RS",
          "SC", "SL", "SG", "SK", "SI", "SB", "SO", "ZA", "GS", "ES", "LK", "SD", "SR", "SJ", "SZ",
          "SE", "CH", "SY", "TW", "TJ", "TZ", "TH", "TL", "TG", "TK", "TO", "TT", "TN", "TR", "TM",
          "TC", "TV", "UG", "UA", "AE", "GB", "US", "UM", "UY", "UZ", "VU", "VE", "VN", "VG", "VI",
          "WF", "EH", "YE", "ZM", "ZW");

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

  public static BigDecimal randomBigDecimal(final int scale, final RoundingMode mode) {
    return randomBigDecimal(Double.MIN_VALUE, Double.MAX_VALUE, scale, mode);
  }

  public static BigDecimal randomBigDecimal(
      final double min, final double max, final int scale, final RoundingMode mode) {
    return new BigDecimal("" + randomDouble(min, max)).setScale(scale, mode);
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

  public static <E> E randomCollectionElement(Collection<E> collection) {
    return List.copyOf(collection).get(randomInt(0, collection.size() - 1));
  }

  public static <E> E randomArrayElement(E[] arr) {
    return arr[randomInt(0, arr.length - 1)];
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
    return randomArrayElement(enumClass.getEnumConstants());
  }

  public static float randomFloat() {
    return randomFloat(Float.MIN_VALUE, Float.MAX_VALUE);
  }

  public static float randomFloat(float min, float max) {
    return (float) randomDouble(min, max);
  }

  public static Instant randomInstant() {
    return randomDate().toInstant();
  }

  public static Instant randomInstant(final Instant start, final Instant end) {
    return randomDate(Date.from(start), Date.from(end)).toInstant();
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

  public static String randomISO3166Alpha2CountryCode() {
    return randomCollectionElement(ISO_3216_CODES);
  }

  public static String randomISO4217CurrencyCode() {
    return randomCollectionElement(ISO_4217_CODES);
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
