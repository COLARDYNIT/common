package org.hwbot.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberUtil {

    public static final NumberFormat pointsFormat = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
    public static final NumberFormat pctFormat = new DecimalFormat("+#.00%;-#.00%", new DecimalFormatSymbols(Locale.US));

    public static String getOrdinalSuffix(int number) {
        String suffix;
        if (number == 0) {
            suffix = "";
        } else if (number < 20 && number > 9) {
            suffix = "th";
        } else {
            number = number % 10;
            switch (number) {
            case 1:
                suffix = "st";
                break;
            case 2:
                suffix = "nd";
                break;
            case 3:
                suffix = "rd";
                break;
            default:
                suffix = "th";
                break;
            }
        }
        return suffix;
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
                return "<span class='green'>" + pctFormat.format(pct) + "</span>";
            } else if (pct < 0) {
                return "<span class='red'>" + pctFormat.format(pct) + "</span>";
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
