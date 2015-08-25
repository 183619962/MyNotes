package com.lpf.mynotes.model;

import android.content.Context;

/**
 * 
 * @author lpf
 * 
 *         [img] **##** [img]
 * 
 *         [img] **##** [img]
 * 
 *         [img] **##** [img]
 * 
 *         基础布局以左边图片 中间文本 右边图片的格式
 * 
 */
public class ItemInfo {

	// 左边的图标
	public int leftIcon;

	// 文本1
	public String text1;

	// 文本2
	public String text2;

	// 左边的图标
	public int rightIcon;

	// 跳转的class的名称
	public String clsName;

	public ItemInfo(Context context, int leftIcon, int text1, int text2,
			int rightIcon, String clsName) {
		this.leftIcon = leftIcon;
		if (text1 > 0)
			this.text1 = context.getString(text1);
		if (text2 > 0)
			this.text2 = context.getString(text2);
		this.rightIcon = rightIcon;
		this.clsName = clsName;
	}

	public ItemInfo(Context context, int leftIcon, int text1, int text2,
			int rightIcon, Class<?> cls) {
		this.leftIcon = leftIcon;
		if (text1 > 0)
			this.text1 = context.getString(text1);
		if (text2 > 0)
			this.text2 = context.getString(text2);
		this.rightIcon = rightIcon;
		this.clsName = cls.getName();
	}

	public ItemInfo(int leftIcon, String text1, String text2, int rightIcon,
			String clsName) {
		this.leftIcon = leftIcon;
		this.text1 = text1;
		this.text2 = text2;
		this.rightIcon = rightIcon;
		this.clsName = clsName;
	}

	public ItemInfo(int leftIcon, String text1, String text2, int rightIcon,
			Class<?> cls) {
		this.leftIcon = leftIcon;
		this.text1 = text1;
		this.text2 = text2;
		this.rightIcon = rightIcon;
		this.clsName = cls.getName();
	}

	@Override
	public String toString() {
		return "ItemInfo [leftIcon=" + leftIcon + ", text1=" + text1
				+ ", text2=" + text2 + ", rightIcon=" + rightIcon
				+ ", clsName=" + clsName + "]";
	}
}
