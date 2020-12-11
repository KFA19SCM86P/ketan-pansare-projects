package com.example.newsgateway;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ArticlesPagerAdapter extends FragmentPagerAdapter {

    List<Article> articleList;
    private long baseId = 0;

    public ArticlesPagerAdapter(@NonNull FragmentManager fm, List<Article> articleList) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return ArticleFragment.newInstance(articleList.get(position), position, articleList.size());
    }

    @Override
    public long getItemId(int position) {
        return baseId + position;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return articleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "SECTION " + position;
    }

    void notifyChangeInPosition(int n) {
        // shift the ID returned by getItemId outside the range of all previous fragments
        baseId += getCount() + n;
    }
}
