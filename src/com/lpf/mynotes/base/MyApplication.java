package com.lpf.mynotes.base;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;

public class MyApplication extends Application {
	private static RequestQueue requestQueue;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		requestQueue = Volley.newRequestQueue(getApplicationContext());
	}

	public static RequestQueue getRequestQueue() {
		return requestQueue;
	}
}
