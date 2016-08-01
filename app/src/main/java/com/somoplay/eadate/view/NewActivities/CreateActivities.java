package com.somoplay.eadate.view.NewActivities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.Interfaces.WebRequestActivityInterface;

import org.json.JSONObject;

public class CreateActivities extends AppCompatActivity implements WebRequestActivityInterface {

    Button btnAdd;
    Button btnSearch;

    FrgAddActivities frgAdd;
    FrgSearchActivities frgSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newactivity_create);

       // final TextView tv = (TextView) findViewById(R.id.txt_info);
        btnAdd = (Button) findViewById(R.id.add);
       // btnAdd.setBackgroundColor(Color.parseColor("#F44336"));
       // btnAdd.setTextColor(Color.parseColor("#FFEB3B"));
        btnSearch = (Button) findViewById(R.id.search);
        //btnSearch.setBackgroundColor(Color.parseColor("#7C4DFF"));
       // btnSearch.setBackgroundColor(Color.LTGRAY);
      //  btnSearch.setTextColor(Color.parseColor("#FFEB3B"));

        frgAdd = new FrgAddActivities();
        frgSearch = new FrgSearchActivities();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data;
                btnAdd.setVisibility(View.INVISIBLE);
                btnSearch.setVisibility(View.INVISIBLE);
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.frg_holder, frgAdd);
                transaction.commit();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAdd.setVisibility(View.INVISIBLE);
                btnSearch.setVisibility(View.INVISIBLE);
                Bundle data;
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                //hideFragment(transaction);
                transaction.replace(R.id.frg_holder, frgSearch);
                transaction.commit();
            }
        });
        SharedPreferences sp = this.getSharedPreferences("PREF_newActivity",MODE_PRIVATE);
        String info = "Title: " + sp.getString("TITLE","")
                     + "\n Type: " + sp.getString("TYPE", "")
                     + "\n Number of People: " + sp.getString("NUMBER", "")
                     + "\n Payment Type: " + sp.getString("PAYMENT", "");
       // tv.setText(info);
    }

    @Override
    public JSONObject webRequestCallBack(JSONObject jsonObject) {
        return null;
    }
}
