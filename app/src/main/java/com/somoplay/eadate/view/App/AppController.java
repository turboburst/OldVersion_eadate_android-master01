package com.somoplay.eadate.view.App;

import android.app.Application;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.somoplay.eadate.view.WebRequest.TokenRequest;
import com.somoplay.eadate.view.utils.LruBitmapCache;

/**
 * Created by turbo on 2016/6/21.
 */
public class AppController extends Application
{
    private static RequestQueue requestQueue;
    private static TokenRequest tokenRequest;
    private static AppController mInstance;
    public static final String TAG = AppController.class.getSimpleName();
    private static SharedPreferences sharedPref;
    private static ImageLoader mImageLoader;
    private static String token_url = Constances.Token_url;
    private static String access_token;

    @Override
    public void onCreate()
    {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public String getAccess_token()
    {
        return access_token;
    }

    public void setAccess_token(String access_token)
    {
        this.access_token = access_token;
    }

    public static AppController getInstance()
    {
        if(mInstance == null)
        {
            synchronized (AppController.class)
            {
                if(mInstance == null)
                {
                    return new AppController();
                }
            }
        }
        return null;
    }

    //Volley methods
    public static RequestQueue getRequestQueue() {

        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.requestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (requestQueue != null)
        {
            requestQueue.cancelAll(tag);
        }
    }

}
