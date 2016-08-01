package com.somoplay.eadate.view.NewActivities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.Interfaces.SaveDataToBundleInterface;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class FrgTimePicker extends Fragment implements SaveDataToBundleInterface {

    private Bundle bundle;
    private ViewPager viewPager;
    private TextView stepTextView;
    //private Button preBtn;
   // private Button nextBtn;
    private TextView startTv;
    private TextView endTv, detailTv;
    private Calendar calendar;

    private String startdateStr, starttimeStr, enddateStr, endtimeStr;

    /*private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;*/

    private static final String START = "start";
    private static final String END = "end";

    int start_year, start_month, start_day, start_hour, start_min;
    int end_year, end_month, end_day, end_hour, end_min;
    Calendar start_cal, end_cal;

    public FrgTimePicker()
    {
        // Required empty public constructor
    }

    public FrgTimePicker(ViewPager viewPager, TextView stepTextView)
    {
        this.viewPager = viewPager;
        this.stepTextView = stepTextView;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bundle = ((CreateNewActivityActivity)this.getActivity()).getBundle();
        View timeView = inflater.inflate(R.layout.newactivity_frg_time_picker, container, false);

      //  preBtn = (Button) timeView.findViewById(R.id.newactivity_previousBtn);
       // nextBtn = (Button) timeView.findViewById(R.id.newactivity_nextBtn);

        startTv = (TextView) timeView.findViewById(R.id.newactivity_start);
        endTv = (TextView) timeView.findViewById(R.id.newactivity_end);
       //

        /*sharedpreferences = getContext().getSharedPreferences("PREF_newActivity", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();*/

        start_cal = Calendar.getInstance();
        end_cal = Calendar.getInstance();

        long curTime = System.currentTimeMillis();
       // Toast.makeText(getContext()," Now time === " + curTime, Toast.LENGTH_SHORT).show();

        doClick();


        return timeView;
    }

    private void doClick()
    {
        startTv.setOnClickListener(new View.OnClickListener()
        {
            @Override
           public void onClick(View view)
            {
                startTv.setText("");
                showStartDialog();
                // showStartTimeDialog();
            }
        });

        endTv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                endTv.setText("");
                showEndDialog();
                // showEndTimeDialog();
            }
        });

       /* preBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                viewPager.setCurrentItem(1);
                stepTextView.setText("Step 2");
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                viewPager.setCurrentItem(3);
                stepTextView.setText("Step 4");
            }
        });*/

    }

    private void showStartDialog()
    {
        calendar=Calendar.getInstance();
        TimePickerDialog time_dialog=new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener()
        {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // tv_time.setText("Time set at "+hourOfDay+":"+minute);
                start_hour = hourOfDay;
                start_min = minute;

                start_cal.set(start_year,start_month,start_day,start_hour,start_min);

                starttimeStr = " " + hourOfDay+":"+minute;
                if(startdateStr != null)
                {
                    startTv.setText(startdateStr + starttimeStr);
                    bundle.putString("START", startTv.getText().toString());
                }
                else
                    startTv.setText(starttimeStr);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        time_dialog.show();

        DatePickerDialog date_dialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                String weekdayStr;
                //weekdayStr = DateUtils.formatDateTime(getActivity(),
                //        System.currentTimeMillis(), DateUtils.FORMAT_SHOW_WEEKDAY);
                calendar.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
                weekdayStr = String.format("%tA", calendar);

                start_year = year;
                start_month = monthOfYear;
                start_day = dayOfMonth;

                startdateStr = " " + weekdayStr + " ; " + year+"-"+(monthOfYear+1)+"-"+dayOfMonth + " ; ";
                if(starttimeStr != null)
                    startTv.setText(startdateStr + starttimeStr);
                else
                    startTv.setText(startdateStr);

            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH) );
        date_dialog.show();
    }

    private void showEndDialog()
    {
        calendar=Calendar.getInstance();
        TimePickerDialog time_dialog=new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // tv_time.setText("Time set at "+hourOfDay+":"+minute);
                end_hour = hourOfDay;
                end_min = minute;
                endtimeStr = " "+hourOfDay+":"+minute;

                end_cal.set(end_year,end_month,end_day,end_hour,end_min);
                if(end_cal.getTimeInMillis() < System.currentTimeMillis() ){
                    Toast.makeText(getContext()," End time should be After Current time.", Toast.LENGTH_SHORT).show();
                    endTv.setText("");
                }
                else {
                    if (enddateStr != null)
                     {
                         endTv.setText(enddateStr + endtimeStr);
                         bundle.putString("END", endTv.getText().toString());
                     }
                      else
                        endTv.setText(endtimeStr);
                }
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        time_dialog.show();

        DatePickerDialog date_dialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                String weekdayStr;

                //weekdayStr = DateUtils.formatDateTime(getActivity(),
                //        System.currentTimeMillis(), DateUtils.FORMAT_SHOW_WEEKDAY);
                calendar.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
                weekdayStr = String.format("%tA", calendar);

                end_year = year;
                end_month = monthOfYear;
                end_day = dayOfMonth;

                enddateStr = " "+ weekdayStr + " ; " +year+"-"+(monthOfYear+1)+"-"+dayOfMonth + " ; ";

                if(endtimeStr != null)
                   endTv.setText(enddateStr + endtimeStr);
                else
                    endTv.setText(enddateStr);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        date_dialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();

        startTv.setText(bundle.getString("START"));
        endTv.setText(bundle.getString("END"));
//        detailTv.setText(sharedpreferences.getString("DETAIL", ""));
    }

    @Override
    public void SaveDataToBundle() {
        bundle.putString("START",startTv.getText().toString());
        bundle.putString("END", endTv.getText().toString());


    }
}
