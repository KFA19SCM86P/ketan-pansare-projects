package com.example.newsgateway;

import android.util.Log;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Article implements Serializable {
    private static final String TAG = "Article";

    String headline, image, date, author, text, url;

    public Article(String headline, String image, String date, String author, String text, String url) {
        this.headline = headline;
        this.image = image;
        this.date = date;
        this.author = author;
        this.text = text;
        this.url = url;

        formatDate();
    }

    private void formatDate() {

        SimpleDateFormat DF1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        DF1.setTimeZone(TimeZone.getTimeZone("UTC"));

        SimpleDateFormat DF2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        DF2.setTimeZone(TimeZone.getTimeZone("UTC"));

        SimpleDateFormat DF3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US);

        Date newDate = null;
        if(date != null) {
            try {
                newDate = DF1.parse(date);
            } catch (ParseException ignored) {  }

            try {
                newDate = DF2.parse(date);
            } catch (ParseException ignored) {  }

            try {
                newDate = DF3.parse(date);
            } catch (ParseException ignored) {  }

            SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.US);
            if (newDate != null) {
                date = outputFormat.format(newDate);
            } else {
                Log.e(TAG, "formatDate: Unable to format date: " + date);
            }
        }
    }
}
