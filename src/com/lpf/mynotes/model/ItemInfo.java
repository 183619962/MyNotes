package com.lpf.mynotes.model;

public class ItemInfo {
	
	public int icon;
	public int text;
	public String clsName;
	
	public ItemInfo(int icon, int text, String clsName) {
		this.icon = icon;
		this.text = text;
		this.clsName = clsName;
	}
	
	public ItemInfo(int icon, int text, Class<?> cls) {
		this.icon = icon;
		this.text = text;
		this.clsName = cls.getName();
	}
	
}
