package org.hwbot.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;

public class StringUtil {
    public static boolean isEmpty(String field) {
        return !isNotEmpty(field);
    }

    public static boolean isNotEmpty(String field) {
        if (field == null || "".equals(field)) {
            return false;
        } else {
            return true;
        }
    }

    public static String encode(String name) {
        try {
            return URLEncoder.encode(name, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String makeUrlSafe(String name) {
        return makeUrlSafe(name, true);
    }

    public static String unmakeUrlSafe(String name) {
        return name.replaceAll("_", " ");
    }

    public static String makeUrlSafe(String name, boolean lowercase) {
        if (name == null) {
            return null;
        }
        if (lowercase) {
            name = name.toLowerCase();
        }
        StringBuffer safe = new StringBuffer();
        int sz = name.length();
        for (int i = 0; i < sz; i++) {
            char character = name.charAt(i);
            if (Character.isLetter(character) || Character.isDigit(character)) {
                safe.append(character);
            } else if (Character.isWhitespace(character)) {
                safe.append("_");
            } else if ('|' == character) {
                safe.append("i");
            } else if ('@' == character) {
                safe.append("a");
            } else if ('_' == character) {
                safe.append(character);
            } else if ('-' == character) {
                safe.append("_");
            } else if ('.' == character) {
                safe.append(character);
            } else if ('(' == character || ')' == character) {
                safe.append(character);
            } else if ('&' == character) {
                safe.append("_and_");
            } else {
                // ignore
            }
        }
        return safe.toString();
    }

    public static String makeUrlSafe(String name, int maxLength) {
        String safeUrl = makeUrlSafe(name);
        return safeUrl.length() > maxLength ? safeUrl.substring(0, maxLength) : safeUrl;
    }

    public static String getDayOfMonthSuffix(long time) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date(time));
        int n = instance.get(Calendar.DAY_OF_MONTH);
        return getNumberSuffix(n);
    }

    public static String getNumberSuffix(int n) {
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
        case 1:
            return "st";
        case 2:
            return "nd";
        case 3:
            return "rd";
        default:
            return "th";
        }
    }

    public static String escapeLtAndGt(String input) {
        input = input.replaceAll("<", "&lt;");
        return input.replaceAll(">", "&gt;");
    }
    
    /**
     * <p>Abbreviates a String using ellipses. This will turn
     * "Now is the time for all good men" into "Now is the time for..."</p>
     *
     * <p>Specifically:
     * <ul>
     *   <li>If {@code str} is less than {@code maxWidth} characters
     *       long, return it.</li>
     *   <li>Else abbreviate it to {@code (substring(str, 0, max-3) + "...")}.</li>
     *   <li>If {@code maxWidth} is less than {@code 4}, throw an
     *       {@code IllegalArgumentException}.</li>
     *   <li>In no case will it return a String of length greater than
     *       {@code maxWidth}.</li>
     * </ul>
     * </p>
     *
     * <pre>
     * StringUtils.abbreviate(null, *)      = null
     * StringUtils.abbreviate("", 4)        = ""
     * StringUtils.abbreviate("abcdefg", 6) = "abc..."
     * StringUtils.abbreviate("abcdefg", 7) = "abcdefg"
     * StringUtils.abbreviate("abcdefg", 8) = "abcdefg"
     * StringUtils.abbreviate("abcdefg", 4) = "a..."
     * StringUtils.abbreviate("abcdefg", 3) = IllegalArgumentException
     * </pre>
     *
     * @param str  the String to check, may be null
     * @param maxWidth  maximum length of result String, must be at least 4
     * @return abbreviated String, {@code null} if null String input
     * @throws IllegalArgumentException if the width is too small
     * @since 2.0
     */
    public static String abbreviate(String str, int maxWidth) {
        return abbreviate(str, 0, maxWidth);
    }
    
    /**
     * <p>Abbreviates a String using ellipses. This will turn
     * "Now is the time for all good men" into "...is the time for..."</p>
     *
     * <p>Works like {@code abbreviate(String, int)}, but allows you to specify
     * a "left edge" offset.  Note that this left edge is not necessarily going to
     * be the leftmost character in the result, or the first character following the
     * ellipses, but it will appear somewhere in the result.
     *
     * <p>In no case will it return a String of length greater than
     * {@code maxWidth}.</p>
     *
     * <pre>
     * StringUtils.abbreviate(null, *, *)                = null
     * StringUtils.abbreviate("", 0, 4)                  = ""
     * StringUtils.abbreviate("abcdefghijklmno", -1, 10) = "abcdefg..."
     * StringUtils.abbreviate("abcdefghijklmno", 0, 10)  = "abcdefg..."
     * StringUtils.abbreviate("abcdefghijklmno", 1, 10)  = "abcdefg..."
     * StringUtils.abbreviate("abcdefghijklmno", 4, 10)  = "abcdefg..."
     * StringUtils.abbreviate("abcdefghijklmno", 5, 10)  = "...fghi..."
     * StringUtils.abbreviate("abcdefghijklmno", 6, 10)  = "...ghij..."
     * StringUtils.abbreviate("abcdefghijklmno", 8, 10)  = "...ijklmno"
     * StringUtils.abbreviate("abcdefghijklmno", 10, 10) = "...ijklmno"
     * StringUtils.abbreviate("abcdefghijklmno", 12, 10) = "...ijklmno"
     * StringUtils.abbreviate("abcdefghij", 0, 3)        = IllegalArgumentException
     * StringUtils.abbreviate("abcdefghij", 5, 6)        = IllegalArgumentException
     * </pre>
     *
     * @param str  the String to check, may be null
     * @param offset  left edge of source String
     * @param maxWidth  maximum length of result String, must be at least 4
     * @return abbreviated String, {@code null} if null String input
     * @throws IllegalArgumentException if the width is too small
     * @since 2.0
     */
    public static String abbreviate(String str, int offset, int maxWidth) {
        if (str == null) {
            return null;
        }
        if (maxWidth < 4) {
            throw new IllegalArgumentException("Minimum abbreviation width is 4");
        }
        if (str.length() <= maxWidth) {
            return str;
        }
        if (offset > str.length()) {
            offset = str.length();
        }
        if (str.length() - offset < maxWidth - 3) {
            offset = str.length() - (maxWidth - 3);
        }
        final String abrevMarker = "...";
        if (offset <= 4) {
            return str.substring(0, maxWidth - 3) + abrevMarker;
        }
        if (maxWidth < 7) {
            throw new IllegalArgumentException("Minimum abbreviation width with offset is 7");
        }
        if (offset + maxWidth - 3 < str.length()) {
            return abrevMarker + abbreviate(str.substring(offset), maxWidth - 3);
        }
        return abrevMarker + str.substring(str.length() - (maxWidth - 3));
    }
}
