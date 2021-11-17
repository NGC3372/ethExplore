package com.rainbowguo.ethexplore.Utils;

import android.animation.ObjectAnimator;
import android.view.View;

public class myAnimation {
    public static void rotationView(View view){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
        animator.setDuration(500);
        animator.start();
    }
}
