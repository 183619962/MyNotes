package com.lpf.mynotes.adapter.base;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CommonAdapter<T> extends BaseAdapter {
	private Context context;
	private List<T> mDatas;
	private int layoutID;

	/**
	 * 
	 * @param context
	 *            �����Ķ���
	 * @param mDatas
	 *            ���ڰ�item��list
	 * @param layoutID
	 *            item�Ĳ����ļ�
	 */
	public CommonAdapter(Context context, List<T> mDatas, int layoutID) {
		this.context = context;
		this.mDatas = mDatas;
		this.layoutID = layoutID;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDatas.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = new ViewHolder(context, layoutID, position, parent);
		convert(holder, getItem(position));
		return holder.getConvertView();
	}

	/**
	 * ��Ҫʵ�ֵĳ����� ����holder��ȡ�ؼ�����ֵ
	 * 
	 * @param holder
	 * @param t
	 */
	public abstract void convert(ViewHolder holder, T t);
}
