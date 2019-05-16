/*
 * Copyright 2019 ZetaSQL Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.google.zetasql;

import com.google.common.base.Preconditions;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.joda.time.DateTime;

/**
 * A utility class helps decorate strings
 */
public class ZetaSQLStrings {
  private static final DateTime EPOCH = new DateTime(1970, 1, 1, 0, 0);
  private static final Charset UTF_8 = StandardCharsets.UTF_8;

  /**
   * Convert a string to a ZetaSQL identifier literal.
   * The output will be quoted (with backticks) and escaped if necessary.
   * @param str String to be converted to identifier.
   * @return Legal ZetaSQL identifier converted from the string.
   */
  public static String toIdentifierLiteral(String str) {
    return "`" + str + "`";
  }

  /**
   * Return a quoted and escaped ZetaSQL bytes literal for this byte array.
   * Prefixes with b and may choose to quote with ' or " to produce nicer
   * output.
   *
   * @param bytes Binary bytes to escape
   * @return Quoted and escaped ZetaSQL bytes literal.
   */
  public static String toBytesLiteral(byte[] bytes) {
    throw new UnsupportedOperationException();
  }

  /**
   * Return a quoted and escaped ZetaSQL bytes literal for this string.
   * Prefixes with b and may choose to quote with ' or " to produce nicer
   * output.
   *
   * @param str String to escape
   * @return Quoted and escaped ZetaSQL bytes literal.
   */
  public static String toBytesLiteral(String str) {
    return toBytesLiteral(str.getBytes(UTF_8));
  }

  /**
   * Return a quoted and escaped ZetaSQL bytes literal for this byte array.
   * Prefixes with b and always uses single quotes.
   *
   * @param bytes Binary bytes to escape
   * @return Quoted and escaped ZetaSQL bytes literal.
   */
  public static String toSingleQuotedBytesLiteral(byte[] bytes) {
    throw new UnsupportedOperationException();
  }

  /**
   * Return a quoted and escaped ZetaSQL bytes literal for this string.
   * Prefixes with b and always uses single quotes.
   *
   * @param str String to escape
   * @return Quoted and escaped ZetaSQL bytes literal.
   */
  public static String toSingleQuotedBytesLiteral(String str) {
    return toSingleQuotedBytesLiteral(str.getBytes(UTF_8));
  }

  /**
   * Return a quoted and escaped ZetaSQL bytes literal for this byte array.
   * Prefixes with b and always uses double quotes.
   *
   * @param bytes Binary bytes to escape
   * @return Quoted and escaped ZetaSQL bytes literal.
   */
  public static String toDoubleQuotedBytesLiteral(byte[] bytes) {
    throw new UnsupportedOperationException();
  }

  /**
   * Return a quoted and escaped ZetaSQL bytes literal for this string.
   * Prefixes with b and always uses double quotes.
   *
   * @param str String to escape
   * @return Quoted and escaped ZetaSQL bytes literal.
   */
  public static String toDoubleQuotedBytesLiteral(String str) {
    return toDoubleQuotedBytesLiteral(str.getBytes(UTF_8));
  }

  /**
   * Return a quoted and escaped ZetaSQL string literal for this string.
   * May choose to quote with ' or " to produce nicer output.
   *
   * @param str String to escape
   * @return Quoted and escaped ZetaSQL string literal.
   */
  public static String toStringLiteral(String str) {
    return toDoubleQuotedStringLiteral(str);
  }

  /**
   * Return a quoted and escaped ZetaSQL string literal for this string.
   * Always uses single quotes.
   *
   * @param str String to escape
   * @return Quoted and escaped ZetaSQL string literal.
   */
  public static String toSingleQuotedStringLiteral(String str) {
    return "\'" + str + "\'";
  }

  /**
   * Return a quoted and escaped ZetaSQL string literal for this string.
   * Always uses double quotes.
   *
   * @param str String to escape
   * @return Quoted and escaped ZetaSQL string literal.
   */
  public static String toDoubleQuotedStringLiteral(String str) {
    return "\"" + str + "\"";
  }

  /**
   * @param date  Number of days since 1970-01-01
   * @return A string of format "YYYY-MM-DD" for this date
   */
  public static String convertDateToString(int date) {
    DateTime d = EPOCH.plusDays(date);
    return String.format("%04d-%02d-%02d", d.getYear(), d.getMonthOfYear(), d.getDayOfMonth());
  }

  public static String convertSimpleValueToString(Value value, boolean verbose) {
    Type type = value.getType();
    Preconditions.checkArgument(type.isSimpleType());
    if (value.isNull())
      return "null";
    switch (type.getKind()) {
      case TYPE_STRING:
        return value.getStringValue();
      case TYPE_INT32:
        return Integer.toString(value.getInt32Value());
      case TYPE_INT64:
        return Long.toString(value.getInt64Value());
      case TYPE_BOOL:
        return Boolean.toString(value.getBoolValue());
      case TYPE_FLOAT:
        return Float.toString(value.getFloatValue());
      case TYPE_DOUBLE:
        return Double.toString(value.getDoubleValue());
      case TYPE_BYTES:
      case TYPE_DATE:
      case TYPE_TIMESTAMP:
      case TYPE_TIME:
      case TYPE_DATETIME:
      case TYPE_NUMERIC:
      case TYPE_UINT32:
      case TYPE_UINT64:
    }

    throw new UnsupportedOperationException();
  }

  /**
   * Return an unescaped ZetaSQL identifier for this string.
   * Return {@code null} if the string is not a valid ZetaSQL identifier.
   *
   * Examples:
   * {@code unescapeIdentifier("foo")} is {@code "foo"}.
   * {@code unescapeIdentifier("`foo.bar`")} is {@code "foo.bar"}.
   * {@code unescapeIdentifier("3foo")} is {@code null}.
   *
   * @param str ZetaSQL identifier to unescape
   * @return Unescaped ZetaSQL identifier or {@code null}.
   */
  public static String unescapeIdentifier(String str) {
    throw new UnsupportedOperationException();
  }
}
