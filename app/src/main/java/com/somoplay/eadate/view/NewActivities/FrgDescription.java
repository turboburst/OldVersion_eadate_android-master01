package com.somoplay.eadate.view.NewActivities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.Interfaces.SaveDataToBundleInterface;

/**
 * Created by turbo on 2016/5/31.
 */
public class FrgDescription extends Fragment implements SaveDataToBundleInterface
{
    private Bundle bundle;
    private ViewPager viewPager;
    private TextView stepTextView;
    private EditText detailEdittext;
    /*private Button preBtn;
    private Button nextBtn;*/

    public FrgDescription()
    {
        super();
    }

    public FrgDescription(ViewPager viewPager, TextView stepTextView)
    {
        this.viewPager = viewPager;
        this.stepTextView = stepTextView;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View mView = inflater.inflate(R.layout.newactivity_frg_description, container, false);
       // preBtn = (Button) mView.findViewById(R.id.newactivity_previousBtn);
      //  nextBtn = (Button) mView.findViewById(R.id.newactivity_nextBtn);
        bundle = ((CreateNewActivityActivity)this.getActivity()).getBundle();
        detailEdittext = (EditText) mView.findViewById(R.id.newactivity_description_id);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == 1)
                {
                    bundle.putString("DETAIL", detailEdittext.getText().toString());
                }
            }
        });
        doClick();
        return mView;
    }

    private void doClick()
    {
        /*preBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                viewPager.setCurrentItem(2);
                stepTextView.setText("Step 3");
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                viewPager.setCurrentItem(4);
                stepTextView.setText("Step 5");
                editor.putString("DETAIL", detailEdittext.getText().toString());
                editor.commit();
                
                Fragment finalFrg = ((FragmentPagerAdapter)viewPager.getAdapter()).getItem(viewPager.getCurrentItem());
                if(finalFrg.getClass().getSimpleName().equals("FrgSubmit"))
                {
                    ((FrgSubmit)finalFrg).setDetailPartTvText("Activity Detail:" + sharedPreferences.getString("DETAIL", ""));
                }


            }
        });*/

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == 1)
                {
                    bundle.putString("DETAIL", detailEdittext.getText().toString());
                    if(viewPager.getCurrentItem() == 3)
                    {
                        Fragment finalFrg = ((FragmentPagerAdapter)viewPager.getAdapter()).getItem(viewPager.getCurrentItem() + 1);
                        if(finalFrg.getClass().getSimpleName().equals("FrgSubmit"))
                        {
                            ((FrgSubmit)finalFrg).setDetailPartTvText("Activity Detail:" + bundle.getString("DETAIL"));
                        }
                    }
                }

            }
        });
    }

    /*@Override
    public void saveDataToSharedPreference()
    {
        editor.putString("DETAIL", detailEdittext.getText().toString());
        editor.commit();

        Fragment finalFrg = ((FragmentPagerAdapter)viewPager.getAdapter()).getItem(viewPager.getCurrentItem() + 1);

        if(finalFrg.getClass().getSimpleName().equals("FrgSubmit"))
        {
            ((FrgSubmit)finalFrg).setDetailPartTvText("Activity Detail:" + sharedPreferences.getString("DETAIL", ""));
        }
    }*/

    @Override
    public void onResume() {
        super.onResume();
        bundle.putString("DETAIL", detailEdittext.getText().toString());
    }

    @Override
    public void SaveDataToBundle() {
        bundle.putString("DETAIL", detailEdittext.getText().toString());
    }
}
