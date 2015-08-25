package com.lpf.mynotes.base.nfc;

import com.lpf.mynotes.command.APDUCommand;
import com.lpf.mynotes.exception.CardException;
import com.lpf.mynotes.utils.ByteUtil;

import android.nfc.Tag;
import android.nfc.tech.IsoDep;

public class AbstractCardService {
	private CardChannel channel;

	public AbstractCardService(Tag tag) {
		channel = new CardChannel(IsoDep.get(tag));
		try {
			channel.connect();
		} catch (CardException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ѡ���ļ�
	 */
	private void select_mf() throws CardException {
		switch (0) {
		case 0:
			// ѡ��00���ļ�
			channel.transceive(APDUCommand.SELECT_MF);
		case 1:
			// ѡ��00���ļ�
			channel.transceive(APDUCommand.SELECT_MF1);

		default:
			break;
		}
	}

	/**
	 * ��ȡ���
	 * 
	 * @return ���
	 */
	private int getBal() throws CardException {
		int bal = 0;
		// �����
		bal = ByteUtil.hexToInt(channel.transceive(APDUCommand.GET_BALANCE), 0,
				4);
		if (bal > 100000 || bal < -100000)
			bal -= 0x80000000;
		return bal;
	}

	public int getBalance() throws CardException {
		select_mf();
		int bal = getBal();
		return bal;
	}
}
