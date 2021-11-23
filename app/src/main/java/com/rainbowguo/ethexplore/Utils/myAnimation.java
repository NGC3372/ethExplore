package com.rainbowguo.ethexplore.Utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;

public class myAnimation {
    public static void rotationView(View view){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
        animator.setDuration(500);
        animator.start();
    }

    public static void SmallToBig(View view){
        PropertyValuesHolder ValuesHolder1 = PropertyValuesHolder.ofFloat("scaleX",0,1);
        PropertyValuesHolder ValuesHolder2 = PropertyValuesHolder.ofFloat("scaleY",0,1);
        PropertyValuesHolder ValuesHolder3 = PropertyValuesHolder.ofFloat("alpha",0,1);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, ValuesHolder1,ValuesHolder2, ValuesHolder3);
        objectAnimator.setDuration(300);
        objectAnimator.start();
    }

    public static void SmallToBig_listener(View view,AnimatorListenerAdapter adapter){
        PropertyValuesHolder ValuesHolder1 = PropertyValuesHolder.ofFloat("scaleX",0,1);
        PropertyValuesHolder ValuesHolder2 = PropertyValuesHolder.ofFloat("scaleY",0,1);
        PropertyValuesHolder ValuesHolder3 = PropertyValuesHolder.ofFloat("alpha",0,1);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, ValuesHolder1,ValuesHolder2, ValuesHolder3);
        objectAnimator.setDuration(300);
        objectAnimator.setStartDelay(100);
        objectAnimator.addListener(adapter);
        objectAnimator.start();
    }

}
