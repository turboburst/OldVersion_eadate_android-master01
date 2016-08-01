package com.somoplay.eadate.view.tabfavourite;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.adapter.TopTabFragmentPagerAdapter;
import com.somoplay.eadate.view.tab_top_model.BaseFragment;
import com.somoplay.eadate.view.tab_top_model.SlidingTabLayout;

import org.androidannotations.annotations.EFragment;

import java.util.LinkedList;


/**
 * Created by yaolu on 15-09-14.
 */

//@EFragment(R.layout.tab_top_favourite)
public class TabFragmentFavourite extends Fragment {
    private String mTitle = "Default";

    public static final String TITLE = "title3";

    private SlidingTabLayout tabs;
    private ViewPager pager;
    private FragmentPagerAdapter adapter;

    public static Fragment newInstance(){
        TabFragmentFavourite f = new TabFragmentFavourite();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tab_top_favourite, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //get the fragments needed to map to viewpager
        final LinkedList<BaseFragment> fragments = getFragments();

        adapter = new TopTabFragmentPagerAdapter(getFragmentManager(), fragments);
        //pager
        pager = (ViewPager) view.findViewById(R.id.pager_favourite);
        //
        pager.setOffscreenPageLimit(3);

        pager.setAdapter(adapter);
        //tabs
        tabs = (SlidingTabLayout) view.findViewById(R.id.tab_top_layout_favourite);
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
        // set tab title layout, with the txt, image
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

    private LinkedList<BaseFragment> getFragments(){
        //int indicatorColor = Color.parseColor(this.getResources().getString(R.color.tab_indicator));
        int indicatorColor = Color.BLUE;
        int dividerColor = Color.GRAY;

        LinkedList<BaseFragment> fragments = new LinkedList<BaseFragment>();
        fragments.add(TabFrag1_favourite.newInstance("tab4", indicatorColor, dividerColor));
        fragments.add(TabFrag2_favourite.newInstance("tab5", indicatorColor, dividerColor));
        fragments.add(TabFrag3_favourite.newInstance("tab6", indicatorColor, dividerColor));

        return fragments;
    }

}