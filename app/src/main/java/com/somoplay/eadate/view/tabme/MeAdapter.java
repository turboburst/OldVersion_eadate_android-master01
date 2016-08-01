package com.somoplay.eadate.view.tabme;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.somoplay.eadate.R;

import java.util.ArrayList;

/**
 * Created by work on 2016-02-21.
 */
public class MeAdapter extends BaseAdapter {

    ArrayList<MeMenu> meList;
    Context context;

    public MeAdapter(Context context) {
        this.context = context;
        meList = new ArrayList<MeMenu>();

        Resources res = context.getResources();
        int[] images = {R.drawable.me_akb, R.drawable.me_ake, R.drawable.me_akc, R.drawable.me_akd};
        String[] menus = res.getStringArray(R.array.me_menu_chinese);

        for (int i=0; i<images.length; i++) {
            MeMenu meMenus = new MeMenu(images[i], menus[i]);
            meList.add(meMenus);
        }
    }

    @Override
    public int getCount() {
        return meList.size();
    }

    @Override
    public Object getItem(int i) {
        return meList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        /*
        1. get the root view
        2. use the root view to find other views
        3. set the values
         */
       // LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater inflater = LayoutInflater.from(context); //.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.me_list_item, viewGroup, false);

        ImageView image = (ImageView) item.findViewById(R.id.menuImage);
        TextView name = (TextView) item.findViewById(R.id.menuText);

        MeMenu temp = meList.get(i);

        image.setImageResource(temp.image);
        name.setText(temp.meMenu);

        return item;

    }
}
