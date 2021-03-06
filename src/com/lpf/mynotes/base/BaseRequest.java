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

	// 通过get请求获取网络数据
	public static void requestGet(Context context, String url, String tag,
			VolleyInterface vi) {
		// 通过tag删除消息队列中正在获取的消息
		MyApplication.getRequestQueue().cancelAll(tag);

		// 实例化一个request
		request = new StringRequest(Method.GET, url, vi.loadingListener(),
				vi.loadingErrorListener());

		// 将这个request设置一个tag
		request.setTag(tag);

		// 将这个请求加到消息队列中
		MyApplication.getRequestQueue().add(request);

		// 开始请求
		MyApplication.getRequestQueue().start();
	}

	public static String requestGet1(Context context, String url, String tag)
			throws NetworkException {
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
							new NetworkException(e.getMessage());
						}
					}
				});

		// 将这个request设置一个tag
		request.setTag(tag);

		// 将这个请求加到消息队列中
		MyApplication.getRequestQueue().add(request);

		// 开始请求
		MyApplication.getRequestQueue().start();

		return null;
	}

	// 通过post请求获取网络数据
	public static void requestPoset(Context context, String url,
			final Map<String, String> vals, String tag, VolleyInterface vi) {
		// 通过tag删除消息队列中正在获取的消息
		MyApplication.getRequestQueue().cancelAll(tag);

		// 实例化一个request
		request = new StringRequest(Method.POST, url, vi.loadingListener(),
				vi.loadingErrorListener()) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				// 设置请求的参数
				return vals;
			}
		};

		// 将这个request设置一个tag
		request.setTag(tag);

		// 将这个请求加到消息队列中
		MyApplication.getRequestQueue().add(request);

		// 开始请求
		MyApplication.getRequestQueue().start();
	}
}
