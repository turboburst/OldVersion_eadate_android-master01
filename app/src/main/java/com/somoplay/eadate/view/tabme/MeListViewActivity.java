package com.somoplay.eadate.view.tabme;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.Interfaces.WebRequestActivityInterface;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by work on 2016-02-21.
 */
public class MeListViewActivity extends Activity implements WebRequestActivityInterface {

    private ListView infoLv;
    private String[] listContent;
    private String item;
    private ArrayList<String> list = new ArrayList<String>();

    private Bundle extras;

    private int activityId;

    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_activity_list_view);

        extras = getIntent().getExtras();

        if (extras != null) {
            activityId = extras.getInt("ACTIVITYID");
        }

        sharedpreferences = getSharedPreferences("PSRSONALINFO", MODE_PRIVATE);
        editor = sharedpreferences.edit();

        infoLv = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getData());
        infoLv.setAdapter(adapter);

        infoLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(list.get(position));

                sharedpreferences = getSharedPreferences("PSRSONALINFO", MODE_PRIVATE);
                editor = sharedpreferences.edit();
                editor.putString(item, list.get(position));
                editor.commit();

                Intent intent = new Intent(MeListViewActivity.this, MePersonalInfoActivity.class);
//                intent.putExtra("GENDER", list.get(position));
                startActivity(intent);
            }
        });
    }

    public List<String> getData() {

        Resources res = this.getResources();

        if (activityId == 3) {
            listContent = res.getStringArray(R.array.gender_chinese);
            item = "gender";
        } else if (activityId == 6) {
            listContent = res.getStringArray(R.array.marriage_chinese);
            item = "marriage";
        } else if (activityId == 8) {
            listContent = res.getStringArray(R.array.region_chinese);
            item = "region";
        } else if (activityId == 10) {
            listContent = res.getStringArray(R.array.job_chinese);
            item = "job";
        }

        for (int i = 0; i < listContent.length; i++) {
            list.add(listContent[i]);
        }

        return list;
    }

    @Override
    public JSONObject webRequestCallBack(JSONObject jsonObject) {
        return null;
    }
}
