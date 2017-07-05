package com.example.android.lab1;
/**
 * Created by lizaveta on 20.03.2017.
 */

public class CountBMIforKgM implements ICountBMI {

    static final float MIN_H = 0.5f;
    static final float MIN_W = 2.5f;
    static final float MAX_H = 10f;
    static final float MAX_W = 250f;


    public boolean isValidHeight(float h) {
        return h>MIN_H && h<MAX_H;
    }

    @Override
    public boolean isValidWeight(float w) {
        return w>MIN_W && w<MAX_W;
    }

    @Override
    public float countBMI(float h, float w) {
        if (isValidHeight(h)==false || isValidWeight(w)==false) throw new IllegalArgumentException();
        return w/(h*h);
    }
}
