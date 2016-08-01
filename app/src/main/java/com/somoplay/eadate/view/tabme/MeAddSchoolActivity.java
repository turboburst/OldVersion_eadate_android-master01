package com.somoplay.eadate.view.tabme;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.Interfaces.WebRequestActivityInterface;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by work on 2016-02-21.
 */
public class MeAddSchoolActivity extends Activity implements WebRequestActivityInterface{

    //    private EditText addSchoolText;
    private AutoCompleteTextView searchText;
    private View selectYearView, selectSchoolView;
    private TextView selectYearTv;
    private DatePicker datePicker;
    private int selectedYear = 2000;

    private ArrayList<String> schoolList = new ArrayList<String>();
    private String[] school;
    private ArrayAdapter<String> arrayAdapter;
    MeSchoolAdapter schoolAdapter;

    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_activity_add_school);

        Resources res = this.getResources();
        String[] schools = res.getStringArray(R.array.school_chinese);

//        addSchoolText = (EditText) findViewById(R.id.addSchoolText);
        searchText = (AutoCompleteTextView) findViewById(R.id.autotext);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, schools);
        searchText.setAdapter(arrayAdapter);

        sharedpreferences = getSharedPreferences("PSRSONALINFO", MODE_PRIVATE);
        editor = sharedpreferences.edit();

        datePicker = (DatePicker) findViewById(R.id.yearPicker);
//        ((ViewGroup)(((ViewGroup) datePicker.getChildAt(0)).getChildAt(0))).getChildAt(1).setVisibility(View.GONE);
//        ((ViewGroup)(((ViewGroup) datePicker.getChildAt(0)).getChildAt(0))).getChildAt(2).setVisibility(View.GONE);
        datePicker.setCalendarViewShown(false);
        datePicker.setVisibility(View.GONE);

        selectYearTv = (TextView) findViewById(R.id.infoText);
        selectYearTv.setText("选择入学时间");

        selectYearView = findViewById(R.id.selectYear);
        selectYearView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.setVisibility(View.VISIBLE);
            }
        });

        datePicker.init(selectedYear, 1, 1, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int schoolYear,
                                      int monthOfYear, int dayOfMonth) {
                // 获取一个日历对象，并初始化为当前选中的时间
                Calendar calendar = Calendar.getInstance();
                calendar.set(schoolYear, monthOfYear, dayOfMonth);
                selectedYear = calendar.get(Calendar.YEAR);
                selectYearTv.setText(Integer.toString(selectedYear)+"年入学");
            }
        });
    }

    public void save(View view) {

        if (searchText.length()>0) {

            editor.putString("schoolname", searchText.getText().toString());
            editor.putString("schoolyear", Integer.toString(selectedYear));
            editor.commit();

            Intent intent = new Intent(MeAddSchoolActivity.this, MeSchoolActivity.class);
            intent.putExtra("SCHOOLNAME", searchText.getText().toString());
            intent.putExtra("SCHOOLYEAR", Integer.toString(selectedYear));
            startActivity(intent);
        }
        else {
            Toast.makeText(MeAddSchoolActivity.this, "Please input the school", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public JSONObject webRequestCallBack(JSONObject jsonObject) {
        return null;
    }
}
