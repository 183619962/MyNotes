package com.lpf.mynotes.base;

import java.util.Map;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lpf.mynotes.exception.NetworkException;

public class BaseRequest {
	private static StringRequest request;

	private static Context mContext;

	static String re = null;

	// ͨ��get�����ȡ��������
	public static void requestGet(Context context, String url, String tag,
			VolleyInterface vi) {
		// ͨ��tagɾ����Ϣ���������ڻ�ȡ����Ϣ
		MyApplication.getRequestQueue().cancelAll(tag);

		// ʵ����һ��request
		request = new StringRequest(Method.GET, url, vi.loadingListener(),
				vi.loadingErrorListener());

		// �����request����һ��tag
		request.setTag(tag);

		// ���������ӵ���Ϣ������
		MyApplication.getRequestQueue().add(request);

		// ��ʼ����
		MyApplication.getRequestQueue().start();
	}

	public static String requestGet1(Context context, String url, String tag)
			throws NetworkException {
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
							new NetworkException(e.getMessage());
						}
					}
				});

		// �����request����һ��tag
		request.setTag(tag);

		// ���������ӵ���Ϣ������
		MyApplication.getRequestQueue().add(request);

		// ��ʼ����
		MyApplication.getRequestQueue().start();

		return null;
	}

	// ͨ��post�����ȡ��������
	public static void requestPoset(Context context, String url,
			final Map<String, String> vals, String tag, VolleyInterface vi) {
		// ͨ��tagɾ����Ϣ���������ڻ�ȡ����Ϣ
		MyApplication.getRequestQueue().cancelAll(tag);

		// ʵ����һ��request
		request = new StringRequest(Method.POST, url, vi.loadingListener(),
				vi.loadingErrorListener()) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				// ��������Ĳ���
				return vals;
			}
		};

		// �����request����һ��tag
		request.setTag(tag);

		// ���������ӵ���Ϣ������
		MyApplication.getRequestQueue().add(request);

		// ��ʼ����
		MyApplication.getRequestQueue().start();
	}
}
