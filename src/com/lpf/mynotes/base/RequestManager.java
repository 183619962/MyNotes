package com.lpf.mynotes.base;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.StringRequest;
import com.lpf.mynotes.activity.ui.VolleyActivity;
import com.lpf.mynotes.exception.NetworkException;
import com.lpf.mynotes.utils.ToastUtils;

import android.content.Context;

public class RequestManager {
	private Context mContext;

	private String url;

	private String tag;

	private String re = null;

	private static StringRequest request;

	public RequestManager(Context context, String url, String tag) {
		this.mContext = context;
		this.url = url;
		this.tag = tag;
	}

	public String getValueByGet() throws NetworkException {

		// 通过tag删除消息队列中正在获取的消息
		MyApplication.getRequestQueue().cancelAll(tag);

		// 实例化一个request
		request = new StringRequest(Method.GET, url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
						re = response;
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						try {
							throw new NetworkException(error.getMessage());
						} catch (NetworkException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});

		// 将这个request设置一个tag
		request.setTag(tag);

		// 将这个请求加到消息队列中
		MyApplication.getRequestQueue().add(request);

		// 开始请求
		MyApplication.getRequestQueue().start();
		return re;
	}
}
