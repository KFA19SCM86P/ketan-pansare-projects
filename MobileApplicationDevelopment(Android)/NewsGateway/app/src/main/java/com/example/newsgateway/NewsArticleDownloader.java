package com.example.newsgateway;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("FieldMayBeFinal")
public class NewsArticleDownloader implements Runnable {

    private static final String TAG = "NewsArticleDownloader";
    private static final String API_KEY = "9e33caa4a5fa468ab57c5de69bd01f72";
    private static final String DATA_URL = "https://newsapi.org/v2/top-headlines?sources=%1$s&language=en&apiKey=%2$s";
    private NewsService newsService;
    private Source source;

    public NewsArticleDownloader(NewsService newsService, Source source) {
        this.newsService = newsService;
        this.source = source;
    }

    @Override
    public void run() {
        String finalDataUrl = String.format(DATA_URL, source.getId(), API_KEY);
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
        List<Article> articleList = parseJSON(JSONString);

        newsService.setArticleList(articleList, source);
    }

    private List<Article> parseJSON(String JSONString) {
        List<Article> articleList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(JSONString);
            JSONArray articleArray = jsonObject.getJSONArray("articles");

            for (int i = 0; i < articleArray.length(); i++) {
                JSONObject articleObj = articleArray.getJSONObject(i);
                Article article = new Article(
                        articleObj.getString("title"),
                        articleObj.getString("urlToImage"),
                        articleObj.getString("publishedAt"),
                        articleObj.getString("author"),
                        articleObj.getString("description"),
                        articleObj.getString("url")
                );

                articleList.add(article);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return articleList;
    }
}
