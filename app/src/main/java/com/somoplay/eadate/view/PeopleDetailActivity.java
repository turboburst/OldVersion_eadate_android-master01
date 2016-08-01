package com.somoplay.eadate.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.Interfaces.WebRequestActivityInterface;

import org.json.JSONObject;

public class PeopleDetailActivity extends AppCompatActivity implements WebRequestActivityInterface {
    private TextView NameView;
    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fav_people_detail);

        NameView = (TextView) findViewById(R.id.name);
        mImageView = (ImageView)findViewById(R.id.people_image);

        Intent intent = getIntent();

        if (intent != null && intent.getStringExtra("NAME") != null) {

            Bitmap bitmap = getIntent().getParcelableExtra("BITMAP");
            String namestr = intent.getStringExtra("NAME");

            mImageView.setImageBitmap(bitmap);
            NameView.setText(namestr);
            NameView.setTextColor(Color.RED);
        }
    }

    @Override
    public JSONObject webRequestCallBack(JSONObject jsonObject) {
        return null;
    }
}
