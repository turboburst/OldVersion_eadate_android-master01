package com.somoplay.eadate.view.tabme;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.somoplay.eadate.R;

import java.util.ArrayList;

/**
 * Created by work on 2016-02-21.
 */
public class MePersonalInfoAdapter extends BaseAdapter {

    private ArrayList<MePersonalInfo> infoList;
    private MePersonalDetil personalDetil = new MePersonalDetil();
    private Context context;
    private SharedPreferences sharedpreferences;

    private String name;
    private String id;
    private String qrcode;
    private String gender;
    private String address;
    private String age;
    private String marriage;
    private String signature;
    private String region;
    private String school;
    private String job;

    public MePersonalInfoAdapter(Context context) {
        this.context = context;
        infoList = new ArrayList<MePersonalInfo>();

        sharedpreferences = context.getSharedPreferences("PSRSONALINFO", context.MODE_PRIVATE);
        name = sharedpreferences.getString("username", "Somoplay");
        id = sharedpreferences.getString("userid", "123456");
        qrcode = sharedpreferences.getString("qrcode", "");
        gender = sharedpreferences.getString("gender", "male");
        address = sharedpreferences.getString("address", "");
        age = sharedpreferences.getString("age", "20岁");
        marriage = sharedpreferences.getString("marriage", "single");
        signature = sharedpreferences.getString("signature", "");
        region = sharedpreferences.getString("region", "Canada");
        school = sharedpreferences.getString("school", "UTC");
        job = sharedpreferences.getString("job", "IT");

        Resources res = context.getResources();
        String[] infos = res.getStringArray(R.array.personal_info_chinese);
        String[] details = {name, id, qrcode, gender, address, age, marriage, signature, region, school, job};

        for (int i=0; i<infos.length; i++) {
            MePersonalInfo personalInfo = new MePersonalInfo(infos[i], details[i]);
            infoList.add(personalInfo);
        }
    }

    @Override
    public int getCount() {
        return infoList.size();
    }

    @Override
    public Object getItem(int i) {
        return infoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.me_list_item_personalinfo, viewGroup, false);

        TextView info = (TextView) item.findViewById(R.id.infoText);
        TextView detail = (TextView) item.findViewById(R.id.detailText);

        MePersonalInfo temp = infoList.get(i);

        info.setText(temp.getChangeItem());
        detail.setText(temp.getDetail());

        return item;
    }
}
