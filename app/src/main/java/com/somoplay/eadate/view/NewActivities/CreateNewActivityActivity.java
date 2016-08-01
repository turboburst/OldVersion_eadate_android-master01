package com.somoplay.eadate.view.NewActivities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.Interfaces.SaveDataToBundleInterface;
import com.somoplay.eadate.view.Interfaces.WebRequestActivityInterface;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//@EActivity(R.layout.activity_createnewactivity_layout1)
public class CreateNewActivityActivity extends FragmentActivity implements WebRequestActivityInterface{

    public Bundle bundle;

    /*public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;*/
    public ViewPager viewPager;
    public FragmentPagerAdapter fragmentPagerAdapter;
    public List<Fragment> fragmentList;
    public Fragment fragmentGeneral;
    public Fragment fragmentAddActivities;
    public Fragment fragmentTimerPicker;
    public Fragment fragmentSubmit;
    public Fragment fragmentDescription;
    public TextView stepTextView;
    public Button preBtn, nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createnewactivity_layout1);
        init();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {}

            @Override
            public void onPageScrollStateChanged(int state)
            {
                if(state == 0)
                {
                    if(!preBtn.getText().equals("pre"))
                    {
                        preBtn.setText("pre");
                    }
                    if(!nextBtn.getText().equals("Next"))
                    {
                        nextBtn.setText("Next");
                    }
                    stepTextView.setText("Step " + (viewPager.getCurrentItem() + 1));
                    if(viewPager.getCurrentItem() == 0 && viewPager.getCurrentItem() != viewPager.getAdapter().getCount() - 1)
                    {

                        preBtn.setEnabled(false);
                    }
                    else if(viewPager.getCurrentItem() > 0 && viewPager.getCurrentItem() != viewPager.getAdapter().getCount() - 1)
                    {
                        if(preBtn.isEnabled() == false)
                        {
                            preBtn.setEnabled(true);
                        }
                    }
                    else if(viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1)
                    {
                        preBtn.setText("Redo");
                        nextBtn.setText("Submit");

                        preBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //sharedPreferences.edit().clear().commit();
                                CreateNewActivityActivity.this.finish();

                                //getActivity().startActivity(getActivity(),CreateNewActivityActivity.class);
                            }
                        });

                        nextBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(CreateNewActivityActivity.this,
                                        ((FrgSubmit)((FragmentPagerAdapter)viewPager.getAdapter()).getItem(fragmentList.size() - 1)).getTitlePartTv().getText().toString()
                                        + " has been submitted", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                else if(state == 1)
                {
                    onClick();
                }
            }
        });

        onClick();
    }

    private void onClick()
    {
        preBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(nextBtn.isEnabled() == false)
                {
                    nextBtn.setEnabled(true);
                }

                if(viewPager.getCurrentItem() > 0)
                {
                    if(viewPager.getCurrentItem() == 1)
                    {
                        preBtn.setEnabled(false);
                    }
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SaveDataToBundleInterface)((FragmentPagerAdapter)viewPager.getAdapter()).getItem(viewPager.getCurrentItem())).SaveDataToBundle();
                if(viewPager.getCurrentItem() == 3)
                {
                    Fragment finalFrg = ((FragmentPagerAdapter)viewPager.getAdapter()).getItem(viewPager.getCurrentItem() + 1);
                    if(finalFrg.getClass().getSimpleName().equals("FrgSubmit"))
                    {
                        ((FrgSubmit)finalFrg).setDetailPartTvText("Activity Detail:" + bundle.getString("DETAIL"));
                    }
                }
                if(preBtn.isEnabled() == false)
                {
                    preBtn.setEnabled(true);
                }
                if(viewPager.getCurrentItem() < viewPager.getAdapter().getCount() - 1)
                {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
        });
    }

    private void init()
    {
        bundle = new Bundle();

        preBtn = (Button) findViewById(R.id.newactivity_previousBtn);
        nextBtn = (Button) findViewById(R.id.newactivity_nextBtn);
        stepTextView = (TextView) findViewById(R.id.newactivity_top_text_id);
        viewPager = (ViewPager) findViewById(R.id.viewpagerid);
        /*sharedPreferences = getSharedPreferences("PREF_newActivity", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();*/

        fragmentList = new ArrayList<Fragment>();

        fragmentGeneral = new FrgGeneral(viewPager, stepTextView);
        fragmentAddActivities = new FrgAddActivities(viewPager, stepTextView);
        fragmentTimerPicker = new FrgTimePicker(viewPager, stepTextView);
        fragmentDescription = new FrgDescription(viewPager, stepTextView);
        fragmentSubmit = new FrgSubmit(stepTextView);

        fragmentList.add(fragmentGeneral);
        fragmentList.add(fragmentAddActivities);
        fragmentList.add(fragmentTimerPicker);
        fragmentList.add(fragmentDescription);
        fragmentList.add(fragmentSubmit);


        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
        {
            @Override
            public Fragment getItem(int position)
            {
                return fragmentList.get(position);
            }

            @Override
            public int getCount()
            {
                return fragmentList.size();
            }
        };

        viewPager.setAdapter(fragmentPagerAdapter);

        preBtn.setEnabled(false);


    }

    public Bundle getBundle() {
        return bundle;
    }

    @Override
        public void onResume ()
        {
            super.onResume();
            //title.setText(sharedPreferences.getString("TITLE", ""));
        }


    @Override
    public JSONObject webRequestCallBack(JSONObject jsonObject) {
        return null;
    }
}
