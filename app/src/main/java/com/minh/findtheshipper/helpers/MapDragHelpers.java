package com.minh.findtheshipper.helpers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;
import com.minh.findtheshipper.utils.MapDragUtils;

/**
 * Created by trinh on 6/10/2017.
 */

public class MapDragHelpers extends SupportMapFragment {
    private View originView;
    private MapDragUtils mapDragUtils;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        originView = super.onCreateView(layoutInflater, viewGroup, bundle);
        mapDragUtils = new MapDragUtils(getActivity());
        mapDragUtils.addView(originView);
        return mapDragUtils;
    }

    @Nullable
    @Override
    public View getView() {
        return originView;
    }

    public void setOnDragListener(MapDragUtils.OnMapDragListener onMapDragListener)
    {
        mapDragUtils.setOnMapDragListener(onMapDragListener);
    }
}
