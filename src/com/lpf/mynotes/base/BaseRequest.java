package com.lpf.mynotes.base;

import java.util.Map;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.StringRequest;

public class BaseRequest {
	private static StringRequest request;

	private static Context mContext;

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
