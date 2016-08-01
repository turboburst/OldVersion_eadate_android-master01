package com.somoplay.eadate.view.NewActivities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.Interfaces.SaveDataToBundleInterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class FrgSubmit extends Fragment implements SaveDataToBundleInterface {

    private Bundle bundle;
    private TextView stepTextView;

    public TextView getTitlePartTv() {
        return titlePartTv;
    }

    private TextView titlePartTv;
    private TextView addressPartTv;
    private TextView timePartTv;

    private TextView detailPartTv;

    //private Button redoBtn, submitBtn;

    public void setDetailPartTvText(String detailPart) {
        this.detailPartTv.setText(detailPart);
    }

    public FrgSubmit() {
        // Required empty public constructor
    }

    public FrgSubmit(TextView stepTextView)
    {
        this.stepTextView = stepTextView;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bundle = ((CreateNewActivityActivity)this.getActivity()).getBundle();
        View summaryView = inflater.inflate(R.layout.newactivity_frg_submit, container, false);

        titlePartTv = (TextView) summaryView.findViewById(R.id.newactivity_title_part);
        addressPartTv = (TextView) summaryView.findViewById(R.id.newactivity_address_part);
        timePartTv = (TextView) summaryView.findViewById(R.id.newactivity_time_part);
        detailPartTv = (TextView) summaryView.findViewById(R.id.newactivity_detail_part);

       /* redoBtn = (Button) summaryView.findViewById(R.id.newactivity_backBtn);
        submitBtn = (Button) summaryView.findViewById(R.id.newactivity_submitBtn);*/


        final String titlePart = "Title: " + bundle.getString("TITLE") + "\n Type: " + bundle.getString("TYPE")
                              + " Number of People: " + bundle.getString("NUMBER", "")
                              + "  Payment Type: " + bundle.getString("PAYMENT", "") ;
        titlePartTv.setText(titlePart);

        String addressPart = "Address: " + bundle.getString("STREET", "") + "\n City: " + bundle.getString("CITY", "")
                                + "  Post Code: " + bundle.getString("POSTCODE", "")
                                + "\n Store Name: " + bundle.getString("STORE", "")
                                + "\n Telephone: " + bundle.getString("TELPHONE","");
        addressPartTv.setText(addressPart);

        String timePart = "Start Time: " + bundle.getString("START", "") + "\n End Time: " + bundle.getString("END", "");
        timePartTv.setText(timePart);

        /*redoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().clear().commit();
                 getActivity().finish();

                  //getActivity().startActivity(getActivity(),CreateNewActivityActivity.class);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), titlePart + " has been submitted", Toast.LENGTH_SHORT).show();
            }
        });*/
        return summaryView;
    }

    @Override
    public void SaveDataToBundle() {

    }
}
