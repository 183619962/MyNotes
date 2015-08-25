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
	/** ����װ�ӿؼ���map ���Ǳ�mapЧ�ʸ��� �����int��object ������������� **/
	private SparseArray<View> mViews;
	/** �����Ķ��� **/
	private Context context;
	/** ��ǰ�Ĳ��� **/
	private View convertView;
	/** ��ǰitem��λ�� **/
	private int position;

	public ViewHolder() {

	}

	/** ���췽��������ʵ����viewHolder���� **/
	public ViewHolder(Context context, int layoutID, int position,
			ViewGroup parent) {
		this.mViews = new SparseArray<View>();
		this.context = context;
		convertView = LayoutInflater.from(context).inflate(layoutID, parent,
				false);
		convertView.setTag(this);
	}

	/**
	 * ��ȡһ��viewHolder����
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
			// ��Ȼ����һ����Ҳֵʵ������һ��������ÿ����һ����Ҫ����һ��position
			holder.position = position;
			return holder;
		}
	}

	/**
	 * ����view��ID��ȡ���õ�view
	 * 
	 * @param viewID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewID) {
		// �����ڼ��ϸ���IDȥ��ȡ
		View view = mViews.get(viewID);
		// ��ȡ����ֱ���� û�л�ȡ�����ò���convertViewȥ��ȡ��Ӧ��view�����뼯�ϣ������λ�ȡ
		if (null == view) {
			view = convertView.findViewById(viewID);
			mViews.put(viewID, view);
		}
		return (T) view;
	}

	/** ���ص�ǰ��item���� **/
	public View getConvertView() {
		return convertView;
	}

	public int getPosition() {
		return position;
	}

	/**
	 * ���ð�ť���ı�
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
	 * ͨ���ı�ID ���ð�ť�ı�
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
	 * ���õ�ѡ��ť�Ǳ�ѡ��
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
	 * �ṩֱ�Ӹ��ı����Ƶķ���
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
	 * ����ID�����ı�
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
	 * ����ID����imageview�ı���
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
	 * ������ɫID����imageview����
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
	 * ��������URL��ȡͼƬ
	 * 
	 * @param viewID
	 * @param url
	 * @return
	 */
	public ViewHolder setImageViewByURL(int viewID, String url) {
		ImageView view = (ImageView) getView(viewID);
		// �˴�����ʹ�õ���������������������ͼƬ�ķ���
		return this;
	}

	/**
	 * ����drawable��ֻimageview�ı���
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

	// -------------------���и����ʵ�ֿؼ�������ʵ�ʵĿ����������Ż�--------------------------

}
