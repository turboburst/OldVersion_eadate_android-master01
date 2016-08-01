package com.somoplay.eadate.view.tabsearch;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.adapter.TopTabFragmentPagerAdapter;
import com.somoplay.eadate.view.tab_top_model.BaseFragment;
import com.somoplay.eadate.view.tab_top_model.SlidingTabLayout;
import com.somoplay.eadate.view.tabfavourite.TabFrag1_favourite;
import com.somoplay.eadate.view.tabfavourite.TabFrag2_favourite;
import com.somoplay.eadate.view.tabfavourite.TabFrag3_favourite;

import org.androidannotations.annotations.EFragment;

import java.util.LinkedList;

/**
 * Created by yaolu on 15-09-14.
 */

@EFragment(R.layout.tab_top_search)
public class TabFragmentSearch extends Fragment {
    private String mTitle = "Default";
    public static final String TITLE = "title2";

    private SlidingTabLayout tabs;
    private ViewPager pager;
    private FragmentPagerAdapter adapter;

    // all the fragments need to put on viewpager
    private LinkedList<BaseFragment> getFragments(){
        //int indicatorColor = Color.parseColor(this.getResources().getString(R.color.tab_indicator));
        int indicatorColor = Color.BLUE;
        int dividerColor = Color.GRAY;

        LinkedList<BaseFragment> fragments = new LinkedList<BaseFragment>();
        fragments.add(TabFrag1_search.newInstance("Search 1", indicatorColor, dividerColor));
        //fragments.add(TabFrag2_search.newInstance("Search 2", indicatorColor, dividerColor));

        return fragments;
    }

    public static Fragment newInstance(){
        TabFragmentSearch f = new TabFragmentSearch();
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //get the fragments needed to map to viewpager
        final LinkedList<BaseFragment> fragments = getFragments();

        adapter = new TopTabFragmentPagerAdapter(getFragmentManager(), fragments);
        //pager
        pager = (ViewPager) view.findViewById(R.id.pager_search);
        //
        pager.setOffscreenPageLimit(2);

        pager.setAdapter(adapter);
        //tabs
        tabs = (SlidingTabLayout) view.findViewById(R.id.tab_top_layout_search);
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {

            @Override
            public int getIndicatorColor(int position) {
                return fragments.get(position).getIndicatorColor();
            }

            // @Override
            public int getDividerColor(int position) {
                return fragments.get(position).getDividerColor();
            }
        });
        tabs.setBackgroundResource(R.color.colorBottom);
        tabs.setCustomTabView(R.layout.tab_layout_title, R.id.txtTabTitle);
        tabs.setDistributeEvenly(true); //fill whole screen evenly
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.RED;
            }
        });
        tabs.setViewPager(pager);

    }
}
