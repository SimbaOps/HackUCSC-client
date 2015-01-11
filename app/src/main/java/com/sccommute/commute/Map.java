package com.sccommute.commute;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Map extends ActionBarActivity {

    private static GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private static HashMap<String, Route> routes;

    private String cur = "16";

    private ActionMode mMode = null;
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.contex_route, menu);
            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_marker:
                    routes.get(cur).toggleStops(mMap);
                    return true;
                case R.id.action_watch:
                    Toast.makeText(Map.this, "Notifying Updates", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.action_x:
                    routes.get(cur).remove();
                    mode.finish();
                    mMode = null;
                    return true;
                default:
                    return false;
            }
        }

        // Called when the user exits the action mode
        @Override
        public void onDestroyActionMode(ActionMode mode) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        View listBtn =findViewById(R.id.listBtn);
        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Map.this, Schedule.class);
                startActivity(intent);
            }
        });
        View commuteBtn =findViewById(R.id.commuteBtn);
        commuteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Map.this, Commute.class);
                startActivity(intent);
            }
        });
        new UpdateMapFn().execute();
        setUpMapIfNeeded();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    @Override
    @TargetApi(11)
    public boolean onCreateOptionsMenu(Menu menu) {
        SearchView searchView;
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.search, menu);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            MenuItem searchItem = menu.findItem(R.id.action_search_view);
            searchView = (SearchView) searchItem.getActionView();
            SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
            ComponentName name = getComponentName();
            SearchableInfo info = searchManager.getSearchableInfo(name);
            searchView.setSearchableInfo(info);
            searchView.setSubmitButtonEnabled(true);
        }
        return true;
    }

    private void setUpMap() {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.9786339,-122.0440915), 13));
        mMap.setMyLocationEnabled(true);
        routes = RouteInit.initRoutes();
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                routes.get("16").drawLine(mMap);
                mMode = startSupportActionMode(mActionModeCallback);
                return true;
            }
        });
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
               if(routes.get("16").isDrawn()) {
                   routes.get("16").remove();
                   mMode.finish();
               }
            }
        });
    }



    static class UpdateMapFn extends AsyncTask<Void, Void, JSONArray> {

        static HashMap<String, Marker> markers = new HashMap<String, Marker>();

        @Override
        protected JSONArray doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                return new CloudController("http://ec2-54-67-127-40.us-west-1.compute.amazonaws.com")
                        .getLocations();
            } catch(Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        private BitmapDescriptor getIcon(String type) {
            switch(type) {
                case "20":
                    return BitmapDescriptorFactory.fromResource(R.drawable.route20);
                case "16":
                    return BitmapDescriptorFactory.fromResource(R.drawable.route16);
                case "10":
                    return BitmapDescriptorFactory.fromResource(R.drawable.route10);
                case "19":
                    return BitmapDescriptorFactory.fromResource(R.drawable.route19);
                default:
                    return BitmapDescriptorFactory.fromResource(R.drawable.route15);
            }
        }

        @Override
        protected void onPostExecute(JSONArray arr) {
            for(int i = 0; i < arr.length(); i++) {
                try {
                    JSONObject obj = arr.getJSONObject(i);
                    String objName = obj.getString("id");
                    Marker mark = markers.get(objName);
                    if(mark != null) {
                        mark.remove();
                        markers.remove(mark);
                    }
                    Marker marker = mMap.addMarker(new MarkerOptions().position(
                            new LatLng(obj.getDouble("lat"), obj.getDouble("lon")))
                            .title(objName).icon(getIcon(obj.getString("type"))));
                    markers.put(objName, marker);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
            new UpdateMapFn().execute();
        }

    }
}
