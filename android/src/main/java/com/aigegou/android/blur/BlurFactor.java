package com.aigegou.android.blur;

import android.graphics.Color;

/**
 * Created by herbert on 4/10/16.
 */


public class BlurFactor {

    public static final int DEFAULT_RADIUS = 25;
    public static final int DEFAULT_SAMPLING = 1;

    public int width = 200;
    public int height = 200;
    public int radius = DEFAULT_RADIUS;
    public int sampling = DEFAULT_SAMPLING;
    public int color = Color.TRANSPARENT;
}