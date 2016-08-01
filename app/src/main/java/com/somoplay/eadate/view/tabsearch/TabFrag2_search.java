/*
package com.somoplay.eadate.view.tabsearch;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.SimpleDividerItemDecoration;
import com.somoplay.eadate.view.adapter.FavouritePeopleAdapter;
import com.somoplay.eadate.view.tab_top_model.BaseFragment;

import java.util.ArrayList;

*
 * Created by jeff qiu on 4/10/2016.


public class TabFrag2_search extends BaseFragment {
    private RecyclerView mRecyclerView;
    private myAdapter mListAdapter;
    private ArrayList<String> mDatas;

   // private FavouritePeopleAdapter favouritePeopleAdapter;
    private static final String DATA_NAME = "name";

    private String title = "";

    public static TabFrag2_search newInstance(String title, int indicatorColor, int dividerColor) {
        TabFrag2_search f = new TabFrag2_search();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_tab_common, container, false);

        View view = inflater.inflate(R.layout.search_tab2, container, false);

        mDatas = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            mDatas.add(" Show Item " + i + " LongClick delete me. ");
        }
        Context context = getActivity();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_search);
        mListAdapter = new myAdapter(context, mDatas);

        mRecyclerView.setAdapter(mListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(context));

        return view;

    }

    /
***********************************

    private class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder> {

        // the data source
        private ArrayList<String> data;

        private Context context;
        // constructor
        public myAdapter(Context context,ArrayList<String> data){
            this.data = data;
            this.context = context;
        }

        @Override
        public myAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create the view for viewhoder
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,
                    parent, false);
            MyViewHolder vh = new MyViewHolder(itemView);

            return vh;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            if(position == 2){
                String str = "this is at position " + (position+1);
                holder.mTextView1.setText(str);
                str = "index is " + position;
                holder.mTextView2.setText(str);
            }
            else {
                holder.mTextView1.setText(data.get(position));
                holder.mTextView2.setText(data.get(position));
            }
        }


        @Override
        public int getItemCount() {
            return data.size();
        }

        // viewholder class
        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView1;
            public TextView mTextView2;
            public RelativeLayout mLayout;

            public MyViewHolder(View view) {
                super(view);
                mTextView1 = (TextView) view.findViewById(R.id.name);
                mTextView2 = (TextView) view.findViewById(R.id.detail);
                mLayout = (RelativeLayout)view.findViewById(R.id.viewholder_container);

                mLayout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Log.e("tag", "LongClick On：" + getLayoutPosition());
                        remove(getAdapterPosition()); //remove the item

                        Toast.makeText(context, "LongClick  Delete: " + mTextView1.getText(), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

                mTextView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String str = "Adapter position:  " + getAdapterPosition() + ". Layout Position: " + getLayoutPosition();
                        add(getAdapterPosition(), str);
                        Toast.makeText(context, "one Click , add one item here", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }


        public void add(int position, String value) {
            if(position > data.size()) {
                position = data.size();
            }
            if(position < 0) {
                position = 0;
            }
            data.add(position, value);
*
             * 使用notifyItemInserted/notifyItemRemoved会有动画效果
             * 而使用notifyDataSetChanged()则没有


            notifyItemInserted(position);
        }

        public String remove(int position) {
            if(position > data.size()-1) {
                return null;
            }
            String value = data.remove(position);
            notifyItemRemoved(position);
            return value;
        }
    }

}
*/
