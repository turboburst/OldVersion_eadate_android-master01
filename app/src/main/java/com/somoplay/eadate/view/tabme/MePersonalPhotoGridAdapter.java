package com.somoplay.eadate.view.tabme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.somoplay.eadate.R;

import java.util.List;

/**
 * Created by work on 2016-02-21.
 */
public class MePersonalPhotoGridAdapter extends MeBaseDynamicGridAdapter {

    public MePersonalPhotoGridAdapter(Context context, List<?> items, int columnCount) {
        super(context, items, columnCount);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.me_item_grid, null);
            holder = new ImageViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ImageViewHolder) convertView.getTag();
        }
        holder.build(getItem(position).toString());
        return convertView;
    }

    private class ImageViewHolder {
        //        private TextView titleText;
        private ImageView image;

        private ImageViewHolder(View view) {
//            titleText = (TextView) view.findViewById(R.id.item_title);
            image = (ImageView) view.findViewById(R.id.item_img);
        }

        void build(String title) {
//            titleText.setText(title);
            image.setImageResource(R.drawable.me_image2);
        }
    }
}
