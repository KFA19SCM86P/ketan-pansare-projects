package com.example.newsgateway;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.newsgateway.MainActivity.ACTION_MSG_TO_SERVICE;
import static com.example.newsgateway.MainActivity.ACTION_NEWS_STORY;

public class NewsService extends Service {

    private static final String TAG = "NewsService";
    private boolean running = true;
    private List <Article> articleList;
    private Source source;
    private ServiceReceiver serviceReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        articleList = new ArrayList<>();

        serviceReceiver = new ServiceReceiver();
        IntentFilter intentFilter = new IntentFilter(ACTION_MSG_TO_SERVICE);

        registerReceiver(serviceReceiver, intentFilter);

        //noinspection Convert2Lambda
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    if(articleList.isEmpty()) {
                        try {
                            //noinspection BusyWait
                            Thread.sleep(250);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Intent intent = new Intent();
                        intent.setAction(ACTION_NEWS_STORY);
                        intent.putExtra("article_list", (Serializable) articleList);
                        intent.putExtra("source", source);
                        sendBroadcast(intent);

                        articleList.clear();
                    }
                }
            }
        }).start();

        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(serviceReceiver);
        running = false;
        super.onDestroy();
    }

    public void setArticleList(List<Article> articleList, Source source) {
        this.articleList.clear();
        this.articleList.addAll(articleList);

        this.source = source;
    }

    class ServiceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent == null || intent.getAction() == null) {
                return;
            }

            if(intent.getAction().equals(ACTION_MSG_TO_SERVICE)) {
                Source source = (Source) intent.getSerializableExtra("source");
                NewsArticleDownloader downloader = new NewsArticleDownloader(NewsService.this, source);
                new Thread(downloader).start();
            }
        }

    }
}
