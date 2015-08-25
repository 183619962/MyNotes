package com.lpf.mynotes.activity.base;

import android.os.Bundle;
import android.widget.Toast;

import com.lpf.mynotes.utils.NetUtils;

public abstract class BaseActivity extends NfcActivity {

	/**
	 * ����̳в���֪��صĲ����ļ�
	 * 
	 * @return ���ֵ�id
	 */
	abstract public int getLayout();

	/**
	 * �̳�ʵ�ֵõ��õ���ص�view
	 */
	abstract public void findView();

	/**
	 * ���û�������
	 */
	abstract public void setData();

	/**
	 * ��û������ʱ���ý����Ƿ���Ҫ��ʾ
	 * 
	 * @return true:��ʾ false:����ʾ
	 */
	abstract public boolean unConnectNetShowToast();

	/**
	 * �Ƿ������ӵ�wifi
	 */
	private boolean isWifi = false;

	/**
	 * �Ƿ���������
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
	 * ������������У�� ���ж��Ƿ��������
	 */
	private void baseCheck() {
		// TODO Auto-generated method stub
		isConnectNet = NetUtils.isConnected(this);
		if (!isConnectNet && unConnectNetShowToast()) {
			Toast.makeText(this, "δ��������", Toast.LENGTH_LONG).show();
		}

		isWifi = NetUtils.isWifi(this);
	}

}
