package com.somoplay.eadate.view.tabnearby;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.somoplay.eadate.R;
import com.somoplay.eadate.view.App.AppController;
import com.somoplay.eadate.view.Profile;
import com.somoplay.eadate.view.SimpleDividerItemDecoration;
import com.somoplay.eadate.view.adapter.FavouritePeopleAdapter;
import com.somoplay.eadate.view.tab_top_model.BaseFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFrag1_nearby extends BaseFragment {

    private static final String DATA_NAME = "name";

    private static final String URL = "http://ec2-52-4-197-144.compute-1.amazonaws.com:8080/api/userList";
    private List<Profile> profileList = new ArrayList<>();

    private RecyclerView recyclerView;
    private FavouritePeopleAdapter favouritePeopleAdapter;
    private String token_type = "Bearer";
    private String access_token = "";

    private String title = "";

    public static TabFrag1_nearby newInstance(String title, int indicatorColor, int dividerColor) {
        TabFrag1_nearby f = new TabFrag1_nearby();
        f.setTitle(title);
        f.setIndicatorColor(indicatorColor);
        f.setDividerColor(dividerColor);
        //f.setIconResId(iconResId);
        //pass data
        Bundle args = new Bundle();
        args.putString(DATA_NAME, title);
        f.setArguments(args);
        return f;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.nearby_tabfrg1, container, false);
        AppController appController = AppController.getInstance();
        //access_token = appController.getAccess_token();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_people);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this.getContext()));

        /*LYTokenRequest theTokenRequest = new LYTokenRequest(this);
        theTokenRequest.sendTokenRequest("http://192.168.2.10:8080/api/authenticate");*/
        //StringRequest wordReq = new StringRequest
        favouritePeopleAdapter = new FavouritePeopleAdapter(getActivity(), profileList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(favouritePeopleAdapter);

        StringRequest wordReq = new StringRequest(Request.Method.GET, "http://eadate.com/api/userList",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("lukenearBy ", response.toString());
                        try {
                            JSONArray entries = new JSONArray(response);

                            // Parsing json
                            for (int i = 0; i < entries.length(); i++) {
                                try {

                                    JSONObject obj = entries.getJSONObject(i);

                                    Profile profile = new Profile();
                                    profile.setName(obj.getString("displayName"));

                                    JSONObject avatarObj = obj.getJSONObject("avatar");
                                    profile.setImageUrl(avatarObj.getString("medium"));

                                    profileList.add(profile);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                        favouritePeopleAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("lukeerror", "Error: " + error.getMessage());
            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Accept","application/json");
                params.put("Authorization",token_type + " " + access_token);
                return params;
            }
        };
        AppController.getInstance().getRequestQueue().add(wordReq);

        return view;
    }
}
