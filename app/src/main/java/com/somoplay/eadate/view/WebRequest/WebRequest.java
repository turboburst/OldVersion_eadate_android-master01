package com.somoplay.eadate.view.WebRequest;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.somoplay.eadate.view.App.AppController;
import com.somoplay.eadate.view.Interfaces.WebRequestActivityInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by turbo on 2016/7/7.
 */
public class WebRequest {

    private String URL;
    private int requestMethod;
    private JSONObject requiredJsonObject;
    private HashMap<String, String> header;

    private WebRequestActivityInterface webRequestActivityInterface;

    public WebRequest(int method, String url, JSONObject requiredJsonObject, HashMap<String, String> header, WebRequestActivityInterface webRequestActivityInterface)
    {
        this.requestMethod = method;
        this.URL = url;
        this.requiredJsonObject = requiredJsonObject;
        this.header = new HashMap<String, String>(header);
        this.webRequestActivityInterface = webRequestActivityInterface;
    }

    public void sendStringWebRequest()
    {
        StringRequest stringRequest = new StringRequest(this.requestMethod, this.URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    webRequestActivityInterface.webRequestCallBack(new JSONObject(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("StringRequestError", error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return new HashMap<String, String>(header);
            }
        };

        AppController.getInstance().getRequestQueue().add(stringRequest);
    }

    public void sendJsonWebRequest()
    {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(this.requestMethod, this.URL, this.requiredJsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                webRequestActivityInterface.webRequestCallBack(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("JsonRequestError", error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return new HashMap<String, String>(header);
            }
        };

        AppController.getInstance().getRequestQueue().add(jsonObjectRequest);

    }
}
