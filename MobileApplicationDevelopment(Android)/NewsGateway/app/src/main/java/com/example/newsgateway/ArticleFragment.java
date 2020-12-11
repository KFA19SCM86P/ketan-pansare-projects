package com.example.newsgateway;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class ArticleFragment extends Fragment {

    public static ArticleFragment newInstance(Article article, int position, int total) {
        ArticleFragment articleFragment = new ArticleFragment();

        Bundle args = new Bundle();
        args.putSerializable("article", article);
        args.putInt("position", position);
        args.putInt("total", total);
        articleFragment.setArguments(args);

        return articleFragment;
    }

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.article_fragment, container, false);

        TextView headline, date, author, text, page_count;
        ImageView image;

        headline = rootView.findViewById(R.id.article_headline);
        date = rootView.findViewById(R.id.article_date);
        author = rootView.findViewById(R.id.article_author);
        text = rootView.findViewById(R.id.article_text);
        image = rootView.findViewById(R.id.article_image);
        page_count = rootView.findViewById(R.id.page_count);

        if(getArguments() != null) {
            Bundle args = getArguments();
            Article article = (Article) args.getSerializable("article");
            int position = args.getInt("position");
            int total = args.getInt("total");

            page_count.setText(String.format("%1$d of %2$d", position + 1, total));

            if(article.headline != null && !article.headline.equals("null")) {
                headline.setText(article.headline);
            } else {
                headline.setVisibility(View.GONE);
            }

            if(article.date != null && !article.date.equals("null")) {
                date.setText(article.date);
            } else {
                date.setVisibility(View.GONE);
            }

            if(article.author != null && !article.author.equals("null")) {
                author.setText(article.author);
            } else {
                author.setVisibility(View.GONE);
            }

            if(article.text != null && !article.text.equals("null")) {
                text.setText(article.text);
            } else {
                text.setVisibility(View.GONE);
            }

            if(article.image != null && !article.image.equals("null") && !article.image.isEmpty()) {
                loadImage(article.image, image);
            } else {
                image.setVisibility(View.GONE);
            }

            if(article.url != null && !article.url.equals("null") && !article.url.isEmpty()) {
                setOnClicks(rootView, article);
            }
        }

        return rootView;
    }

    private void setOnClicks(View rootView, Article article) {
        //noinspection Convert2Lambda
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url = Uri.parse(article.url);
                Intent intent = new Intent(Intent.ACTION_VIEW, url);
                startActivity(intent);
            }
        };

        TextView headline = rootView.findViewById(R.id.article_headline);
        TextView text = rootView.findViewById(R.id.article_text);
        ImageView image = rootView.findViewById(R.id.article_image);

        headline.setOnClickListener(onClickListener);
        text.setOnClickListener(onClickListener);
        image.setOnClickListener(onClickListener);
    }

    private void loadImage(String uri, ImageView imageView) {
        Picasso.get().load(uri)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.brokenimage)
                .into(imageView);
    }

}
