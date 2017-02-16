package com.cameronzemek.workout;

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
}
