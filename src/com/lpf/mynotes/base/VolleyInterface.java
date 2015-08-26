package com.lpf.mynotes.base;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import android.content.Context;

public abstract class VolleyInterface {
	public Context context;

	public static Listener<String> mListener;

	public static ErrorListener mErrorListener;

	public VolleyInterface(Context context) {
		this.context = context;
//		VolleyInterface.mListener = listener;
//		VolleyInterface.mErrorListener = errorListener;
	}

	public abstract void mySuccess(String response);

	public abstract void myError(VolleyError error);

	public Listener<String> loadingListener() {
		mListener = new Listener<String>() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				mySuccess(response);
			}
		};
		return mListener;
	}

	public ErrorListener loadingErrorListener() {
		mErrorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				myError(error);
			}
		};

		return mErrorListener;
	}

}
