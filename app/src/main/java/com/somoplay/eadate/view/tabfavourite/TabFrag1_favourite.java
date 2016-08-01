package com.somoplay.eadate.view.tabfavourite;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.somoplay.eadate.R;
import com.somoplay.eadate.view.PeopleDetailActivity;
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


public class TabFrag1_favourite extends BaseFragment {

    private static final String DATA_NAME = "name";

    private static final String URL = "http://ec2-52-4-197-144.compute-1.amazonaws.com:8080/api/userList";
    private static final String url_json = "http://api.androidhive.info/json/movies.json";

    private List<Profile> profileList = new ArrayList<>();

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private FavouritePeopleAdapter favouritePeopleAdapter;

    private String token_type = "Bearer";
    private String access_token = "bd96b0b3-af8b-464a-9661-897ba1cac277";
                                // 93916fab-e9dc-4f1b-acd2-a042b64e3837
//    private String token_type;
//    private String access_token;

    private String title = "";

    private ProgressDialog pDialog;


    public static TabFrag1_favourite newInstance(String title, int indicatorColor, int dividerColor) {
        TabFrag1_favourite f = new TabFrag1_favourite();
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


        pDialog = new ProgressDialog(this.getContext());
        pDialog.setMessage("Loading...");
        pDialog.show();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.favourite_tab1, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_people);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this.getContext()));

        JsonArrayRequest profileReq1 = new JsonArrayRequest(url_json,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("luke fav", response.toString());

                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);

                                Profile profile = new Profile();
                                profile.setName(obj.getString("title"));

                               // JSONObject avatarObj = obj.getJSONObject("avatar");
                                profile.setImageUrl(obj.getString("image"));
                                ///////////////////////////////////////
                                //parsedStr += "Title:" + obj.getString("title") + "\n";
                                //parsedStr += "imge:" + obj.getString("image") + "\n";
                                //parsedStr += "rate:" + obj.getString("rating") + "\n";
                                //parsedStr += "release Year:" + obj.getString("releaseYear") + "\n\n";
                                //////////////////////////////////////////
                                profileList.add(profile);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        // after get response, and profileList is finished to get data, need to tell adapter
                        // to make change at this point, otherwise it can not get the new data
                        // another way is using callback do this
                        myAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("luke", "Error: " + error.getMessage());
                hidePDialog();
            }
        });

        /*{

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Accept", "application/json");
                params.put("Authorization", token_type + " " + access_token);
                return params;
            }

        };*/

       /*RequestQueue requestQueue1 = VolleySingleton.getInstance().getRequestQueue();
       requestQueue1.add(profileReq1);*/


        myAdapter = new MyAdapter(getActivity(), profileList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(myAdapter);
////////////////////////////////////////////////////////////////////////////////////////////
        return view;


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    //*************************************************************************
    /*
    *  RecyclerView adapter for this tab list items
    *
     */
    private static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
            implements View.OnClickListener {

        private final Context context;

        private LayoutInflater mInflater;
        private List<Profile> profileItems;
        /*ImageLoader imageLoader = VolleySingleton.getInstance().getImageLoader();*/

        public MyAdapter(Context context, List<Profile> profileItems) {

            this.context = context;
            mInflater = LayoutInflater.from(context);
            this.profileItems = profileItems;
        }

        private OnRecyclerViewItemClickListener mOnItemClickListener = null;

        @Override
        public void onClick(View v) {

            if(mOnItemClickListener!= null){
                mOnItemClickListener.onItemClick(v);
            }
        }

        public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
            this.mOnItemClickListener = listener;
        }

        public interface OnRecyclerViewItemClickListener {
            void onItemClick(View view);
        }


        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

            View view = mInflater.inflate(R.layout.list_row_people, viewGroup, false);
            MyViewHolder myViewHolder = new MyViewHolder(view);

            view.setOnClickListener(this);
            return myViewHolder;

        }

        @Override
        public void onBindViewHolder(final MyAdapter.MyViewHolder myViewHolder, int position) {

            // getting profile data for the row
            Profile profile = profileItems.get(position);

            myViewHolder.name.setText(profile.getName());

            // myViewHolder.image.setImageUrl(ImageURL + profile.getImageUrl(), imageLoader);
            /*myViewHolder.image.setImageUrl( profile.getImageUrl(), imageLoader);*/
            Log.d("tag", profile.getImageUrl());

        }



        @Override
        public int getItemCount() {
            return profileItems.size();
        }


        class MyViewHolder extends RecyclerView.ViewHolder {  //implements View.OnClickListener{

            private TextView name;
            private NetworkImageView image;
            private RelativeLayout mLayout;

            public MyViewHolder(View itemView) {
                super(itemView);

                name = (TextView) itemView.findViewById(R.id.name);
                image = (NetworkImageView) itemView.findViewById(R.id.profile_img);
                mLayout =(RelativeLayout) itemView.findViewById(R.id.people_layout);

                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(context, PeopleDetailActivity.class);

                        image.setDrawingCacheEnabled(Boolean.TRUE);
                        intent.putExtra("BITMAP", image.getDrawingCache());
                        intent.putExtra("NAME", name.getText());
                        context.startActivity(intent);
                    }
                });
            }
        }
    }
}
