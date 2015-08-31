package com.lpf.mynotes.activity.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.lpf.mynotes.R;
import com.lpf.mynotes.activity.base.BaseActivity;
import com.lpf.mynotes.adapter.ItemAdapter;
import com.lpf.mynotes.model.ItemInfo;
import com.lpf.mynotes.utils.DateUtil;
import com.lpf.mynotes.utils.ToastUtils;

public class CommadapterActivity extends BaseActivity {

	PullToRefreshListView lv;

	List<ItemInfo> itemInfos;

	// ��¼��ǰ�����κ�
	private int batch = 0;

	private ItemAdapter itemAdapter;

	private ILoadingLayout startLabels;

	@Override
	public int getLayout() {
		// TODO Auto-generated method stub
		return R.layout.activity_commadapter;
	}

	@Override
	public void findView() {
		// TODO Auto-generated method stub
		lv = (PullToRefreshListView) findViewById(R.id.commadapter_listview);
	}

	private void initData() {
		if (null == itemInfos)
			return;

		itemInfos.clear();
		for (int i = 0; i < 10; i++) {
			ItemInfo info = new ItemInfo(R.drawable.skyblue_logo_email, "item"
					+ i, null, R.drawable.icon_my_arror, "");
			itemInfos.add(info);
		}
	}

	@Override
	public void setData() {
		// TODO Auto-generated method stub
		itemInfos = new ArrayList<ItemInfo>();
		initData();

		itemAdapter = new ItemAdapter(this, itemInfos,
				R.layout.listview_base_item);
		// ��������
		lv.setAdapter(itemAdapter);

		// ���õ���¼�
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

		// ��listview��������ˢ�� �������ظ����ʱ��
		lv.setOnRefreshListener(new OnRefreshListener2<ListView>() {
			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				Log.e("TAG", "onPullDownToRefresh");

				batch = 0;
				// ����д����ˢ�µ�����
				new GetDataTask().execute();
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				Log.e("TAG", "onPullUpToRefresh");

				batch++;
				// ����д�������ظ��������
				new GetDataTask().execute();
			}
		});

		initIndicator();
	}

	// ���»�������ʾ�ı�����
	private void initIndicator() {
		startLabels = lv.getLoadingLayoutProxy(true, false);
		startLabels.setPullLabel("��ɾ�������...");// ������ʱ����ʾ����ʾ
		startLabels.setRefreshingLabel("���ϣ�����ˢ��...");// ˢ��ʱ
		startLabels.setReleaseLabel("��ҷţ��Ҿ͸�ˢ��...");// �����ﵽһ������ʱ����ʾ����ʾ
		startLabels.setLastUpdatedLabel(DateUtil.getNow(DateUtil.FORMAT_LONG));

		ILoadingLayout endLabels = lv.getLoadingLayoutProxy(false, true);
		endLabels.setPullLabel("��ɾ�������...");// ������ʱ����ʾ����ʾ
		endLabels.setRefreshingLabel("���ϣ����ڼ��ظ���...");// ˢ��ʱ
		endLabels.setReleaseLabel("��ҷţ��Ҿ͸Ҽ��ظ���...");// �����ﵽһ������ʱ����ʾ����ʾ
	}

	private class GetDataTask extends AsyncTask<Void, Void, List<ItemInfo>> {

		@Override
		protected List<ItemInfo> doInBackground(Void... params) {
			// ģ�������ʱ
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			List<ItemInfo> items = new ArrayList<ItemInfo>();
			if (batch > 0) {
				for (int i = 0; i < 10; i++) {
					ItemInfo info = new ItemInfo(R.drawable.skyblue_logo_email,
							"more-->" + batch + i, null,
							R.drawable.icon_my_arror, "");
					items.add(info);
				}
			}
			return items;
		}

		@Override
		protected void onPostExecute(List<ItemInfo> result) {
			if (batch == 0) {
				// ���õ�ǰˢ��ʱ��
				startLabels.setLastUpdatedLabel(DateUtil
						.getNow(DateUtil.FORMAT_LONG));
				initData();
			} else {
				if (null != result) {
					itemInfos.addAll(result);
				}
			}
			itemAdapter.notifyDataSetChanged();
			lv.onRefreshComplete();
		}
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
