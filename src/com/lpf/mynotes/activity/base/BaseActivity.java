package com.lpf.mynotes.activity.base;

import android.os.Bundle;
import android.widget.Toast;

import com.lpf.mynotes.utils.NetUtils;

public abstract class BaseActivity extends NfcActivity {

	/**
	 * 子类继承并告知相关的布局文件
	 * 
	 * @return 布局的id
	 */
	abstract public int getLayout();

	/**
	 * 继承实现得到得到相关的view
	 */
	abstract public void findView();

	/**
	 * 设置基础数据
	 */
	abstract public void setData();

	/**
	 * 当没有网络时，该界面是否需要提示
	 * 
	 * @return true:提示 false:不提示
	 */
	abstract public boolean unConnectNetShowToast();

	/**
	 * 是否是连接的wifi
	 */
	private boolean isWifi = false;

	/**
	 * 是否连接网络
	 */
	private boolean isConnectNet = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(getLayout());
		findView();

		baseCheck();

		setData();
	}

	/**
	 * 用于做基础的校验 如判断是否有网络等
	 */
	private void baseCheck() {
		// TODO Auto-generated method stub
		isConnectNet = NetUtils.isConnected(this);
		if (!isConnectNet && unConnectNetShowToast()) {
			Toast.makeText(this, "未连接网络", Toast.LENGTH_LONG).show();
		}

		isWifi = NetUtils.isWifi(this);
	}

}
