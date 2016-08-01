/*
package com.somoplay.eadate.view.tabnearby;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.somoplay.eadate.view.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

*/
/**
 * Created by turbo on 2016/6/19.
 *//*

public class LYTokenRequest {

    public TabFrag1_nearby fragment1;

    public LYTokenRequest(TabFrag1_nearby fragment1)
    {
        this.fragment1 = fragment1;
    }

    public void sendTokenRequest(String url)
    {
        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", "user");
        params.put("password", "user");
        params.put("rememberMe", "true");
        JSONObject jsonObject = new JSONObject(params);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(fragment1.getContext(), response.toString(), Toast.LENGTH_LONG).show();
                try {
                    String id_token = response.getString("id_token");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(fragment1.getContext(), volleyError.toString(), Toast.LENGTH_LONG).show();

            }
        });

        requestQueue.add(stringRequest);
    }
}
*/
