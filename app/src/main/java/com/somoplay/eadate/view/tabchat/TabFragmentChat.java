package com.somoplay.eadate.view.tabchat;

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

@EFragment(R.layout.tab_top_chat)
public class TabFragmentChat extends Fragment {
    private String mTitle = "Default";

    public static final String TITLE = "title3";

    private SlidingTabLayout tabs;
    private ViewPager pager;
    private FragmentPagerAdapter adapter;

    public static Fragment newInstance(){
        TabFragmentChat f = new TabFragmentChat();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //adapter
        final LinkedList<BaseFragment> fragments = getFragments();
        adapter = new TopTabFragmentPagerAdapter(getFragmentManager(), fragments);
        //pager
        pager = (ViewPager) view.findViewById(R.id.pager_chat);
        //
        pager.setOffscreenPageLimit(3);

        pager.setAdapter(adapter);
        //tabs
        tabs = (SlidingTabLayout) view.findViewById(R.id.tab_top_layout_chat);
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {

            @Override
            public int getIndicatorColor(int position) {
                return fragments.get(position).getIndicatorColor();
            }

            //@Override
            public int getDividerColor(int position) {
                return fragments.get(position).getDividerColor();
            }
        });
        tabs.setCustomTabView(R.layout.tab_layout_title, R.id.txtTabTitle);
        tabs.setViewPager(pager);

    }

    private LinkedList<BaseFragment> getFragments(){
        //int indicatorColor = Color.parseColor(this.getResources().getString(R.color.tab_indicator));
        int indicatorColor = Color.BLUE;
        int dividerColor = Color.TRANSPARENT;

        LinkedList<BaseFragment> fragments = new LinkedList<BaseFragment>();
        fragments.add(TabFrag1_chat.newInstance("tab1", indicatorColor, dividerColor));
        fragments.add(TabFrag2_chat.newInstance("tab2", indicatorColor, dividerColor));


        return fragments;
    }

}
