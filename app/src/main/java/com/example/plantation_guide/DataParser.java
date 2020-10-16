package com.example.plantation_guide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataParser {

    private HashMap<String,String>getPlace(JSONObject googlePlaceJson) {

        HashMap<String, String> googlePlaceMap = new HashMap<>();
        String placeName = "-NA-";
        String vicinity = "-NA-";
        String latitude = "";
        String longitude = "";
        String refrence = "";

        try {
            if (!googlePlaceJson.isNull("name")) {

                placeName = googlePlaceJson.getString("name");
            }
            if(!googlePlaceJson.isNull("vicinity")){

                vicinity = googlePlaceJson.getString("vicinity");
            }
            latitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lng");
            refrence = googlePlaceJson.getString("refrence");

            googlePlaceMap.put("place_name",placeName);
            googlePlaceMap.put("vacinity",vicinity);
            googlePlaceMap.put("lat",latitude);
            googlePlaceMap.put("lng",longitude);
            googlePlaceMap.put("refrence",refrence);



        } catch (JSONException e) {
            e.printStackTrace();
        }
         return googlePlaceMap;

    }
    private List<HashMap<String,String>> getPlaces(JSONArray jsonArray){

        int count = jsonArray.length();
        List<HashMap<String,String>> placelist = new ArrayList<>();
        HashMap<String,String> placeMap =null;
        for(int i=0;i<count;i++){
            try {
                placeMap = getPlace((JSONObject) jsonArray.get(i));
                placelist.add(placeMap);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return placelist;
    }
     public  List<HashMap<String,String>> parse(String jsonData){

        JSONArray jsonArray = null;
        JSONObject jsonObject;

         try {
             jsonObject = new JSONObject(jsonData);
             jsonArray = jsonObject.getJSONArray("results");

         } catch (JSONException e) {
             e.printStackTrace();
         }

         return getPlaces(jsonArray);
     }

}
