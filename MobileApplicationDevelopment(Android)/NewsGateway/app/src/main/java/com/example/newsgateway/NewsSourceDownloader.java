package com.example.newsgateway;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("FieldMayBeFinal")
public class NewsSourceDownloader implements Runnable {

    private static final String TAG = "NewsSourceDownloader";
    private static final String API_KEY = "9e33caa4a5fa468ab57c5de69bd01f72";
    private static final String DATA_URL = "https://newsapi.org/v2/sources?language=en&country=us&category=%1$s&apiKey=%2$s";
    private MainActivity mainActivity;
    private String category;

    public NewsSourceDownloader(MainActivity mainActivity, String category) {
        this.mainActivity = mainActivity;
        if (category.equals("all")) {
            this.category = "";
        } else {
            this.category = category;
        }
    }

    @Override
    public void run() {
        // call api for the category
        String finalDataUrl = String.format(DATA_URL, category, API_KEY);
        Uri dataUri = Uri.parse(finalDataUrl);
        String urlToUse = dataUri.toString();

        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlToUse);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.addRequestProperty("User-Agent", "");
            conn.connect();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.e(TAG, "run: HTTP ResponseCode NOT OK: " + conn.getResponseCode());
                return;
            }

            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader((new InputStreamReader(is)));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }

            is.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String JSONString = sb.toString();
        List<Source> sourceList = parseJSON(JSONString);
        List<String> categoryList = getCategoryList(sourceList);

        //noinspection Convert2Lambda
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if(categoryList != null && NewsSourceDownloader.this.category.isEmpty()) {
                    mainActivity.setCategories(categoryList);
                }

                if (sourceList != null) {
                    mainActivity.setSources(sourceList);
                }
            }
        });
    }

    private List<Source> parseJSON(String JSONString) {
        List<Source> sourceList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(JSONString);
            JSONArray sourcesArray = jsonObject.getJSONArray("sources");
            for (int i = 0; i < sourcesArray.length(); i++) {
                JSONObject sourceObj = sourcesArray.getJSONObject(i);
                Source source = new Source(
                        sourceObj.getString("id"),
                        sourceObj.getString("name"),
                        sourceObj.getString("category")
                );
                sourceList.add(source);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return sourceList;
    }

    private List<String> getCategoryList(List<Source> sourceList) {

        if(sourceList == null) {
            return null;
        }

        List<String> categoryList = new ArrayList<>();

        for (Source source :
                sourceList) {
            if (!categoryList.contains(source.getCategory())) {
                categoryList.add(source.getCategory());
            }
        }

        return categoryList;
    }
}
