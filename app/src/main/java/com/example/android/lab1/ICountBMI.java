package com.example.android.lab1;

/**
 * Created by lizaveta on 20.03.2017.
 */

public interface ICountBMI {
    boolean isValidHeight(float h);
    boolean isValidWeight(float w);
    float countBMI(float h, float w);


}
