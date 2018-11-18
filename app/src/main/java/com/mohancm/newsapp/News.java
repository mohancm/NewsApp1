package com.mohancm.newsapp;

import android.annotation.SuppressLint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class News {

    private String newsHeadLine;
    private String newsWebUrl;
    private String newsDate;
    private String newsCategory;
    private String newsThumbline;
    private String newsAuthor;

    public String getNewsDate() {
        return newsDate;
    }

    public String getNewsHeadLine() {
        return newsHeadLine;
    }

    public String getNewsThumbline() {
        return newsThumbline;
    }

    public String getNewsWebUrl() {
        return newsWebUrl;
    }

    public String getNewsAuthor() {
        return newsAuthor;
    }

    public String getNewsCategory() {
        return newsCategory;
    }

    private News setNewsData(JSONObject json) throws JSONException, ParseException {
        this.newsHeadLine = json.getString("webTitle");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date_format = sdf.parse(json.getString("webPublicationDate").replace("Z", "").replace("T", " "));
        this.newsDate = new SimpleDateFormat("MMMM dd, yyyy").format(date_format).toString();
        if (!json.getJSONObject("fields").isNull("thumbnail"))
            this.newsThumbline = json.getJSONObject("fields").getString("thumbnail");
        this.newsWebUrl = json.getString("webUrl");
        this.newsCategory = json.getString("sectionName");
        this.newsAuthor = json.getJSONArray("tags").getJSONObject(0).getString("webTitle");
        return this;
    }

    public ArrayList<News> getAllNewsData(String json) {
        ArrayList<News> outputNews = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONObject(json).getJSONObject("response").getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++)
                outputNews.add(new News().setNewsData(jsonArray.getJSONObject(i)));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputNews;
    }
}
