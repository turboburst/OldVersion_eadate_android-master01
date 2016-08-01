package com.somoplay.eadate.view.tabme;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.Interfaces.WebRequestActivityInterface;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by work on 2016-02-21.
 */
public class MeJobActivity extends Activity implements WebRequestActivityInterface {

    private ListView jobLv,jobDetailLv;
    private ArrayList<String> jobList = new ArrayList<String>();
    private ArrayList<String> jobDetailList = new ArrayList<String>();
    private String[] job, jobDetail;
    private int jobId;
    private boolean jobSelected = false;
    private String defaultJobFirstLevel, defaultJobSecondLevel, defaultJob;

    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_activity_job);

        jobLv = (ListView) findViewById(R.id.listview);
        jobDetailLv = (ListView) findViewById(R.id.listview2);

        sharedpreferences = getSharedPreferences("PSRSONALINFO", MODE_PRIVATE);
        defaultJobFirstLevel = sharedpreferences.getString("jobFirstLevel", "null");
        defaultJobSecondLevel = sharedpreferences.getString("jobSecondLevel", "null");

        ArrayAdapter<String> jobAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getJob()) ;
        jobLv.setAdapter(jobAdapter);

        jobLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(jobList.get(position));
                jobId = position;
                defaultJobFirstLevel = jobList.get(position);
                final ArrayAdapter<String> jobDetailAdapter = new ArrayAdapter<String>(MeJobActivity.this, android.R.layout.simple_list_item_1, getJobDetail()) ;
                jobDetailLv.setAdapter(jobDetailAdapter);

                jobDetailLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        System.out.println(jobDetailList.get(position));

                        defaultJobSecondLevel = jobDetailList.get(position);
                        defaultJob = defaultJobFirstLevel + " -> " + defaultJobSecondLevel;
                        sharedpreferences = getSharedPreferences("PSRSONALINFO", MODE_PRIVATE);
                        editor = sharedpreferences.edit();
                        editor.putString("jobFirstLevel", defaultJobFirstLevel);
                        editor.putString("jobSecondLevel", defaultJobSecondLevel);
                        editor.putString("job", defaultJob);
                        editor.commit();

                        Intent intent = new Intent(MeJobActivity.this, MePersonalInfoActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    public List<String> getJob() {

        Resources jobRes = this.getResources();

        job = jobRes.getStringArray(R.array.job_chinese);
        for (int i=0; i<job.length; i++) {
            jobList.add(job[i]);
        }

        return jobList;
    }

    public List<String> getJobDetail() {

        jobDetailList.clear();
        Resources jobDetailRes = this.getResources();

        if (jobSelected) {
            switch (jobId) {
                case 0:
                    jobDetail = jobDetailRes.getStringArray(R.array.information_chinese);
                    break;
                case 1:
                    jobDetail = jobDetailRes.getStringArray(R.array.finance_chinese);
                    break;
                case 2:
                    jobDetail = jobDetailRes.getStringArray(R.array.business_chinese);
                    break;
                case 3:
                    jobDetail = jobDetailRes.getStringArray(R.array.engineering_chinese);
                    break;
                case 4:
                    jobDetail = jobDetailRes.getStringArray(R.array.transport_chinese);
                    break;
                case 5:
                    jobDetail = jobDetailRes.getStringArray(R.array.media_chinese);
                    break;
                case 6:
                    jobDetail = jobDetailRes.getStringArray(R.array.entertainment_chinese);
                    break;
                case 7:
                    jobDetail = jobDetailRes.getStringArray(R.array.commonality_chinese);
                    break;
                case 8:
                    jobDetail = new String[]{"学生"};
                    break;
                case 9:
                    jobDetail = new String[]{"无"};
                    break;
                default:
                    break;
            }
        }
        else {
            jobDetail = jobDetailRes.getStringArray(R.array.information_chinese);
            jobSelected = true;
        }

        for (int i = 0; i < jobDetail.length; i++) {
            jobDetailList.add(jobDetail[i]);
        }

        return jobDetailList;
    }

    @Override
    public JSONObject webRequestCallBack(JSONObject jsonObject) {
        return null;
    }
}
