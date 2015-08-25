package com.lpf.mynotes.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpf.mynotes.R;
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
		if (ItemInfo.leftIcon > 0) {
			ImageView view = ((ImageView) holder.getView(R.id.left_icon));
			view.setBackgroundResource(ItemInfo.leftIcon);
			view.setVisibility(View.VISIBLE);
		}
		if (null != ItemInfo.text1) {
			TextView view = ((TextView) holder.getView(R.id.text1));
			view.setText(ItemInfo.text1);
			view.setVisibility(View.VISIBLE);
		}

		if (null != ItemInfo.text2) {
			TextView view = ((TextView) holder.getView(R.id.text2));
			view.setText(ItemInfo.text2);
			view.setVisibility(View.VISIBLE);
		}

		if (ItemInfo.rightIcon > 0) {
			ImageView view = ((ImageView) holder.getView(R.id.right_icon));
			view.setBackgroundResource(ItemInfo.rightIcon);
			view.setVisibility(View.VISIBLE);
		}
	}

}
