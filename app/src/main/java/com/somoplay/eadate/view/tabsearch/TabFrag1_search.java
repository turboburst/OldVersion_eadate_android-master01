package com.somoplay.eadate.view.tabsearch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.adapter.ListImageTextAdapter;
import com.somoplay.eadate.view.tab_top_model.BaseFragment;
import com.somoplay.eadate.view.tabme.MeBirthDateActivity;
import com.somoplay.eadate.view.tabme.MeJobActivity;
import com.somoplay.eadate.view.tabme.MeMenuList;

/**
 * Created by jeff qiu on 4/10/2016.
 */
public class TabFrag1_search extends BaseFragment {

    private static final String DATA_NAME = "name";
    private View view;
    private LinearLayout personalInfoLayout;
    private ListView listView;
    private MeMenuList meMenuList= new MeMenuList();

    public static TabFrag1_search newInstance(String title, int indicatorColor, int dividerColor) {

        TabFrag1_search f = new TabFrag1_search();
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
        view = inflater.inflate(R.layout.search_tab1, container, false);

        listView = (ListView) view.findViewById(R.id.listview);
        /*
        personalInfoLayout = (LinearLayout) view.findViewById(R.id.namechang_layout);
        personalInfoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MePersonalInfoActivity.class);
                startActivity(intent);
            }
        });
        */
        int[] images = {R.drawable.me_akb, R.drawable.me_ake, R.drawable.me_akc, R.drawable.me_akd};
        String[] menus = getActivity().getResources().getStringArray(R.array.me_menu_chinese);

        ListImageTextAdapter myAdapter = new ListImageTextAdapter(getActivity(), images, menus);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("position = ", "pos :" + position);
                Toast.makeText(getContext(),"position " + position, Toast.LENGTH_SHORT).show();
                if(position == 0) {
                    Intent intent = new Intent(getActivity(), MeBirthDateActivity.class);
                    startActivity(intent);
                }
                if(position == 1) {
                    Intent intent = new Intent(getActivity(), MeJobActivity.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }
}
