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
 *         �������������ͼƬ �м��ı� �ұ�ͼƬ�ĸ�ʽ
 * 
 */
public class ItemInfo {

	// ��ߵ�ͼ��
	public int leftIcon;

	// �ı�1
	public String text1;

	// �ı�2
	public String text2;

	// ��ߵ�ͼ��
	public int rightIcon;

	// ��ת��class������
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
