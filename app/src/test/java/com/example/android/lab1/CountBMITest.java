package com.example.android.lab1;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Created by lizaveta on 20.03.2017.
 */

public class CountBMITest {
    @Test
    public void massUnderZeroIsInvalid(){
        //GIVEN
        float textMan = -1.0f;

        //WHEN
        ICountBMI countBMI = new CountBMIforKgM();
        // THEN
        boolean anchor = countBMI.isValidWeight(textMan);
        assertFalse(anchor);
    }
    @Test
    public void countBMIIsInvalid(){
        //GIVEN
        float h = 1.65f;
        float w = 54.0f;

        //WHEN
        ICountBMI countBMI = new CountBMIforKgM();
        // THEN
        float bmi = countBMI.countBMI(h,w);
        float myBMI = w/(h*h);
        assertEquals(myBMI,bmi,0.0001f);
    }

}
