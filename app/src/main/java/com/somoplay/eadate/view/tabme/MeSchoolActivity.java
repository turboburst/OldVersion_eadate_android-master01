package com.somoplay.eadate.view.tabme;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.Interfaces.WebRequestActivityInterface;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by work on 2016-02-21.
 */
public class MeSchoolActivity extends Activity implements WebRequestActivityInterface {

    private TextView addSchoolTv;
    private ListView schoolLv;
    private View searchLayout;

    private SharedPreferences sharedpreferences;
    private String schoolInfo;
    private ArrayList<String> schoolList = new ArrayList<String>();
    private ArrayAdapter<String> arrayAdapter;

    private String schoolName, schoolYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_activity_school);

        schoolLv = (ListView) findViewById(R.id.listview);

        addSchoolTv = (TextView) findViewById(R.id.infoText);
        addSchoolTv.setText(getString(R.string.add_school));

        sharedpreferences = getSharedPreferences("PSRSONALINFO", MODE_PRIVATE);

//        schoolName = sharedpreferences.getString("schoolname", "");
//        schoolYear = sharedpreferences.getString("schoolyear", "");
//        if (schoolName.length() > 3) {
//                schoolInfo = schoolName + schoolYear.charAt(2) + schoolYear.charAt(3) + "级";
//                schoolList.add(schoolInfo);
//            }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            schoolName = extras.getString("SCHOOLNAME");
            schoolYear = extras.getString("SCHOOLYEAR");
            if (schoolName  != null) {
                schoolInfo = schoolName + schoolYear.charAt(2) + schoolYear.charAt(3) + "级";
                schoolList.add(schoolInfo);

            }
        }

//        sharedpreferences = getSharedPreferences("PSRSONALINFO", MODE_PRIVATE);
//        if (sharedpreferences.getString("schoolname", "") != null) {
//            schoolInfo = sharedpreferences.getString("schoolname", "") +
//                    sharedpreferences.getString("schoolyear", "") +
//                    "级";
//            System.out.println("school name and school year is " + schoolInfo);
//            schoolList.add(schoolInfo);
//        }

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, schoolList);
        schoolLv.setAdapter(arrayAdapter);

        searchLayout = (View) findViewById(R.id.searchLayout);

        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeSchoolActivity.this, MeAddSchoolActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public JSONObject webRequestCallBack(JSONObject jsonObject) {
        return null;
    }
}
