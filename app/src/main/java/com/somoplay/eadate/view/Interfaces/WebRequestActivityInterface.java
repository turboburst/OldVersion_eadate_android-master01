package com.somoplay.eadate.view.Interfaces;

import android.annotation.TargetApi;
import android.os.Build;

import org.json.JSONObject;

/**
 * Created by turbo on 2016/7/7.
 */
public interface WebRequestActivityInterface {
    public JSONObject webRequestCallBack(JSONObject jsonObject);
}
