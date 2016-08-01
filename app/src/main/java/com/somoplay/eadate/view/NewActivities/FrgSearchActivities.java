package com.somoplay.eadate.view.NewActivities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class FrgSearchActivities extends Fragment {

    private Bundle bundle;
    private static final String DATA_NAME = "name";

    private View view;
    private LinearLayout personalInfoLayout;
    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.newactivity_fragment_search, container, false);

        bundle = new Bundle();

        listView = (ListView) view.findViewById(R.id.listview);

        int[] images = {R.drawable.me_akb, R.drawable.me_ake, R.drawable.me_akc, R.drawable.me_akd};
        String[] menus = getActivity().getResources().getStringArray(R.array.Activities_Search);

        ListImageTextAdapter myAdapter = new ListImageTextAdapter(getActivity(), images, menus);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("position = ", "pos :" + position);
                Toast.makeText(getContext(),"position " + position, Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

    //private MeMenuList meMenuList= new MeMenuList();
    public FrgSearchActivities() {
        // Required empty public constructor
    }

}
