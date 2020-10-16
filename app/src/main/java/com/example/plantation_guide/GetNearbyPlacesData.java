package com.example.plantation_guide;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetNearbyPlacesData extends AsyncTask <Object,String,String>{


    String googlePlaceData;
    GoogleMap mMap;
    String url;


    @Override
    protected String doInBackground(Object... objects) {

        mMap = (GoogleMap)objects[0];
        url =(String)objects[1];

        DownloadUrl downloadUrl =  new DownloadUrl();
        try {
            googlePlaceData = downloadUrl.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return googlePlaceData;
    }

    @Override
    protected void onPostExecute(String s) {
        List<HashMap<String,String>> nearbyPlaceList = null;
        DataParser parser = new DataParser();
        nearbyPlaceList = parser.parse(s);
        showNearbyPlaces(nearbyPlaceList);

    }

    private  void showNearbyPlaces(List<HashMap<String,String>> nearbyPlacesList){

        for(int i=0;i<nearbyPlacesList.size();i++){
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String ,String> googlePlaces = nearbyPlacesList.get(i);

            String placeName = googlePlaces.get("place_name");
            String vicinity = googlePlaces.get("vicinity");
            double lat = Double.parseDouble(googlePlaces.get("lat"));
            double lng = Double.parseDouble(googlePlaces.get("lng"));

            LatLng latLng = new LatLng(lat,lng);
            markerOptions.position(latLng);
            markerOptions.title(placeName+":"+vicinity);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        }
    }

}
