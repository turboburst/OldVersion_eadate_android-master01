package com.somoplay.eadate.view.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.App.AppController;
import com.somoplay.eadate.view.App.Constances;
import com.somoplay.eadate.view.Interfaces.WebRequestActivityInterface;
import com.somoplay.eadate.view.WebRequest.TokenRequest;

import org.json.JSONObject;

public class LoadingActivity extends AppCompatActivity implements WebRequestActivityInterface {

    public String accesstoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);


        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                TokenRequest tokenRequest = new TokenRequest(LoadingActivity.this, Constances.Token_url);
                tokenRequest.sendTokenRequest();

            }
        }, 2000);
    }

    public void refreshActivity(String token)
    {
        this.accesstoken = token;
        AppController.getInstance().setAccess_token(token);
        MainMainActivity.toActivity(LoadingActivity.this, this.accesstoken);
        finish();
    }


    @Override
    public JSONObject webRequestCallBack(JSONObject jsonObject) {
        return null;
    }
}
