package com.mohancm.newsapp;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        final String[] categoryNames = {
                getActivity().getString(R.string.topic_world),
                getActivity().getString(R.string.topic_science),
                getActivity().getString(R.string.topic_technology),
                getActivity().getString(R.string.topic_sports),
                getActivity().getString(R.string.topic_games),
                getActivity().getString(R.string.topic_music),
                getActivity().getString(R.string.topic_film),
                getActivity().getString(R.string.topic_politics)
        };

        final String[] categoryAPIUrl = {
                "https://content.guardianapis.com/search?section=world&api-key=0d451edd-27e7-4256-b573-367caef40d57&show-fields=headline,thumbnail&show-tags=contributor&page-size=30",
                "https://content.guardianapis.com/search?section=science&api-key=0d451edd-27e7-4256-b573-367caef40d57&show-fields=headline,thumbnail&show-tags=contributor&page-size=30",
                "https://content.guardianapis.com/search?section=technology&api-key=0d451edd-27e7-4256-b573-367caef40d57&show-fields=headline,thumbnail&show-tags=contributor&page-size=30",
                "https://content.guardianapis.com/search?section=sport&api-key=0d451edd-27e7-4256-b573-367caef40d57&show-fields=headline,thumbnail&show-tags=contributor&page-size=30",
                "https://content.guardianapis.com/search?section=games&api-key=0d451edd-27e7-4256-b573-367caef40d57&show-fields=headline,thumbnail&show-tags=contributor&page-size=30",
                "https://content.guardianapis.com/search?section=music&api-key=0d451edd-27e7-4256-b573-367caef40d57&show-fields=headline,thumbnail&show-tags=contributor&page-size=30",
                "https://content.guardianapis.com/search?section=film&api-key=0d451edd-27e7-4256-b573-367caef40d57&show-fields=headline,thumbnail&show-tags=contributor&page-size=30",
                "https://content.guardianapis.com/search?section=politics&api-key=0d451edd-27e7-4256-b573-367caef40d57&show-fields=headline,thumbnail&show-tags=contributor&page-size=30"
        };


        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        CustomPagerAdapter customPagerAdapter = new CustomPagerAdapter(getChildFragmentManager());
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        for (int i = 0; i < categoryNames.length; i++) {
            CategoryTabFragment categoryTabFragment = new CategoryTabFragment();
            Bundle bundle = new Bundle();
            bundle.putString("URL", categoryAPIUrl[i]);
            categoryTabFragment.setArguments(bundle);
            customPagerAdapter.add(categoryTabFragment, categoryNames[i]);
        }
        viewPager.setAdapter(customPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {

        }
    }

    class CustomPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentName = new ArrayList<>();

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void add(Fragment fm, String name) {
            mFragmentList.add(fm);
            mFragmentName.add(name);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentName.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }

}
