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
        animatorSet.playTogether(animatorTranslateY,animatorTranslateX);
        animatorSet.start();

    }

    public static void animateItem(RecyclerView.ViewHolder holder)
    {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(holder.itemView,"translationY",-100,100,-70,70,-50,50,-20,20,-10,10,-5,5,0);
        animatorTranslateY.setDuration(1000);
        animatorSet.playTogether(animatorTranslateY);
        animatorSet.start();
    }
}
