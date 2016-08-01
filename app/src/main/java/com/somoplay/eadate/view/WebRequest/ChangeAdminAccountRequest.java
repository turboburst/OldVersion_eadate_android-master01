package com.somoplay.eadate.view.WebRequest;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.somoplay.eadate.view.App.AppController;
import com.somoplay.eadate.view.App.Constances;
import com.somoplay.eadate.view.main.SignupActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by turbo on 2016/6/23.
 */
public class ChangeAdminAccountRequest
{
    public Context context;
    public JSONArray jsonArray;
    public JSONObject jsonObject;
    public JsonObjectRequest jsonObjectRequest;
    public RequestQueue requestQueue;
    public String changeAdminAccount_url;
    public String access_token;

    public ChangeAdminAccountRequest(Context context)
    {
        this.context = context;
    }

    public void sendChangeAdminAccountRequest()
    {
        //this.access_token = AppController.getInstance().getAccess_token();
        this.changeAdminAccount_url = Constances.Account_url;
        requestQueue = AppController.getRequestQueue();
        jsonArray = new JSONArray();
        jsonArray.put("string");
        jsonObject = new JSONObject();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("activated", true);
        params.put("authorities", jsonArray);
        params.put("email", "myemail@hotmail.com");
        params.put("firstName", "myfirstname");
        params.put("langKey", "en");
        params.put("lastName", "mylastname");
        params.put("login", "admin");

        jsonObject = new JSONObject(params);
        requestQueue = AppController.getRequestQueue();

        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, this.changeAdminAccount_url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("changeAdminsuccess", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("ErrorChangeAdmin", error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String, String>();
                header.put("Content-Type", "application/json");
                header.put("Authorization", "Bearer " + access_token);
                return header;
            }

        };

        requestQueue.add(jsonObjectRequest);
    }
}
