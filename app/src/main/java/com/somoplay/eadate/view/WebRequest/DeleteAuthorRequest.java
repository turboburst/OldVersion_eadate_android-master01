package com.somoplay.eadate.view.WebRequest;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.somoplay.eadate.view.App.AppController;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by turbo on 2016/7/8.
 */
public class DeleteAuthorRequest {

    private Context context;
    private String url;
    private int id;
    private JsonObjectRequest jsonObjectRequest;
    private RequestQueue requestQueue;
    private String accesstoken;
    private JSONObject jsonObject;

    public DeleteAuthorRequest(Context context, int id)
    {
        this.context = context;
        this.id = id;
        this.url += ("/" + id);
        requestQueue = AppController.getRequestQueue();
    }

    public void sendDeleteAuthorRequest()
    {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Todo callback method in context

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("DeleteRequestError", error.toString());

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String, String>();
                header.put("Accept", "application/json");
                header.put("Authorization", "Bearer " + accesstoken);
                return header;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }
}
