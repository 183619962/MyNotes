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

		// ͨ��tagɾ����Ϣ���������ڻ�ȡ����Ϣ
		MyApplication.getRequestQueue().cancelAll(tag);

		// ʵ����һ��request
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

		// �����request����һ��tag
		request.setTag(tag);

		// ���������ӵ���Ϣ������
		MyApplication.getRequestQueue().add(request);

		// ��ʼ����
		MyApplication.getRequestQueue().start();
		return re;
	}
}
