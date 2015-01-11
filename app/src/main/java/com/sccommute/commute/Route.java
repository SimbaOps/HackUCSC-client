package com.sccommute.commute;

import android.graphics.Color;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

/**
 * Created by simba on 1/11/15.
 */
public class Route {
    private ArrayList<LatLng> polyList;
    private ArrayList<LatLng> stops;

    private ArrayList<Marker> drawnMarks = new ArrayList<Marker>();
    private ArrayList<Polyline> drawnLines = new ArrayList<Polyline>();
    private boolean drawn = false;
    private boolean stopsDrawn = false;

    public Route(String encodedPoly, ArrayList<LatLng> stops) {
        this.stops = stops;
        polyList = decodePoly(encodedPoly);
    }

    public void drawAll(GoogleMap map) {
        if(!drawn) {
            drawn = true;
            stopsDrawn = true;
            PolylineOptions opts = new PolylineOptions();
            opts.addAll(polyList).width(5).color(Color.RED);
            drawnLines.add(map.addPolyline(opts));
            for(int i = 0; i < stops.size(); i++) {
                drawnMarks.add(map.addMarker(new MarkerOptions().position(stops.get(i))));
            }
        }
    }

    public void drawLine(GoogleMap map) {
            drawn = true;
            PolylineOptions opts = new PolylineOptions();
            opts.addAll(polyList).width(5).color(Color.RED);
            drawnLines.add(map.addPolyline(opts));
    }

    public void drawStops(GoogleMap map) {
            drawn = true;
            stopsDrawn = true;
            for(int i = 0; i < stops.size(); i++) {
                drawnMarks.add(map.addMarker(new MarkerOptions().position(stops.get(i))));
            }
    }

    public void toggleStops(GoogleMap map) {
        if(stopsDrawn) {
            for(int i = 0; i < drawnMarks.size(); i++) {
                drawnMarks.get(i).remove();
            }
            stopsDrawn = false;
        } else {
            for(int i = 0; i < stops.size(); i++) {
                drawnMarks.add(map.addMarker(new MarkerOptions().position(stops.get(i))));
            }
            stopsDrawn = true;
        }
    }

    public void remove() {
        for(int i = 0; i < drawnLines.size(); i++) {
            drawnLines.get(i).remove();
        }
        for(int i = 0; i < drawnMarks.size(); i++) {
            drawnMarks.get(i).remove();
        }
        drawn = false;
    }

    public boolean isDrawn() {
        return drawn;
    }

    private ArrayList<LatLng> decodePoly(String encoded) {

        Log.i("Location", "String received: " + encoded);
        ArrayList<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),(((double) lng / 1E5)));
            poly.add(p);
        }

        for(int i=0;i<poly.size();i++){
            Log.i("Location", "Point sent: Latitude: " + poly.get(i).latitude + " Longitude: " + poly.get(i).longitude);
        }
        return poly;
    }
}
