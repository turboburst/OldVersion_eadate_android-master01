package com.somoplay.eadate.view.tabme;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.somoplay.eadate.R;

import java.util.ArrayList;

/**
 * Created by work on 2016-02-21.
 */
public class MeSchoolAdapter extends BaseAdapter {

    private ArrayList<MeSchool> schoolList;
    private ArrayList<String> schoolDetail;
    private Context context;

    private SharedPreferences sharedpreferences;
    private String schoolInfo;

    public MeSchoolAdapter(Context context) {
        this.context = context;
        schoolList = new ArrayList<MeSchool>();
        schoolDetail = new ArrayList<String>();

        sharedpreferences = context.getSharedPreferences("PSRSONALINFO", context.MODE_PRIVATE);
        if (sharedpreferences.getString("schoolname", "") != null) {
            schoolInfo = sharedpreferences.getString("schoolname", "") +
                    sharedpreferences.getString("schoolyear", "") +
                    "级";
            System.out.println("school name and school year is " + schoolInfo);
            schoolDetail.add(schoolInfo);
        }

//        Resources res = context.getResources();
//        String[] schools = res.getStringArray(R.array.school_chinese);
//        for (int i=0; i<schools.length; i++) {
//            School schoolNames = new School(schools[i]);
//            schoolList.add(schoolNames);
//        }
    }

    public ArrayList<MeSchool> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(ArrayList<MeSchool> schoolList) {
        this.schoolList = schoolList;
    }

    @Override
    public int getCount() {
        return schoolList.size();
    }

    @Override
    public Object getItem(int i) {
        return schoolList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.me_list_single_text, viewGroup, false);

        TextView school = (TextView) item.findViewById(R.id.singleText);

        String temp = schoolDetail.get(i);

        school.setText(temp);

        return item;
    }
}
