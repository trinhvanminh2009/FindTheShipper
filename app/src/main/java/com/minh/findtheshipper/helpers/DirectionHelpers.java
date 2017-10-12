package com.minh.findtheshipper.helpers;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;
import com.minh.findtheshipper.helpers.listeners.DirectionFinderListeners;
import com.minh.findtheshipper.models.Distance;
import com.minh.findtheshipper.models.Duration;
import com.minh.findtheshipper.models.Route;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by trinh on 6/8/2017.
 * To support about Direction
 */

public class DirectionHelpers {
    private static final String DIRECTION_URL_API = "https://maps.googleapis.com/maps/api/directions/json?";
    private static final String GOOGLE_API_SERVER_KEY = "AIzaSyC1Ze50Q_hdWX2sIREwvAClrroISETmq-s";
    private DirectionFinderListeners directionFinderListeners;
    private String origin;
    private String destination;

    public DirectionHelpers(DirectionFinderListeners directionFinderListeners, String origin, String destination) {
        this.directionFinderListeners = directionFinderListeners;
        this.origin = origin;
        this.destination = destination;
    }

    public void execute() throws UnsupportedEncodingException {
        directionFinderListeners.onDirectionFinderStart();
        new DownloadRawDataHelpers().execute(createURL());


    }

    private String createURL() throws UnsupportedEncodingException {
        String urlOrigin = URLEncoder.encode(origin, "utf-8");
        String urlDestination = URLEncoder.encode(destination, "utf-8");
        return DIRECTION_URL_API + "origin=" + urlOrigin + "&destination=" + urlDestination + "&key=" + GOOGLE_API_SERVER_KEY;

    }


    private void parseJson(String data) throws JSONException {
        if (data == null) {
            return;
        } else {
            List<Route> routes = new ArrayList<>();
            JSONObject jsonData = new JSONObject(data);
            JSONArray jsonRoutes = jsonData.getJSONArray("routes");
            for (int i = 0; i < jsonRoutes.length(); i++) {
                JSONObject jsonRoute = jsonRoutes.getJSONObject(i);
                Route route = new Route();

                JSONObject overviewPolyline = jsonRoute.getJSONObject("overview_polyline");
                JSONArray jsonLegs = jsonRoute.getJSONArray("legs");
                JSONObject jsonLeg = jsonLegs.getJSONObject(0);
                JSONObject jsonDistance = jsonLeg.getJSONObject("distance");
                JSONObject jsonDuration = jsonLeg.getJSONObject("duration");
                JSONObject jsonStartLocation = jsonLeg.getJSONObject("start_location");
                JSONObject jsonEndLocation = jsonLeg.getJSONObject("end_location");


                route.distance = new Distance(jsonDistance.getString("text"), jsonDistance.getInt("value"));
                route.duration = new Duration(jsonDuration.getString("text"), jsonDuration.getInt("value"));
                route.startAddress = jsonLeg.getString("start_address");
                route.endAddress = jsonLeg.getString("end_address");
                route.startLocation = new LatLng(jsonStartLocation.getDouble("lat"), jsonStartLocation.getDouble("lng"));
                route.endLocation = new LatLng(jsonEndLocation.getDouble("lat"), jsonEndLocation.getDouble("lng"));
                route.points = PolyUtil.decode(overviewPolyline.getString("points"));
                routes.add(route);
            }
            directionFinderListeners.onDirectionFinderSuccess(routes);
        }

    }

    private class DownloadRawDataHelpers extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String link = params[0];
            try {
                URL url = new URL(link);
                InputStream inputStream = url.openConnection().getInputStream();
                StringBuffer buffer = new StringBuffer();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                parseJson(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
