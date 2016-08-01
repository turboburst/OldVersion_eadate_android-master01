package com.somoplay.eadate.view.tabme;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.Interfaces.WebRequestActivityInterface;

import org.json.JSONObject;

import java.util.Calendar;

/**
 * Created by work on 2016-02-21.
 */
public class MeBirthDateActivity extends Activity implements WebRequestActivityInterface{

    private DatePicker datePicker;
    private TextView ageTv, horoscopeTv, zodiacTv;
    private Button saveAgeBtn;

    private String[] horoscopeItem = {"摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座",
            "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座"};
    private String[] zodiacItem = {"鼠", "牛", "虎", "兔", "龍", "蛇",
            "馬", "羊", "猴", "鸡", "狗", "豬"};
    private String horoscope, zodiac, defaultAge, defaultHoroscope, defaultZodiac;
    private int year, month, day, age, defaultYear, defaultMonth, defaultDay;
    private final int currentYear = Calendar.getInstance().get(Calendar.YEAR);

    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_activity_birth_date);

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        ageTv = (TextView) findViewById(R.id.age);
        horoscopeTv = (TextView) findViewById(R.id.horoscope);
        zodiacTv = (TextView) findViewById(R.id.zodiac);
        saveAgeBtn = (Button) findViewById(R.id.saveBtn);

        sharedpreferences = getSharedPreferences("PSRSONALINFO", MODE_PRIVATE);
        defaultAge = sharedpreferences.getString("age", "0");
        defaultHoroscope = sharedpreferences.getString("horoscope", "0");
        defaultZodiac = sharedpreferences.getString("zodiac", "0");
        defaultYear = sharedpreferences.getInt("defaultYear", 2015);
        defaultMonth = sharedpreferences.getInt("defaultMonth", 1);
        defaultDay = sharedpreferences.getInt("defaultDay", 1);

        if (defaultAge != "0") {
            ageTv.setText(defaultAge);
            horoscopeTv.setText(defaultHoroscope);
            zodiacTv.setText(defaultZodiac);
        }

        saveAgeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo(v);
            }
        });


        datePicker.init(defaultYear, defaultMonth, defaultDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int birthYear,
                                      int monthOfYear, int dayOfMonth) {
                // 获取一个日历对象，并初始化为当前选中的时间
                ageTv.setText(Integer.toString(currentYear - 2000) + " 岁");
                Calendar calendar = Calendar.getInstance();
                calendar.set(birthYear, monthOfYear, dayOfMonth);
                year = birthYear;
                month = monthOfYear + 1;
                day = dayOfMonth;

                System.out.println("the year is " + year + " the month is " + month + " and the day is " + day);

                age = currentYear - calendar.get(Calendar.YEAR);
                ageTv.setText(Integer.toString(age) + " 岁");

                horoscopeDisplay();
                zodiacDisplay();
            }
        });
    }

    // 显示星座
    private void horoscopeDisplay() {

        if ((month == 1 && day < 21) || (month == 12 && day < 22) ) {
            horoscopeTv.setText("摩羯座");
        }
        else if ((month == 1 && day >= 21 ) || (month == 2 && day < 21)) {
            horoscopeTv.setText("水瓶座");
        }
        else if ((month == 2 && day >= 21) || (month == 3 && day < 21)) {
            horoscopeTv.setText("双鱼座");
        }
        else if ((month == 3 && day >= 21)|| (month == 4 && day < 21)) {
            horoscopeTv.setText("白羊座");
        }
        else if ((month == 4 && day >= 21) || (month == 5 && day < 22)) {
            horoscopeTv.setText("金牛座");
        }
        else if ((month == 5 && day >= 22) || (month == 6 && day < 22)) {
            horoscopeTv.setText("双子座");
        }
        else if ((month == 6 && day >= 22) || (month == 7 && day < 23)) {
            horoscopeTv.setText("巨蟹座");
        }
        else if ((month == 7 && day >= 23) || (month == 8 && day < 24)) {
            horoscopeTv.setText("狮子座");
        }
        else if ((month == 8 && day >= 24) || (month == 9 && day < 24)) {
            horoscopeTv.setText("处女座");
        }
        else if ((month == 9 && day >= 24) || (month == 10 && day < 24)) {
            horoscopeTv.setText("天秤座");
        }
        else if ((month == 10 && day >= 24) || (month == 11 && day < 23)) {
            horoscopeTv.setText("天蝎座");
        }
        else {
            horoscopeTv.setText("射手座");
        }
    }

    // 显示生肖
    private void zodiacDisplay() {

        int zodiacYear = 2008 - year;
        while (zodiacYear < 0) {
            zodiacYear = zodiacYear + 12;
        }
        zodiacYear = zodiacYear % 12;

        switch (zodiacYear) {
            case 0:
                zodiacTv.setText("鼠");
                break;
            case 11:
                zodiacTv.setText("牛");
                break;
            case 10:
                zodiacTv.setText("虎");
                break;
            case 9:
                zodiacTv.setText("兔");
                break;
            case 8:
                zodiacTv.setText("龙");
                break;
            case 7:
                zodiacTv.setText("蛇");
                break;
            case 6:
                zodiacTv.setText("马");
                break;
            case 5:
                zodiacTv.setText("羊");
                break;
            case 4:
                zodiacTv.setText("猴");
                break;
            case 3:
                zodiacTv.setText("鸡");
                break;
            case 2:
                zodiacTv.setText("狗");
                break;
            case 1:
                zodiacTv.setText("猪");
                break;
            default:
                break;
        }
    }

    private void saveInfo(View view) {

        sharedpreferences = getSharedPreferences("PSRSONALINFO", MODE_PRIVATE);
        editor = sharedpreferences.edit();
        editor.putString("age", ageTv.getText().toString());
        editor.putString("horoscope", horoscopeTv.getText().toString());
        editor.putString("zodiac", zodiacTv.getText().toString());
        editor.putInt("defaultYear", year);
        editor.putInt("defaultMonth", (month-1));
        editor.putInt("defaultDay", day);
        editor.commit();

        Intent intent = new Intent(MeBirthDateActivity.this, MePersonalInfoActivity.class);
        startActivity(intent);

}

    @Override
    public JSONObject webRequestCallBack(JSONObject jsonObject) {
        return null;
    }
}
