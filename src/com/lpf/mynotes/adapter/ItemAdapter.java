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
	 *            上下文对象
	 * @param mDatas
	 *            用于绑定item的list
	 * @param layoutID
	 *            item的布局文件
	 */
	public ItemAdapter(Context context, List<ItemInfo> mDatas, int layoutID) {
		super(context, mDatas, layoutID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void convert(ViewHolder holder, ItemInfo ItemInfo) {

	}

}
