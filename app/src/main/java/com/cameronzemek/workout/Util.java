package com.cameronzemek.workout;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Util {
    public static float roundUp(float w, float p) {
        return (float) Math.ceil(w / p) * p;
    }

    public static float roundDown(float w, float p) {
        return (float) Math.floor(w / p) * p;
    }

    public static float round(float w, float p) {
        return (float) Math.round(w / p) * p;
    }

    private static final DecimalFormat df = new DecimalFormat("#.##");

    public static String format(float f) {
        return df.format(f);
    }

    public static float parseFloat(String str) {
        char decimalSeparator = DecimalFormatSymbols.getInstance().getDecimalSeparator();
        if (decimalSeparator != '.') {
            str = str.replace(decimalSeparator, '.');
        }
        str = str.replaceAll("[^\\d\\.]", "");
        return Float.valueOf(str);
    }

    public static int parseInt(String str) {
        str = str.replaceAll("[^\\d]", "");
        return Integer.valueOf(str);
    }

    public final static PlateCalculator plateCalculator = new PlateCalculator();
}
