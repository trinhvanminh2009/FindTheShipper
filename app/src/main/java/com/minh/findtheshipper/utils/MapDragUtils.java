package com.minh.findtheshipper.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by trinh on 6/10/2017.
 */

public class MapDragUtils extends LinearLayout {

    public interface OnMapDragListener{
        public void onDrag(MotionEvent motionEvent);
    }

    private OnMapDragListener onMapDragListener;

    public MapDragUtils(@NonNull Context context) {
        super(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(onMapDragListener != null)
        {
            onMapDragListener.onDrag(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setOnMapDragListener(OnMapDragListener onMapDragListener){
        this.onMapDragListener = onMapDragListener;
    }
}
