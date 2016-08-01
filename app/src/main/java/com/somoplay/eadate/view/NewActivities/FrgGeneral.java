package com.somoplay.eadate.view.NewActivities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.Interfaces.SaveDataToBundleInterface;

/**
 * Created by turbo on 2016/5/30.
 */
public class FrgGeneral extends Fragment implements SaveDataToBundleInterface
{
    public Bundle bundle;
    public String selectedStyle;
    public String selectedNumber;
    public String selectedPayment;
    public ViewPager viewPager;
    public TextView stepTextView;
    public Spinner typeSpinner;
    public ArrayAdapter typeAdapter;
    public Spinner numberSpinner;
    public ArrayAdapter numberAdapter;
    public Spinner paymenttypeSpinner;
    public ArrayAdapter paymenttypeAdapter;
    //public Button preBtn, nextBtn;

    private String titleStr;
    private String typeStr;
    private String numStr;
    private String paymentStr;

    private EditText title;

    /*private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;*/
    // name of SharedPreferences for newActivity
    private static final String PREF_newActivity = "pref_newActivity";
    private static final String TITLE = "title";
    private static final String TYPE = "type";
    private static final String NUMBER = "number";
    private static final String PAYMENT = "payment";

    public FrgGeneral()
    {
        super();
    }

    public FrgGeneral(ViewPager viewPager, TextView stepTextView)
    {
        this.viewPager = viewPager;
        this.stepTextView = stepTextView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View theView = inflater.inflate(R.layout.fragment_generalinfo, container, false);

        bundle = ((CreateNewActivityActivity)getActivity()).getBundle();

        title = (EditText) theView.findViewById(R.id.newactivity_title);
        if(stepTextView.getText().toString().equals(""))
        {
            stepTextView.setText("Step 1");
        }

        typeSpinner = (Spinner) theView.findViewById(R.id.newactivity_type_spinner);
        typeAdapter = ArrayAdapter.createFromResource(theView.getContext(), R.array.newactivity_types_array, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);
        typeSpinner.setVisibility(View.VISIBLE);

        numberSpinner = (Spinner) theView.findViewById(R.id.newactivity_number_spinner);
        numberAdapter = ArrayAdapter.createFromResource(theView.getContext(), R.array.newactivity_numbers_array, android.R.layout.simple_spinner_item);
        numberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numberSpinner.setAdapter(numberAdapter);
        numberSpinner.setVisibility(View.VISIBLE);

        paymenttypeSpinner = (Spinner) theView.findViewById(R.id.newactivity_paymenttype_spinner);
        paymenttypeAdapter = ArrayAdapter.createFromResource(theView.getContext(), R.array.newactivity_paymenttypes_array, android.R.layout.simple_spinner_item);
        paymenttypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymenttypeSpinner.setAdapter(paymenttypeAdapter);
        paymenttypeSpinner.setVisibility(View.VISIBLE);
        /*preBtn = (Button) theView.findViewById(R.id.newactivity_previousBtn);
        preBtn.setEnabled(false);
        nextBtn = (Button) theView.findViewById(R.id.newactivity_nextBtn);*/

        /*sharedPreferences = getActivity().getSharedPreferences("PREF_newActivity", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();*/

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                selectedStyle = (String) typeSpinner.getSelectedItem();
                bundle.putString("TYPE", selectedStyle);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        numberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                selectedNumber = (String) numberSpinner.getSelectedItem();
                bundle.putString("NUMBER", selectedNumber);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        paymenttypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                selectedPayment = (String) paymenttypeSpinner.getSelectedItem();
                bundle.putString("PAYMENT", selectedPayment);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {

            }

            @Override
            public void onPageScrollStateChanged(int state)
            {
                if(state == 1)
                {
                    bundle.putString("TITLE", title.getText().toString());
                }
            }
        });

        //doClick();

        return theView;
    }

   /* private void doClick()
    {
        nextBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                viewPager.setCurrentItem(1);
                editor.putString("TITLE", title.getText().toString());
                editor.commit();
            }
        });
    }*/

    /*@Override
    public void saveDataToSharedPreference()
    {
        editor.putString("TITLE", title.getText().toString());
        editor.commit();
    }*/

    @Override
    public void onResume() {
        super.onResume();
        title.setText(bundle.getString("TITLE", ""));
    }

    @Override
    public void SaveDataToBundle() {
        bundle.putString("TYPE", selectedStyle);
        bundle.putString("NUMBER", selectedNumber);
        bundle.putString("PAYMENT", selectedPayment);
        bundle.putString("TITLE", title.getText().toString());
    }
}
