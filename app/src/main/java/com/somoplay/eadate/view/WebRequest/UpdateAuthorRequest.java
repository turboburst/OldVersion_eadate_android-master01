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
import com.somoplay.eadate.view.App.Constances;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by turbo on 2016/7/8.
 */
public class UpdateAuthorRequest {
    private Context context;
    private String accesstoken;
    private String url;
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectRequest;

    public UpdateAuthorRequest(Context context)
    {
        this.context = context;
        requestQueue = AppController.getRequestQueue();
    }

    public void sendPutAuthorRequest()
    {
        this.accesstoken = AppController.getInstance().getAccess_token();
        url = Constances.Author_url;

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("birthDate", "2016-07-04");
        params.put("id", 0);
        params.put("name", "zero");
        JSONObject jsonObject = new JSONObject(params);

        jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Todo callback method in context
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               Log.i("UpdateError", error.toString());

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
