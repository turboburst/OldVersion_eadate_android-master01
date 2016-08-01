package com.somoplay.eadate.view.NewActivities;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.somoplay.eadate.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FrgDetail extends Fragment {
    private TextView detailTv;
    private Button nextBtn;

    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;
    private static final String DETAIL = "detail";

    public FrgDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //TextView textView = new TextView(getActivity());
       // textView.setText(R.string.hello_blank_fragment);
        // Inflate the layout for this fragment
        View detailView = inflater.inflate(R.layout.newactivity_frg_details, container, false);
        detailTv = (TextView) detailView.findViewById(R.id.newactivity_detail);
        nextBtn = (Button) detailView.findViewById(R.id.newactivity_nextBtn);

        sharedpreferences = getContext().getSharedPreferences("PREF_newActivity", Context.MODE_PRIVATE);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // save set
                    editor = sharedpreferences.edit();
                    editor.putString("ORIGINALDETAIL", detailTv.getText().toString());
                    editor.commit();

                    FrgSubmit frgSubmit = new FrgSubmit();

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.frg_holder, frgSubmit);
                    transaction.commit();
            }
        });

        return detailView;
    }
    @Override
    public void onResume() {
        super.onResume();

       detailTv.setText(sharedpreferences.getString("ORIGINALDETAIL", ""));
    }
}
