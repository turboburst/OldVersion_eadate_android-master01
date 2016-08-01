package com.somoplay.eadate.view.WebRequest;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.somoplay.eadate.view.App.AppController;
import com.somoplay.eadate.view.main.LoadingActivity;
import com.somoplay.eadate.view.main.MainMainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by turbo on 2016/6/21.
 */
public class TokenRequest
{
    public LoadingActivity loadingActivity;
    public String token_url;
    public JsonObjectRequest jsonObjectRequest;

    public TokenRequest(LoadingActivity loadingActivity, String token_url)
    {
        this.loadingActivity = loadingActivity;
        this.token_url = token_url;
    }

    public void sendTokenRequest()
    {
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", "admin");
        params.put("password", "admin");
        params.put("rememberMe", "true");
        JSONObject jsonObject = new JSONObject(params);

        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, token_url, jsonObject, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                try {
                    String theToken = response.getString("id_token");
                    Log.i("access_token", theToken);
                    loadingActivity.refreshActivity(theToken);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });

        AppController.getInstance().getRequestQueue().add(jsonObjectRequest);

        /*StringRequest stringRequest = new StringRequest(Request.Method.GET, token_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("sdfsf", response.toString());
                appController.setAccess_token(response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                appController.setAccess_token(error.toString());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", "user");
                params.put("password", "user");
                params.put("rememberMe", "true");
                return params;
            }
        };*/
    }
}
