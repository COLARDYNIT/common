package org.hwbot.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberUtil {

    public static final NumberFormat pointsFormat = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
    public static final NumberFormat pctFormat = new DecimalFormat("+#.00%;-#.00%", new DecimalFormatSymbols(Locale.US));

    public static String getOrdinalSuffix(int number) {
        if (number == 0) {
            return "";
        } else {
            String[] suffixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
            switch (number % 100) {
                case 11:
                case 12:
                case 13:
                    return number + "th";
                default:
                    return number + suffixes[number % 10];
            }
        }
    }

    public static String formatChange(float change) {
        String text;
        if (change > 0)
            text = "+" + format(change);
        else if (change < 0)
            text = "-" + format(change);
        else
            text = "~";
        return text;
    }

    public static String format(float points) {
        return pointsFormat.format(points);
    }

    public static String format(Object points) {
        return pointsFormat.format(points);
    }

    public static String formatPct(Float pct) {
        if (pct != null) {
            if (pct > 0) {
                return "<span class='positive'>" + pctFormat.format(pct) + "</span>";
            } else if (pct < 0) {
                return "<span class='negative'>" + pctFormat.format(pct) + "</span>";
            } else {
                return "stock";
            }
        } else {
            return "stock";
        }
    }

    public static boolean isEqual(Number points1, Number points2, float allowedDifference) {
        if (points1 == points2) {
            return true;
        } else if (points1 == null) {
            return false;
        } else if (points2 == null) {
            return false;
        } else if (Math.abs(points1.floatValue() - points2.floatValue()) <= allowedDifference) {
            return true;
        } else {
            return false;
        }
    }

    public static int findMostOccuringVal(final int[] n) {
        int maxKey =  n[0];
        int maxCount = 1;
        int curKey = n[0];
        int curCount = 1;

        for (int i=1; i < n.length; i++) {
            if (n[i] == curKey) {
                curCount++;
            } else {
                if (curCount > maxCount) {
                    maxCount = curCount;
                    maxKey = curKey;
                }
                curKey = n[i];
                curCount = 1;
            }
        }
        return maxKey;
    }
}
