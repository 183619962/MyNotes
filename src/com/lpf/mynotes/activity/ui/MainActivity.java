package com.lpf.mynotes.activity.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.lpf.mynotes.R;
import com.lpf.mynotes.activity.base.BaseActivity;
import com.lpf.mynotes.adapter.ItemAdapter;
import com.lpf.mynotes.model.ItemInfo;

public class MainActivity extends BaseActivity {
	private ListView notes;
	private List<ItemInfo> noteInfos;

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
		// 初始化主界面的数据
		noteInfos = new ArrayList<ItemInfo>();
		ItemInfo info = new ItemInfo(0, "CommAdapter",
				"通过公共的adapter绑定listview数据,使用pullToRefresh实现上拉、下拉刷新", 0,
				CommadapterActivity.class);
		noteInfos.add(info);

		ItemInfo info1 = new ItemInfo(0, "NFC读卡", "通过手机的NFC功能获取卡片的余额消息", 0,
				NFCActivity.class);
		noteInfos.add(info1);

		ItemInfo info2 = new ItemInfo(0, "Volley使用", "通过volley实现网络交互", 0,
				VolleyActivity.class);
		noteInfos.add(info2);

		ItemInfo info3 = new ItemInfo(0, "ViewPager广告轮播", "通过viewpager实现广告轮播",
				0, MyCycleActivity.class);
		noteInfos.add(info3);

		notes.setAdapter(new ItemAdapter(this, noteInfos,
				R.layout.listview_base_item));

		// 给listview绑定按钮跳转事件
		notes.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long arg3) {
				// TODO Auto-generated method stub
				ItemInfo itemInfo = (ItemInfo) adapter
						.getItemAtPosition(position);
				if (null != itemInfo.clsName && itemInfo.clsName.length() > 0) {
					Intent intent = new Intent();
					intent.setClassName(MainActivity.this, itemInfo.clsName);
					startActivity(intent);
				}
			}
		});

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
