package com.somoplay.eadate.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.somoplay.eadate.R;
import com.somoplay.eadate.view.PeopleDetailActivity;
import com.somoplay.eadate.view.Profile;

import java.util.List;


/**
 * Created by yangjiachang on 15-10-15.
*/
public class FavouritePeopleAdapter extends RecyclerView.Adapter<FavouritePeopleAdapter.MyViewHolder>
        implements View.OnClickListener {

    private final Context context;

    private LayoutInflater mInflater;
    private List<Profile> profileItems;
    /*ImageLoader imageLoader = VolleySingleton.getInstance().getImageLoader();*/

    public FavouritePeopleAdapter(Context context, List<Profile> profileItems) {

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

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view);
    }


    @Override
    public FavouritePeopleAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = mInflater.inflate(R.layout.list_row_people, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        view.setOnClickListener(this);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(final FavouritePeopleAdapter.MyViewHolder myViewHolder, int position) {

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

 /*

public class FavouritePeopleAdapter extends RecyclerView.Adapter<FavouritePeopleAdapter.MyViewHolder>{
    private final Context context;

    private String TAG = "FavouritePeopleAdapter";

    private static final String ImageURL = Constants.ImageURL;

    private LayoutInflater mInflater;
    private List<Profile> profileItems;
    ImageLoader imageLoader = VolleySingleton.getInstance().getImageLoader();

    private MyItemClickListener mItemClickListener;

    public FavouritePeopleAdapter(Context context, List<Profile> profileItems) {

        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.profileItems = profileItems;
    }


    @Override
    public FavouritePeopleAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = mInflater.inflate(R.layout.list_row_people, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view, mItemClickListener);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(final FavouritePeopleAdapter.MyViewHolder myViewHolder, int position) {

        // getting profile data for the row
        Profile profile = profileItems.get(position);

        myViewHolder.name.setText(profile.getName());

        myViewHolder.image.setImageUrl(ImageURL + profile.getImageUrl(), imageLoader);
        myViewHolder.userId.setText(profile.getOwnerId());

    }



    @Override
    public int getItemCount() {
        return profileItems.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name;
        private NetworkImageView image;
        private TextView userId;

        private MyItemClickListener mListener;

        public MyViewHolder(View itemView,MyItemClickListener listener) {
            super(itemView);

            this.mListener= listener;

            name = (TextView) itemView.findViewById(R.id.name);
            image = (NetworkImageView) itemView.findViewById(R.id.profile_img);
            userId = (TextView) itemView.findViewById(R.id.userId);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onItemClick(v,getLayoutPosition());
            }
        }
    }

    public void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener = listener;
    }

    public interface MyItemClickListener {
        public void onItemClick(View view,int position);
    }
}
 */