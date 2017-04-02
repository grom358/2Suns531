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

    public static String plateCalculate(float w, String unit) {
        if (unit.equals("kg")) {
            final float[] plates = {20f, 15f, 10f, 5f, 2.5f, 1.25f};
            return plateCalculate(w, 20f, plates);
        } else {
            final float[] plates = {45f, 35f, 25f, 10f, 5f, 2.5f};
            return plateCalculate(w, 45f, plates);
        }
    }

    public static String plateCalculate(float w, final float barWeight, final float[] plates) {
        if (w == 0) {
            return "";
        }

        // Weight to load each side.
        float remaining = (w - barWeight) / 2f;

        boolean first = true;
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        // Use largest plates possible until all weight is loaded.
        for (float pw : plates) {
            int plateCount = (int) (remaining / pw);
            if (plateCount > 0) {
                if (!first) {
                    sb.append(", ");
                }
                first = false;
                remaining -= plateCount * pw;
                if (plateCount > 1) {
                    sb.append(plateCount);
                    sb.append(" x ");
                }
                sb.append(Util.format(pw));
            }
        }
        // No plates to load.
        if (first) {
            return "";
        }
        sb.append(']');
        return sb.toString();
    }
}
