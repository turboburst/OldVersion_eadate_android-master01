package com.somoplay.eadate.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.App.AppController;
import com.somoplay.eadate.view.Interfaces.WebRequestActivityInterface;
import com.somoplay.eadate.view.WebRequest.RegisterRequest;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.json.JSONObject;

@EActivity(R.layout.activity_main_layout)
public class MainMainActivity extends AppCompatActivity implements WebRequestActivityInterface
{

    public static final String tokenkey = "ACCESS_TOKEN";
    public static String accesstoken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(MainMainActivity.this, accesstoken, Toast.LENGTH_LONG).show();
    }

    @AfterViews
    void addActivityToList()
    {
        CloseAllActivities.getInstance().addActivity(this);
    }

    @Click({R.id.enterButtonID, R.id.forgetButtonID, R.id.signUpButtonID})
    void ButtonsonClick(View v)
    {
        switch(v.getId())
        {
            case R.id.enterButtonID:
                Intent myIntentforEnter = getIntent();
                myIntentforEnter.setClass(MainMainActivity.this, MainActivity_.class);
                startActivity(myIntentforEnter);
                break;
            case R.id.forgetButtonID:
                CloseAllActivities.getInstance().exit();

            case R.id.signUpButtonID:
                Intent myIntentforSignup = getIntent();
                myIntentforSignup.setClass(MainMainActivity.this, SignupActivity_.class);
                startActivity(myIntentforSignup);
                break;
        }
    }

    public static void toActivity(Context context, String token)
    {
        Intent intent = new Intent(context, MainMainActivity_.class);
        intent.putExtra(tokenkey, token);
        context.startActivity(intent);
        accesstoken = token;
    }

    @Override
    public JSONObject webRequestCallBack(JSONObject jsonObject) {
        return null;
    }
}


