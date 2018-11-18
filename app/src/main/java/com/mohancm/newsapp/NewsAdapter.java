package com.mohancm.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private List<News> newsList;
    private Context context;

    NewsAdapter(List<News> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_items, parent, false);
        return new NewsHolder(view);
    }


    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        final News currentNews = newsList.get(position);
        holder.newsTimeTextView.setText(currentNews.getNewsDate());
        if (currentNews.getNewsHeadLine().length() > 70)
            holder.newsHeadlineTextView.setText(currentNews.getNewsHeadLine().substring(0, 65).toString() + "...");
        else
            holder.newsHeadlineTextView.setText(currentNews.getNewsHeadLine());
        holder.newsCategoryTextView.setText(context.getString(R.string.news_author_prefix) + " " + currentNews.getNewsAuthor() + "\n" + currentNews.getNewsCategory());
        if (currentNews.getNewsThumbline() != null)
            Picasso.get().load(currentNews.getNewsThumbline())
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_noimages)
                    .into(holder.newsImageView);

        holder.newsContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentNews.getNewsWebUrl()));
                context.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder {
        TextView newsHeadlineTextView;
        TextView newsTimeTextView;
        ImageView newsImageView;
        TextView newsCategoryTextView;
        View newsContainer;

        public NewsHolder(View itemView) {
            super(itemView);
            newsImageView = itemView.findViewById(R.id.newsImage);
            newsHeadlineTextView = itemView.findViewById(R.id.mewsHeadline);
            newsTimeTextView = itemView.findViewById(R.id.newsTime);
            newsContainer = itemView.findViewById(R.id.list_news_items);
            newsCategoryTextView = itemView.findViewById(R.id.newsAuthor);
        }
    }
}
