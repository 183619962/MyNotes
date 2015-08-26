package com.lpf.mynotes.activity.ui;

import java.util.HashMap;
import java.util.Map;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.lpf.mynotes.R;
import com.lpf.mynotes.activity.base.BaseActivity;
import com.lpf.mynotes.base.BaseRequest;
import com.lpf.mynotes.base.VolleyInterface;
import com.lpf.mynotes.utils.ToastUtils;

public class VolleyActivity extends BaseActivity implements OnClickListener {
	public TextView txt;
	public Button get, post;

	@Override
	public int getLayout() {
		// TODO Auto-generated method stub
		return R.layout.activity_volleyactivity;
	}

	@Override
	public void findView() {
		// TODO Auto-generated method stub
		txt = (TextView) findViewById(R.id.guishudi);
		get = (Button) findViewById(R.id.get);
		post = (Button) findViewById(R.id.post);
		get.setOnClickListener(this);
		post.setOnClickListener(this);
	}

	@Override
	public void setData() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean unConnectNetShowToast() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean showOpenNFCLog() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.get:
			String url = "http://chong.qq.com/tws/extinfo/GetMobileProductInfo?mobile=15014050224&amount=10000&callname=getPhoneNumInfoExtCallback&_=1439982011907";
			BaseRequest.requestGet(this, url, "get", new VolleyInterface(this) {

				@Override
				public void mySuccess(String response) {
					// TODO Auto-generated method stub
					ToastUtils.showShort(VolleyActivity.this, response);
					txt.setText(response);
				}

				@Override
				public void myError(VolleyError error) {
					// TODO Auto-generated method stub

				}
			});
			break;
		case R.id.post:
			String u = "http://chong.qq.com/tws/extinfo/GetMobileProductInfo?";
			Map<String, String> val = new HashMap<String, String>();
			val.put("mobile", "15014050224");
			val.put("amount", "10000");
			val.put("callname", "getPhoneNumInfoExtCallback");
			val.put("_", "1439982011907");
			BaseRequest.requestPoset(this, u, val, "post", new VolleyInterface(
					this) {

				@Override
				public void mySuccess(String response) {
					// TODO Auto-generated method stub
					ToastUtils.showShort(VolleyActivity.this, response);
					txt.setText(response);
				}

				@Override
				public void myError(VolleyError error) {
					// TODO Auto-generated method stub

				}
			});
			break;

		default:
			break;
		}
	}

}
