package com.lpf.mynotes.activity;

import android.widget.ListView;

import com.lpf.mynotes.R;
import com.lpf.mynotes.activity.base.BaseActivity;

public class MainActivity extends BaseActivity {
	private ListView notes;

	@Override
	public int getLayout() {
		// TODO Auto-generated method stub
		return R.layout.activity_main;
	}

	@Override
	public void findView() {
		// TODO Auto-generated method stub
		notes = (ListView) findViewById(R.id.notes_listview);
	}

	@Override
	public void setData() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean unConnectNetShowToast() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected boolean showOpenNFCLog() {
		// TODO Auto-generated method stub
		return false;
	}

}
