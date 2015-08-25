package com.lpf.mynotes.command;

public class APDUCommand {
	/**
	 * �鿴00�ļ�
	 */
	public static final byte[] SELECT_MF = { 0x00, (byte) 0xA4, 0x00, 0x00,
			0x02, 0x3F, 0x00 };

	/**
	 * �鿴01�ļ�
	 */
	public static final byte[] SELECT_MF1 = { 0x00, (byte) 0xA4, 0x00, 0x00,
			0x02, 0x3F, 0x01 };

	/** ��ȡ����ǰ��� **/
	public static final byte[] GET_BALANCE = { (byte) 0x80, (byte) 0x5C, 0x00,
			0x02, 0x04 };
}
