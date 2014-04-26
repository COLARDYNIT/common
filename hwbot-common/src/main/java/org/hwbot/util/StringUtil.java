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
}
