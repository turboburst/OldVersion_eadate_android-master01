package com.somoplay.eadate.view.tabchat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.tab_top_model.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */


public class TabFrag1_chat extends BaseFragment {


    private static final String DATA_NAME = "name";

    private String title = "";

    public static TabFrag1_chat newInstance(String title, int indicatorColor, int dividerColor) {
        TabFrag1_chat f = new TabFrag1_chat();
        f.setTitle(title);
        f.setIndicatorColor(indicatorColor);
        f.setDividerColor(dividerColor);
        //f.setIconResId(iconResId);


        //pass data
        Bundle args = new Bundle();
        args.putString(DATA_NAME, title);
        f.setArguments(args);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_common, container, false);
    }

}
