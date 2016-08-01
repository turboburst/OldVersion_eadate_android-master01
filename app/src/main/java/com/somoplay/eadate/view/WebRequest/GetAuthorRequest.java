package com.somoplay.eadate.view.WebRequest;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.somoplay.eadate.view.App.AppController;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by turbo on 2016/6/30.
 */
public class GetAuthorRequest {

    public Context context;
    public RequestQueue requestQueue;
    public JsonObjectRequest jsonObjectRequest;
    public JSONArray jsonArray;
    public JSONObject jsonObject;
    public String access_token;
    public String getAuthor_url;

    public GetAuthorRequest()
    {
        this.context = context;
    }

    public void sendGetAuthorRequest()
    {
        //this.access_token = AppController.getInstance().getAccess_token();
    }
}
