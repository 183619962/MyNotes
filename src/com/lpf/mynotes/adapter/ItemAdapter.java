package com.lpf.mynotes.adapter;

import java.util.List;

import android.content.Context;

import com.lpf.mynotes.adapter.base.CommonAdapter;
import com.lpf.mynotes.adapter.base.ViewHolder;
import com.lpf.mynotes.model.ItemInfo;

public class ItemAdapter extends CommonAdapter<ItemInfo> {

	/**
	 * 
	 * @param context
	 *            �����Ķ���
	 * @param mDatas
	 *            ���ڰ�item��list
	 * @param layoutID
	 *            item�Ĳ����ļ�
	 */
	public ItemAdapter(Context context, List<ItemInfo> mDatas, int layoutID) {
		super(context, mDatas, layoutID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void convert(ViewHolder holder, ItemInfo ItemInfo) {

	}

}
