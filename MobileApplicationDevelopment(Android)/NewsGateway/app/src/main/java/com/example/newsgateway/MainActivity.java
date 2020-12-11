package com.example.newsgateway;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String ACTION_NEWS_STORY = "ACTION_NEWS_STORY";
    public static final String ACTION_MSG_TO_SERVICE = "ACTION_MSG_TO_SERVICE";
    private static final String TAG = "MainActivity";
    private Menu menu;
    private DrawerLayout mDrawerLayout;
    private DrawerAdapter mDrawerAdapter;
    private RecyclerView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private final List<Source> sourceList = new ArrayList<>();
    private final List<Article> articleList = new ArrayList<>();
    private List<String> categoryList = new ArrayList<>();
    private ArticlesPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private Intent newsService;
    private NewsReceiver newsReceiver;
    private HashMap<String, Integer> categoryColorMap;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsReceiver = new NewsReceiver();

        newsService = new Intent(MainActivity.this, NewsService.class);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerList = findViewById(R.id.left_drawer);

        mDrawerAdapter = new DrawerAdapter(this, sourceList);
        mDrawerList.setAdapter(mDrawerAdapter);
        mDrawerList.setLayoutManager(new LinearLayoutManager(this));

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        );
        mDrawerToggle.syncState();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        pagerAdapter = new ArticlesPagerAdapter(getSupportFragmentManager(), articleList);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapter);

        if(savedInstanceState != null) {
            mDrawerAdapter.setCategoryColorMap((HashMap<String, Integer>) savedInstanceState.getSerializable("color_map"));
            this.setArticleList((List<Article>) savedInstanceState.getSerializable("article_list"));
            this.setSources((List<Source>) savedInstanceState.getSerializable("source_list"));
            this.categoryList = (List<String>) savedInstanceState.getSerializable("category_list");

            setTitle(savedInstanceState.getString("title"));
        } else {
            new Thread(new NewsSourceDownloader(this, "all")).start();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("title", getTitle().toString());
        outState.putSerializable("article_list", (Serializable) articleList);
        outState.putSerializable("source_list", (Serializable) sourceList);
        outState.putSerializable("color_map", categoryColorMap);

        List<String> categoryList = new ArrayList<>();
        for (int i = 1; i < menu.size(); i++) {
            String category = menu.getItem(i).getTitle().toString();
            categoryList.add(category);
        }

        outState.putSerializable("category_list", (Serializable) categoryList);
    }

    @Override
    protected void onResume() {
        IntentFilter intentFilter = new IntentFilter(ACTION_NEWS_STORY);
        registerReceiver(newsReceiver, intentFilter);

        startService(newsService);

        super.onResume();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        this.menu = menu;

        if(!categoryList.isEmpty()) {
            setCategories(categoryList);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        String category = item.getTitle().toString();
        new Thread(new NewsSourceDownloader(this, category)).start();

        return super.onOptionsItemSelected(item);
    }

    public void setSources(List<Source> sourceList) {
        this.sourceList.clear();
        this.sourceList.addAll(sourceList);
        mDrawerAdapter.notifyDataSetChanged();
    }

    public void setCategories(List<String> categoryList) {
        menu.clear();
        menu.add("all");
        for (String category :
                categoryList) {
            menu.add(category);
        }

        HashMap<String, Integer> categoryColorMap = new HashMap<>();
        int[] colorArray = getResources().getIntArray(R.array.colorArray);
        for (int i = 1; i < menu.size(); i++) {
            if(i < colorArray.length) {
                MenuItem item = menu.getItem(i);
                SpannableString spanString = new SpannableString(menu.getItem(i).getTitle().toString());
                spanString.setSpan(new ForegroundColorSpan(colorArray[i-1]), 0, spanString.length(), 0); //fix the color to white
                item.setTitle(spanString);

                categoryColorMap.put(menu.getItem(i).getTitle().toString(), colorArray[i-1]);
            }
        }
        this.categoryColorMap = categoryColorMap;
        mDrawerAdapter.setCategoryColorMap(categoryColorMap);

        this.categoryList = categoryList;
    }

    @Override
    public void onClick(View v) {
        int pos = mDrawerList.getChildLayoutPosition(v);
        Source source = sourceList.get(pos);

        Intent intent = new Intent();
        intent.setAction(ACTION_MSG_TO_SERVICE);
        intent.putExtra("source", source);
        sendBroadcast(intent);

        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    protected void onStop() {
        unregisterReceiver(newsReceiver);
        stopService(newsService);
        super.onStop();
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList.clear();
        this.articleList.addAll(articleList);
        pagerAdapter.notifyDataSetChanged();
    }

    @SuppressWarnings("unchecked")
    class NewsReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent == null || intent.getAction() == null) {
                return;
            }

            if(intent.getAction().equals(ACTION_NEWS_STORY)) {
                List<Article> articleList = (List<Article>) intent.getSerializableExtra("article_list");
                Source source = (Source) intent.getSerializableExtra("source");

                setTitle(source.getName());
                reDoFragments(articleList);
            } else {
                Log.e(TAG, "onReceive: Unknown broadcast received");
            }
        }

        private void reDoFragments(List<Article> articleList) {

            for (int i = 0; i < pagerAdapter.getCount(); i++) {
                pagerAdapter.notifyChangeInPosition(i);
            }

            MainActivity.this.setArticleList(articleList);
            viewPager.setCurrentItem(0);
        }
    }
}