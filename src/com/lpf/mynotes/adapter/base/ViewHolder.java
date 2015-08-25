package com.lpf.mynotes.adapter.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class ViewHolder {
	/** 用于装子控件的map 但是比map效率更高 如果是int和object 建议用这个更好 **/
	private SparseArray<View> mViews;
	/** 上下文对象 **/
	private Context context;
	/** 当前的布局 **/
	private View convertView;
	/** 当前item的位置 **/
	private int position;

	public ViewHolder() {

	}

	/** 构造方法，用于实例化viewHolder对象 **/
	public ViewHolder(Context context, int layoutID, int position,
			ViewGroup parent) {
		this.mViews = new SparseArray<View>();
		this.context = context;
		convertView = LayoutInflater.from(context).inflate(layoutID, parent,
				false);
		convertView.setTag(this);
	}

	/**
	 * 获取一个viewHolder对象
	 * 
	 * @param context
	 * @param layoutID
	 * @param convertView
	 * @param position
	 * @param parent
	 * @return
	 */
	public static ViewHolder getHolder(Context context, int layoutID,
			View convertView, int position, ViewGroup parent) {
		if (null == convertView) {
			return new ViewHolder(context, layoutID, position, parent);
		} else {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			// 虽然布局一样，也值实例化了一个，但是每加载一个需要更新一下position
			holder.position = position;
			return holder;
		}
	}

	/**
	 * 根据view的ID获取对用的view
	 * 
	 * @param viewID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewID) {
		// 首先在集合根据ID去获取
		View view = mViews.get(viewID);
		// 获取到就直接用 没有获取到就用布局convertView去获取相应的view并存入集合，避免多次获取
		if (null == view) {
			view = convertView.findViewById(viewID);
			mViews.put(viewID, view);
		}
		return (T) view;
	}

	/** 返回当前的item布局 **/
	public View getConvertView() {
		return convertView;
	}

	public int getPosition() {
		return position;
	}

	/**
	 * 设置按钮的文本
	 * 
	 * @param viewID
	 * @param text
	 * @return
	 */
	public ViewHolder setButtonText(int viewID, String text) {
		Button view = (Button) getView(viewID);
		view.setText(text);
		return this;
	}

	/***
	 * 通过文本ID 设置按钮文本
	 * 
	 * @param viewID
	 * @param textID
	 * @return
	 */
	public ViewHolder setButtonTextByID(int viewID, int textID) {
		Button view = (Button) getView(viewID);
		view.setText(textID);
		return this;
	}

	/**
	 * 设置单选按钮是被选中
	 * 
	 * @param viewID
	 * @param click
	 * @return
	 */
	public ViewHolder setCheckBox(int viewID, boolean click) {
		CheckBox view = (CheckBox) getView(viewID);
		view.setClickable(click);
		return this;
	}

	/**
	 * 提供直接给文本框复制的方法
	 * 
	 * @param viewID
	 * @param text
	 * @return
	 */
	public ViewHolder setTextView(int viewID, String text, Drawable drawable) {
		TextView view = (TextView) getView(viewID);
		view.setText(text);
		if (null != drawable)
			view.setBackgroundDrawable(drawable);
		return this;
	}

	/**
	 * 根据ID设置文本
	 * 
	 * @param viewID
	 * @param textID
	 * @return
	 */
	public ViewHolder setTextViewById(int viewID, int textID, int imgID) {
		TextView view = (TextView) getView(viewID);
		view.setText(textID);
		try {
			if (imgID > 0)
				view.setBackgroundResource(imgID);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return this;
	}

	/**
	 * 
	 * @param viewID
	 * @param textID
	 * @param color
	 * @return
	 */
	public ViewHolder setTextViewByColor(int viewID, int textID, int color) {
		TextView view = (TextView) getView(viewID);
		view.setText(textID);
		try {
			if (color > 0)
				view.setBackgroundColor(color);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return this;
	}

	/**
	 * 根据ID设置imageview的背景
	 * 
	 * @param viewID
	 * @param imgID
	 * @return
	 */
	public ViewHolder setImageView(int viewID, int imgID) {
		ImageView view = (ImageView) getView(viewID);
		view.setBackgroundResource(imgID);
		return this;
	}

	/**
	 * 根据颜色ID设置imageview背景
	 * 
	 * @param viewID
	 * @param color
	 * @return
	 */
	public ViewHolder setImageViewByColor(int viewID, int color) {
		ImageView view = (ImageView) getView(viewID);
		view.setBackgroundColor(color);
		return this;
	}

	/**
	 * 根据网络URL获取图片
	 * 
	 * @param viewID
	 * @param url
	 * @return
	 */
	public ViewHolder setImageViewByURL(int viewID, String url) {
		ImageView view = (ImageView) getView(viewID);
		// 此处允许使用第三方或者其他加载网络图片的方法
		return this;
	}

	/**
	 * 根据drawable这只imageview的背景
	 * 
	 * @param viewID
	 * @param drawable
	 * @return
	 */
	public ViewHolder setImageViewByDrawable(int viewID, Drawable drawable) {
		ImageView view = (ImageView) getView(viewID);
		view.setBackgroundDrawable(drawable);
		return this;
	}

	// -------------------还有更多的实现控件，根据实际的开发过程逐渐优化--------------------------

}
