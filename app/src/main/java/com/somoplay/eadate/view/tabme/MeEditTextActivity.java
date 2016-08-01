package com.somoplay.eadate.view.tabme;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.Interfaces.WebRequestActivityInterface;

import org.json.JSONObject;

/**
 * Created by work on 2016-02-21.
 */
public class MeEditTextActivity extends Activity implements WebRequestActivityInterface {

    private EditText txtStatus;
    private TextView activityTitle, lblCount, saveTx;
    private ImageButton backBtn;
    //    private Button btnTweet;
    private static int MAX_COUNT;
    private int activityId;
    private String defaultName, defaultSignature, defaultAddress, tempString;

    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.me_activity_edit_text);

        activityTitle = (TextView) findViewById(R.id.editTextTitle);
        txtStatus = (EditText)findViewById(R.id.txtStatus);
        lblCount = (TextView)findViewById(R.id.lblCount);
        saveTx = (TextView)findViewById(R.id.saveBtn);
        backBtn = (ImageButton)findViewById(R.id.back);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            activityId = extras.getInt("ACTIVITYID");
        }

        sharedpreferences = getSharedPreferences("PSRSONALINFO", MODE_PRIVATE);
        editor = sharedpreferences.edit();

        sharedpreferences = getSharedPreferences("PSRSONALINFO", MODE_PRIVATE);
        defaultName = sharedpreferences.getString("username", "null");
        defaultSignature = sharedpreferences.getString("signature", "null");
        defaultAddress = sharedpreferences.getString("defaultAddress", "null");


        if (defaultName != "null" && activityId == 0) {
            txtStatus.setText(defaultName);
        }
        else if (defaultAddress != "null" && activityId == 4) {
            txtStatus.setText(defaultAddress);
        }
        else if (defaultSignature != "null" && activityId == 7) {
            txtStatus.setText(defaultSignature);
        }


        saveTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo(v);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(v);
            }
        });

        if(activityId==0)

        {
            activityTitle.setText("Modify Your Name");
            MAX_COUNT = 10 - txtStatus.length();
        }

        else if(activityId==7)

        {
            activityTitle.setText("Modify Your Signature");
            MAX_COUNT = 140 - txtStatus.length();
        }

        lblCount.setText(Integer.toString(MAX_COUNT));

        if (activityId == 4) {
            MAX_COUNT = 100 - txtStatus.length();
            lblCount.setText("");
        }

        // Attached Listener to Edit Text Widget
        txtStatus.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

                if (count <= 0) {
                    tempString = txtStatus.getText().toString();
                    txtStatus.setText(tempString);
                    lblCount.setEnabled(false);
                    lblCount.setFocusableInTouchMode(false);
                    lblCount.setFocusable(false);
                }
            }

            public void afterTextChanged(Editable s) {

                // Display Remaining Character with respective color
                int count = MAX_COUNT - s.length();
                lblCount.setText(Integer.toString(count));
            }
        });
    }

    private void saveInfo(View view) {
        if (activityId == 0) {
            editor.putString("username", txtStatus.getText().toString());
        }
        else if (activityId == 4){
            editor.putString("defaultAddress", txtStatus.getText().toString());
        }
        else if (activityId == 7) {
            String signature = "";
            for (int i=0; i < 10 ; i++) {
                signature = signature + String.valueOf(txtStatus.getText().toString().charAt(i));
            }
            editor.putString("signature", signature);
        }
        editor.commit();

        Intent intent = new Intent(MeEditTextActivity.this, MePersonalInfoActivity.class);
        startActivity(intent);
    }

    private void back(View view) {
        finish();
    }

    @Override
    public JSONObject webRequestCallBack(JSONObject jsonObject) {
        return null;
    }
}
