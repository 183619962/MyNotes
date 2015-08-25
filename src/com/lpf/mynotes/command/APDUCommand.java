package com.lpf.mynotes.command;

public class APDUCommand {
	/**
	 * 查看00文件
	 */
	public static final byte[] SELECT_MF = { 0x00, (byte) 0xA4, 0x00, 0x00,
			0x02, 0x3F, 0x00 };

	/**
	 * 查看01文件
	 */
	public static final byte[] SELECT_MF1 = { 0x00, (byte) 0xA4, 0x00, 0x00,
			0x02, 0x3F, 0x01 };

	/** 获取交易前余额 **/
	public static final byte[] GET_BALANCE = { (byte) 0x80, (byte) 0x5C, 0x00,
			0x02, 0x04 };
}
