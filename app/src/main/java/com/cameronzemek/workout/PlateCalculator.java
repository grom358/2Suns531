package com.cameronzemek.workout;

import android.content.SharedPreferences;

/**
 * Calculator for plate loadings onto a barbell.
 */
public class PlateCalculator {
    public final static float[] DEFAULT_PLATE_WEIGHTS = {100f, 55f, 45f, 35f, 25f, 20f, 15f, 10f, 7.5f, 5f, 2.5f, 2f, 1.5f, 1.25f, 1f, 0.75f, 0.5f, 0.25f};
    public final static int[] DEFAULT_KG_PLATE_COUNTS = {0, 0, 0, 0, 0, 8, 2, 2, 0, 2, 2, 0, 0, 2, 0, 0, 0, 0};
    public final static int[] DEFAULT_LB_PLATE_COUNTS = {0, 0, 8, 2, 2, 2, 2, 2, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0};
    public final static int PLATE_LENGTH = DEFAULT_PLATE_WEIGHTS.length;

    private boolean enabled = true;

    private float kgBarWeight = 20f;
    private float[] kgPlateWeights = DEFAULT_PLATE_WEIGHTS;
    private int[] kgPlateCounts = DEFAULT_KG_PLATE_COUNTS;

    private float lbBarWeight = 45f;
    private float[] lbPlateWeights = DEFAULT_PLATE_WEIGHTS;
    private int[] lbPlateCounts = DEFAULT_LB_PLATE_COUNTS;

    public void setup(SharedPreferences sharedPreferences) {
        enabled = sharedPreferences.getBoolean("plate_calculator", true);
        kgPlateWeights = new float[PLATE_LENGTH];
        kgPlateCounts = new int[PLATE_LENGTH];
        lbPlateWeights = new float[PLATE_LENGTH];
        lbPlateCounts = new int[PLATE_LENGTH];

        kgBarWeight = sharedPreferences.getFloat("kg_bar_weight", 20f);
        lbBarWeight = sharedPreferences.getFloat("lb_bar_weight", 45f);

        for (int i = 0; i < PLATE_LENGTH; i++) {
            kgPlateWeights[i] = sharedPreferences.getFloat("kg_plate_weight_" + i, DEFAULT_PLATE_WEIGHTS[i]);
            kgPlateCounts[i] = sharedPreferences.getInt("kg_plate_count_" + i, DEFAULT_KG_PLATE_COUNTS[i]);
            lbPlateWeights[i] = sharedPreferences.getFloat("lb_plate_weight_" + i, DEFAULT_PLATE_WEIGHTS[i]);
            lbPlateCounts[i] = sharedPreferences.getInt("lb_plate_count_" + i, DEFAULT_LB_PLATE_COUNTS[i]);
        }
    }

    public String calculate(float w, String unit) {
        if (!enabled) {
            return "";
        }
        if (unit.equals("kg")) {
            return calculate(w, kgBarWeight, kgPlateWeights, kgPlateCounts);
        } else {
            return calculate(w, lbBarWeight, lbPlateWeights, lbPlateCounts);
        }
    }

    private String calculate(float w, final float barWeight, final float[] plateWeights, final int[] plateCounts) {
        if (w == 0) {
            return "";
        }

        // Weight to load each side.
        float remaining = (w - barWeight) / 2f;

        boolean first = true;
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        // Use largest plates possible until all weight is loaded.
        for (int i = 0; i < plateWeights.length; i++) {
            float pw = plateWeights[i];
            int plateCount = (int) (remaining / pw);
            // Cap plate count to maximum number available.
            if (plateCount > plateCounts[i]) {
                plateCount = plateCounts[i];
            }
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
