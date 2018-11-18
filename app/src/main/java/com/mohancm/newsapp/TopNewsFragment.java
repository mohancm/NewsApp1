package com.mohancm.newsapp;


import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopNewsFragment extends Fragment implements LoaderManager.LoaderCallbacks<String> {


    private RecyclerView topNews;
    private ProgressDialog progress;
    private TextView errorTextView;

    public TopNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_news, container, false);


        topNews = (RecyclerView) view.findViewById(R.id.topNews);
        errorTextView = (TextView) view.findViewById(R.id.errorMessage);

        topNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        topNews.setItemAnimator(new DefaultItemAnimator());

        if (isNetworkConnected()) {
            progress = new ProgressDialog(getContext());
            progress.setTitle(getActivity().getString(R.string.loading_title));
            progress.setMessage(getActivity().getString(R.string.loading_message));
            progress.setCancelable(false);
            progress.setIcon(R.drawable.ic_download);
            progress.show();
            Bundle bundle = new Bundle();
            bundle.putString("URL", "https://content.guardianapis.com/search?api-key=0d451edd-27e7-4256-b573-367caef40d57&show-fields=headline,thumbnail&show-tags=contributor&page-size=40");
            getLoaderManager().initLoader(1, bundle, this);

        } else {
            errorTextView.setVisibility(View.VISIBLE);
            errorTextView.setText(R.string.no_internet_msg);
            errorTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            errorTextView.setTextColor(getResources().getColor(R.color.colorBlack));
        }
        return view;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {
        return new JsonLoader(getContext(), bundle.getString("URL"));
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String s) {
        progress.dismiss();
        ArrayList<News> allNews = new News().getAllNewsData(s);
        if (allNews.size() == 0)
            errorTextView.setVisibility(View.VISIBLE);

        NewsAdapter adapter = new NewsAdapter(allNews, getContext());
        topNews.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
