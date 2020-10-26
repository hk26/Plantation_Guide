package com.example.plantation_guide;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;


public class NewsMain extends AppCompatActivity {
    String API_KEY = "fccdb758f1ca415cbd17231b97ca7889"; // ### YOUE NEWS API HERE ###
    String NEWS_SOURCE = "agriculture"; // Other news source code at: https://newsapi.org/sources
    ListView listNews;
    ProgressBar loader;

    ArrayList<HashMap<String, String>> dataList = new ArrayList<>();
    static final String KEY_AUTHOR = "author";
    static final String KEY_TITLE = "title";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_URL = "url";
    static final String KEY_URLTOIMAGE = "urlToImage";
    static final String KEY_PUBLISHEDAT = "publishedAt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_main);



            listNews = findViewById(R.id.listNews);
            loader = findViewById(R.id.loader);
            listNews.setEmptyView(loader);


            if (Function.isNetworkAvailable(getApplicationContext())) {
                DownloadNews newsTask = new DownloadNews();
                newsTask.execute();
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }

        }


        class DownloadNews extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            protected String doInBackground(String... args) {
                String xml = Function.excuteGet("http://newsapi.org/v2/everything?q=" + NEWS_SOURCE + "&apiKey=" + API_KEY);
                return xml;
            }


                @Override
                protected void onPostExecute(String xml) {


                    //if (xml.length() > 10) { // Just checking if not empty

                        try {
                            JSONObject jsonResponse = new JSONObject(xml);
                            JSONArray jsonArray = jsonResponse.optJSONArray("articles");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                HashMap<String, String> map = new HashMap<>();
                                map.put(KEY_AUTHOR, jsonObject.optString(KEY_AUTHOR));
                                map.put(KEY_TITLE, jsonObject.optString(KEY_TITLE));
                                map.put(KEY_DESCRIPTION, jsonObject.optString(KEY_DESCRIPTION));
                                map.put(KEY_URL, jsonObject.optString(KEY_URL));
                                map.put(KEY_URLTOIMAGE, jsonObject.optString(KEY_URLTOIMAGE));
                                map.put(KEY_PUBLISHEDAT, jsonObject.optString(KEY_PUBLISHEDAT));
                                dataList.add(map);
                            }
                        }catch (NullPointerException nulle){
                            Toast.makeText(getApplicationContext(), "NullPointerUnexpected error", Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Unexpected error", Toast.LENGTH_SHORT).show();
                        }


                        NewsListAdapter adapter = new NewsListAdapter(NewsMain.this, dataList);
                        listNews.setAdapter(adapter);

                        listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {
                                Intent i = new Intent(NewsMain.this, DetailActivity.class);
                                i.putExtra("url", dataList.get(+position).get(KEY_URL));
                                startActivity(i);
                            }
                        });

                   // } else {
                    //    Toast.makeText(getApplicationContext(), "No news found", Toast.LENGTH_SHORT).show();
                    //}
                    return;
                }



        }

    }

