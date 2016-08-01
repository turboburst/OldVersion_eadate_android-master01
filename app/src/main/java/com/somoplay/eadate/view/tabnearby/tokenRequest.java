/*
package com.somoplay.eadate.view.tabnearby;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.somoplay.eadate.view.VolleySingleton;
import com.somoplay.eadate.view.main.MainActivity;

import java.util.HashMap;
import java.util.Map;

*/
/**
 * Created by turbo on 2016/6/11.
 *//*

public class tokenRequest
{
    public TabFrag1_nearby fragment1;

    public tokenRequest(TabFrag1_nearby fragment1)
    {
        this.fragment1 = fragment1;
    }

    public void sendTokenRequest(String url)
    {
        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                fragment1.refreshAccessToken(s.substring(17, 53));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(fragment1.getContext(), volleyError.toString(), Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", "test15");
                params.put("password", "admin");
                params.put("grant_type", "password");
                params.put("scope", "read write");
                params.put("client_secret", "mySecretOAuthSecret");
                params.put("client_id", "eadateapp");
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}
*/
