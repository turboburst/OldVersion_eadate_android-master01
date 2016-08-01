package com.somoplay.eadate.view.WebRequest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.somoplay.eadate.view.App.AppController;
import com.somoplay.eadate.view.App.Constances;
import com.somoplay.eadate.view.main.MainActivity;
import com.somoplay.eadate.view.main.SignupActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by turbo on 2016/6/23.
 */
public class GetAdminAccountRequest
{
    public Context context;
    public RequestQueue requestQueue;
    public String access_token;
    public JsonObjectRequest jsonObjectRequest;
    public JSONArray jsonArray;
    public JSONObject jsonObject;
    public String getAdminAccount_url;

    public GetAdminAccountRequest(Context context)
    {
        this.context = context;
    }

    public void sendGetAdminAccountRequest()
    {
        //this.access_token = AppController.getInstance().getAccess_token();
        this.getAdminAccount_url = Constances.Account_url;
        requestQueue = AppController.getRequestQueue();
        jsonArray = new JSONArray();
        jsonArray.put("string");
        jsonObject = new JSONObject();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("activated", true);
        params.put("authorities", jsonArray);
        params.put("email", "string");
        params.put("firstName", "string");
        params.put("langKey", "string");
        params.put("lastName", "string");
        params.put("login", "string");

        jsonObject = new JSONObject(params);

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, this.getAdminAccount_url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("accountrequest", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("accounterror", error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String, String>();
                header.put("Accept", "application/json");
                header.put("Authorization", "Bearer " + access_token);
                return header;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }
}
