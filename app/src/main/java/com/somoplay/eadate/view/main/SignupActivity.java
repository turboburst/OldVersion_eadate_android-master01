package com.somoplay.eadate.view.main;
import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.App.Constances;
import com.somoplay.eadate.view.Interfaces.WebRequestActivityInterface;
import com.somoplay.eadate.view.WebRequest.RegisterRequest;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ItemSelect;
import org.androidannotations.annotations.Touch;
import org.androidannotations.annotations.ViewsById;
import org.json.JSONObject;

import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;

@EActivity(R.layout.signup_activity_layout)
public class SignupActivity extends AppCompatActivity implements WebRequestActivityInterface
{
    ImageView signupButton;
    EditText BirthdayEditTextinput;
    final Calendar theCalendar = Calendar.getInstance();

    @AfterViews
    void addActivityToList()
    {
        BirthdayEditTextinput = (EditText)findViewById(R.id.birthdayEditTextID);
        signupButton = (ImageView) findViewById(R.id.signUpButtonID);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        CloseAllActivities.getInstance().addActivity(this);
    }

    @Click(R.id.signup_CancelID)
    void signupCancel()
    {
        SignupActivity.this.finish();
    }

    @Click(R.id.birthdayEditTextID)
    void onClickBirthdayEditText()
    {
        DatePickerDialog dialog = new DatePickerDialog(SignupActivity.this, new DatePickerDialog
        .OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthofyear, int dayofmonth)
            {
                theCalendar.set(year, monthofyear, dayofmonth);
                BirthdayEditTextinput.setText(DateFormat.format("yyyy-MM-dd",theCalendar));
            }
        }, theCalendar.get(Calendar.YEAR),theCalendar.get(Calendar.MONTH),
                theCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    public void saveData(String response) {
        Toast.makeText(SignupActivity.this, response, Toast.LENGTH_LONG).show();
    }

    @Override
    public JSONObject webRequestCallBack(JSONObject jsonObject) {
        return null;
    }
}
