package com.example.android.lab1;

/**
 * Created by lizaveta on 09.04.2017.
 */

public class CountBMIforInPounds implements ICountBMI {
    static final float MIN_H = 1.6f;
    static final float MIN_W = 5.5f;
    static final float MAX_H = 32f;
    static final float MAX_W = 550f;
    @Override
    public boolean isValidHeight(float h) {
        return h>MIN_H&&h<MAX_H;
    }

    @Override
    public boolean isValidWeight(float w) {
        return w>MIN_W&&w<MAX_W;
    }

    @Override
    public float countBMI(float h, float w) {
        if (isValidWeight(w)==false || isValidHeight(h)==false) throw new IllegalArgumentException();
        return w*4.88f/(h*h);
    }
}
