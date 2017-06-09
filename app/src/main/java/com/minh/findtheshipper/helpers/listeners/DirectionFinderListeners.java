package com.minh.findtheshipper.helpers.listeners;

import com.minh.findtheshipper.models.Route;

import java.util.List;

/**
 * Created by trinh on 6/8/2017.
 */

public interface DirectionFinderListeners {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> routes);
}
