package com.lpf.mynotes.activity;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.lpf.mynotes.R;
import com.lpf.mynotes.activity.base.BaseActivity;
import com.lpf.mynotes.adapter.ItemAdapter;
import com.lpf.mynotes.model.ItemInfo;
import com.lpf.mynotes.utils.ToastUtils;

public class CommadapterActivity extends BaseActivity {

	ListView lv;

	List<ItemInfo> itemInfos;

	@Override
	public int getLayout() {
		// TODO Auto-generated method stub
		return R.layout.activity_commadapter;
	}

	@Override
	public void findView() {
		// TODO Auto-generated method stub
		lv = (ListView) findViewById(R.id.commadapter_listview);
	}

	@Override
	public void setData() {
		// TODO Auto-generated method stub
		itemInfos = new ArrayList<ItemInfo>();
		for (int i = 0; i < 100; i++) {
			ItemInfo info = new ItemInfo(R.drawable.skyblue_logo_email, "item"
					+ i, null, R.drawable.icon_my_arror, "");
			itemInfos.add(info);
		}

		// 绑定适配器
		lv.setAdapter(new ItemAdapter(this, itemInfos,
				R.layout.listview_base_item));

		// 设置点击事件
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long arg3) {
				// TODO Auto-generated method stub
				ItemInfo itemInfo = (ItemInfo) adapter
						.getItemAtPosition(position);
				ToastUtils.showShort(CommadapterActivity.this,
						itemInfo.toString());
			}
		});

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

}
