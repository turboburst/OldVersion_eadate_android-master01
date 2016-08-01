package com.somoplay.eadate.view.tabme;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.NewActivities.CreateNewActivityActivity;

import org.androidannotations.annotations.EFragment;


/**
 * Created by yaolu on 15-09-14.
 */

@EFragment(R.layout.tab_me)
public class TabFragmentMe extends Fragment {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private View view;
    private LinearLayout personalInfoLayout;
    private ListView listView;
    private TextView setActivity;
    private RelativeLayout activityLayout;
    private MeMenuList meMenuList= new MeMenuList();

    private String mTitle = "Default";

    public static final String TITLE = "title5";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.me_fragment, container, false);
        sharedPreferences = getActivity().getSharedPreferences("PREF_newActivity", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        personalInfoLayout = (LinearLayout) view.findViewById(R.id.namechang_layout);
        listView = (ListView) view.findViewById(R.id.listview);

        personalInfoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MePersonalInfoActivity.class);
                startActivity(intent);
            }
        });

        setActivity = (TextView) view.findViewById(R.id.setActivity);
        activityLayout = (RelativeLayout) view.findViewById(R.id.activityLayout);

        activityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear().commit();
                Intent intent = new Intent(getActivity(), CreateNewActivityActivity.class);
                startActivity(intent);
            }
        });
        listView.setAdapter(new MeAdapter(getActivity()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    System.out.println("position = " + position);
                }

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.topPic);
        //linearLayout.setVisibility(View.VISIBLE);

        super.onViewCreated(view, savedInstanceState);

    }
}
