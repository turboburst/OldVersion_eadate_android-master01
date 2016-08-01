package com.somoplay.eadate.view.WebRequest;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.somoplay.eadate.view.App.AppController;
import com.somoplay.eadate.view.App.Constances;
import com.somoplay.eadate.view.main.MainMainActivity;
import com.somoplay.eadate.view.main.SignupActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by turbo on 2016/6/22.
 */
public class RegisterRequest
{
    public Context context;
    public RequestQueue requestQueue;
    public JSONArray jsonArray;
    public String register_url;
    public JsonObjectRequest jsonObjectRequest;

    public RegisterRequest(Context context)
    {
        this.context = context;
        this.register_url = Constances.Register_url;
    }

    public void sendRegisterRequest()
    {
        requestQueue = AppController.getRequestQueue();

        jsonArray = new JSONArray();
        jsonArray.put("user");

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("activated", true);
        params.put("authorities", jsonArray);
        params.put("createdDate", "2016-06-22T20:03:25.376Z");
        params.put("email", "turboburst_3@hotmail.com");
        params.put("firstName", "JIANBO");
        params.put("id", 0);
        params.put("langKey", "en");
        params.put("lastModifiedBy", "user");
        params.put("lastModifiedDate", "2016-06-22T20:03:25.376Z");
        params.put("lastName", "SHI");
        params.put("login", "turboburst3");
        params.put("password", "123456789");

        JSONObject jsonObject = new JSONObject(params);

        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, this.register_url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ((SignupActivity)context).saveData("Create user successfully");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ((SignupActivity)context).saveData(error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
