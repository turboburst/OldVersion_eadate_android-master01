package com.somoplay.eadate.view.main;

import android.app.Activity;
import android.app.Application;

import com.somoplay.eadate.view.Interfaces.WebRequestActivityInterface;

import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/8 0008.
 */
public class CloseAllActivities extends Application implements WebRequestActivityInterface {
    private List<Activity> activityList = new LinkedList<Activity>();
    private static CloseAllActivities instance;

    public CloseAllActivities(){}

    public synchronized static CloseAllActivities getInstance()
    {
        if(instance == null)
            instance = new CloseAllActivities();

        return instance;
    }

    public void addActivity(Activity act)
    {
        activityList.add(act);
    }
    public void exit()
    {
        try
        {
            for(Activity act: activityList)
                if(act != null)
                    act.finish();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.exit(0);
        }
    }
    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        System.gc();
    }

    @Override
    public JSONObject webRequestCallBack(JSONObject jsonObject) {
        return null;
    }
}
