package com.somoplay.eadate.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.somoplay.eadate.R;

import java.util.ArrayList;

/**
 * Created by jeff qiu on 4/11/2016.
 */
public class ListImageTextAdapter extends BaseAdapter {

    int[] images;
    String[] titles;
    ArrayList<ListItem> Items;
    Context context;

    public ListImageTextAdapter(Context context, int[] images, String[] titles) {
        this.context = context;
        Items = new ArrayList<ListItem>();
        this.images = images;
        this.titles = titles;

        //Resources res = context.getResources();
        //int[] images = {R.drawable.me_akb, R.drawable.me_ake, R.drawable.me_akc, R.drawable.me_akd};
        //String[] menus = res.getStringArray(R.array.me_menu_chinese);

        for (int i=0; i<images.length; i++) {
            ListItem item = new ListItem(images[i], titles[i]);
            Items.add(item);
        }
    }

    @Override
    public int getCount() {
        return Items.size();
    }

    @Override
    public Object getItem(int i) {
        return Items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        /*
        1. inflate the view of the item
        2. find the child component views inside the item
        3. set the values to all child views
         */
        // LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemview = inflater.inflate(R.layout.list_image_text, viewGroup, false);

        ImageView image = (ImageView) itemview.findViewById(R.id.menuImage);
        TextView text = (TextView) itemview.findViewById(R.id.menuText);

        ListItem item = Items.get(position);

        image.setImageResource(item.image);
        text.setText(item.text);

        return itemview;

    }

    private class ListItem {

        int image;
        String text;
        ImageView Vimage;
        TextView Vname;

        public ListItem(int image, String text) {

            this.image = image;
            this.text = text;
           // Vimage = (ImageView) view.findViewById(R.id.menuImage);
           // Vname = (TextView) view.findViewById(R.id.menuText);
        }

        /*
        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public String getMeMenu() {
            return meMenu;
        }

        public void setMeMenu(String meMenu) {
            this.meMenu = meMenu;
        }
        */
    }
}

