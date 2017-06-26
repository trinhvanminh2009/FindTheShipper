package com.minh.findtheshipper.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationSet;

/**
 * Created by trinh on 6/25/2017.
 */

public class AnimationUtils  {
    public static void animate(RecyclerView.ViewHolder holder, boolean goesDown)
    {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorTranslateX = ObjectAnimator.ofFloat(holder.itemView, "translationX", goesDown? 300:-300,0);
        animatorTranslateX.setDuration(1000);

        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(holder.itemView,"translationY",-50,50,-30,30,-20,20,-5,5,0);
        animatorTranslateY.setDuration(1000);
        //animatorSet.playTogether(animatorTranslateX, animatorTranslateY);
        animatorSet.playTogether(animatorTranslateY,animatorTranslateX);
        animatorSet.start();

    }
}
