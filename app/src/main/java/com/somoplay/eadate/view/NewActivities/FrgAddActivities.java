package com.somoplay.eadate.view.NewActivities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.Interfaces.SaveDataToBundleInterface;
import com.somoplay.eadate.view.adapter.ListImageTextAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FrgAddActivities extends Fragment implements SaveDataToBundleInterface
{
    private Bundle bundle;

    private Button createButton;
    private Button searchButton;
    private ViewPager viewPager;
    private TextView stepTextView;
    //private Button preBtn, nextBtn;
    private ScrollView createScrollView;
    private LinearLayout searchfragmentlayout;

    private static final String DATA_NAME = "name";
    private TextView streetTv, cityTv, postcodeTv, storeTv, telphoneTv;
    private ListView listView;

    private int year, month, day, defaultYear, defaultMonth, defaultDay;
    private String title;

    /*private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;*/

    private static final String STREET = "street";
    private static final String CITY = "city";
    private static final String POSTCODE = "postcode";
    private static final String STORE = "store";
    private static final String TELPHONE = "telphone";

    public FrgAddActivities()
    {
        super();
    }
    public FrgAddActivities(ViewPager viewPager, TextView stepTextView)
    {
        this.viewPager = viewPager;
        this.stepTextView = stepTextView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newactivity_fragment_add, container, false);
        bundle = ((CreateNewActivityActivity)this.getActivity()).getBundle();
        listView = (ListView) view.findViewById(R.id.listview);
        createScrollView = (ScrollView) view.findViewById(R.id.createScrollviewid);
        searchfragmentlayout = (LinearLayout) view.findViewById(R.id.searchActicityFragmentLayoutid);
        searchfragmentlayout.setVisibility(View.INVISIBLE);
        createButton = (Button) view.findViewById(R.id.addbuttonid);
        searchButton = (Button) view.findViewById(R.id.searchbuttonid);

       // preBtn = (Button) view.findViewById(R.id.newactivity_previousBtn);
       // nextBtn = (Button) view.findViewById(R.id.newactivity_nextBtn);

        int[] images = {R.drawable.me_akb, R.drawable.me_ake, R.drawable.me_akc, R.drawable.me_akd};
        String[] menus = getActivity().getResources().getStringArray(R.array.Activities_Search);

        ListImageTextAdapter myAdapter = new ListImageTextAdapter(getActivity(), images, menus);
        listView.setAdapter(myAdapter);

        streetTv = (EditText)view.findViewById(R.id.newactivity_street);
        cityTv = (EditText)view.findViewById(R.id.newactivity_city);
        postcodeTv = (EditText)view.findViewById(R.id.newactivity_postcode);
        storeTv = (EditText)view.findViewById(R.id.newactivity_store);
        telphoneTv = (EditText)view.findViewById(R.id.newactivity_telphone);

        /*sharedpreferences = getContext().getSharedPreferences("PREF_newActivity", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();*/
        title = bundle.getString("TITLE", "");
        //Toast.makeText(getContext()," saved tilte is " + title, Toast.LENGTH_SHORT).show();

        doClick();

        return view;
    }

    private void doClick()
    {

        /*preBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                viewPager.setCurrentItem(0);
                stepTextView.setText("Step 1");

            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                viewPager.setCurrentItem(2);
                stepTextView.setText("Step 3");
                editor.putString("STORE", storeTv.getText().toString());
                editor.putString("STREET", streetTv.getText().toString());
                editor.putString("CITY", cityTv.getText().toString());
                editor.putString("POSTCODE", postcodeTv.getText().toString());
                editor.putString("TELPHONE", telphoneTv.getText().toString());
                editor.commit();
            }
        });*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("position = ", "pos :" + position);
                Toast.makeText(getContext(),"position " + position, Toast.LENGTH_SHORT).show();

            }
        });

        createButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                createButton.setBackgroundResource(R.drawable.round_pink);
                searchButton.setBackgroundResource(R.drawable.round_gray);
                createScrollView.setVisibility(View.VISIBLE);
                searchfragmentlayout.setVisibility(View.INVISIBLE);
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                searchButton.setBackgroundResource(R.drawable.round_pink_another);
                createButton.setBackgroundResource(R.drawable.round_gray_another);
                createScrollView.setVisibility(View.INVISIBLE);
                searchfragmentlayout.setVisibility(View.VISIBLE);
            }
        });

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
                    bundle.putString("STORE", storeTv.getText().toString());
                    bundle.putString("STREET", streetTv.getText().toString());
                    bundle.putString("CITY", cityTv.getText().toString());
                    bundle.putString("POSTCODE", postcodeTv.getText().toString());
                    bundle.putString("TELPHONE", telphoneTv.getText().toString());
                }

            }
        });
    }

   /* @Override
    public void saveDataToSharedPreference()
    {
        editor.putString("STORE", storeTv.getText().toString());
        editor.putString("STREET", streetTv.getText().toString());
        editor.putString("CITY", cityTv.getText().toString());
        editor.putString("POSTCODE", postcodeTv.getText().toString());
        editor.putString("TELPHONE", telphoneTv.getText().toString());
        editor.commit();
    }*/

    @Override
    public void onResume() {
        super.onResume();
        streetTv.setText(bundle.getString("STREET"));
        cityTv.setText(bundle.getString("CITY"));
        postcodeTv.setText(bundle.getString("POSTCODE"));
        storeTv.setText(bundle.getString("STORE"));
        telphoneTv.setText(bundle.getString("TELPHONE"));
    }

    @Override
    public void SaveDataToBundle() {
        bundle.putString("STORE", storeTv.getText().toString());
        bundle.putString("STREET", streetTv.getText().toString());
        bundle.putString("CITY", cityTv.getText().toString());
        bundle.putString("POSTCODE", postcodeTv.getText().toString());
        bundle.putString("TELPHONE", telphoneTv.getText().toString());
    }
}
