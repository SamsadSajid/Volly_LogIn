package com.example.shamsad.volly_login;

import android.content.Context;
import android.support.v4.app.NavUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by shamsad on 1/3/17.
 */

public class MySigleton {
    private static MySigleton mInstance;
    private RequestQueue requestQueue;
    private static Context context;

    private MySigleton(Context context){
        this.context=context;
        requestQueue=getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue== null){
            requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MySigleton getmInstance(Context context){
        if(mInstance==null){
            mInstance=new MySigleton(context);
        }
        return mInstance;
    }

    public <T> void addToRequestQueue(Request<T>request){
        requestQueue.add(request);
    }
}
