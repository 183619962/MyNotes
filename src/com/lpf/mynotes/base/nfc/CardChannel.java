package com.lpf.mynotes.base.nfc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.lpf.mynotes.exception.CardException;
import com.lpf.mynotes.utils.ByteUtil;

import android.annotation.SuppressLint;
import android.nfc.tech.IsoDep;
import android.text.TextUtils;
import android.util.Log;

@SuppressLint("NewApi")
public class CardChannel {

	private IsoDep mDep;

	static final String TAG = "CardChannel";

	public CardChannel(IsoDep dep) {
		// TODO Auto-generated constructor stub
		this.mDep = dep;
	}

	public String getUid() {
		return ByteUtil.hexToStr(mDep.getTag().getId());
	}

	public void connect() throws CardException {
		// TODO Auto-generated method stub
		if (mDep != null) {
			try {
				mDep.connect();
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		throw new CardException("-1", "��Ƭ���뿪����֧�ִ˿�");
	}

	public boolean isConnected() {
		// TODO Auto-generated method stub
		return mDep != null && mDep.isConnected();
	}

	public byte[] transceive(byte[] data) throws CardException {
		// TODO Auto-generated method stub
		return transceive(data, true);
	}

	public byte[] transceive(byte[] data, boolean isError) throws CardException {
		// TODO Auto-generated method stub
		Log.d(TAG, "SEND:" + ByteUtil.hexToStr(data));

		byte[] response = null;
		try {
			response = mDep.transceive(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CardException("-1", "��Ƭ���뿪������������");
		}
		Log.d(TAG, "RECV:" + ByteUtil.hexToStr(response));
		if (isError) {
			String code = getResponseCode(response);
			if (!code.equals("9000")) {
				throw new CardException(code, getErrorMsg(code));
			}
		}
		return response;
	}

	public void disconnect() throws CardException {
		// TODO Auto-generated method stub
		if (mDep != null && mDep.isConnected()) {
			try {
				mDep.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CardException("-1", "����ʧ��,������.");
			}
		}
		mDep = null;
	}

	private String getResponseCode(byte[] data) {
		return ByteUtil.hexToStr(data, data.length - 2, 2);
	}

	@SuppressLint("DefaultLocale")
	String getErrorMsg(String code) {
		String msg = ERROR_TABLE.get(code.toUpperCase());
		if (TextUtils.isEmpty(msg)) {
			msg = "�ÿ���δ֧�֣������ڴ�";
		}
		return msg;
	}

	static Map<String, String> ERROR_TABLE = new HashMap<String, String>();

	static {
		ERROR_TABLE.put("61xx", "�ף�֧���������������鷳������Ŭ��Ϊ������У���ȷ��֧���Ƿ�ɹ���");
		ERROR_TABLE.put("6200", "���� ��Ϣδ�ṩ");
		ERROR_TABLE.put("6281", "���� �������ݿ���");
		ERROR_TABLE.put("6282", "���� �ļ�����С��Le");
		ERROR_TABLE.put("6283", "���� ѡ�е��ļ���Ч");
		ERROR_TABLE.put("6284", "���� FCI��ʽ��P2ָ���Ĳ���");
		ERROR_TABLE.put("6300", "���� ����ʧ��");
		ERROR_TABLE.put("63Cx", "���� У��ʧ�ܣ�x���������Դ�����");
		ERROR_TABLE.put("6400", " ״̬��־λû�б�");
		ERROR_TABLE.put("6581", " �ڴ�ʧ��");
		ERROR_TABLE.put("6700", " ���ȴ���");
		ERROR_TABLE.put("6882", " ��֧�ְ�ȫ����");
		ERROR_TABLE.put("6981", " �������ļ��ṹ�����ݣ���ǰ�ļ��������ļ�");
		ERROR_TABLE.put("6982", " ����������AC�������㣬û��У��PIN");
		ERROR_TABLE.put("6983", "���ѱ�����������ϵ����������");
		ERROR_TABLE.put("6984", " �������Ч�����õ�������Ч");
		ERROR_TABLE.put("6985", " ʹ������������");
		ERROR_TABLE.put("6986", " ����������ִ������������������INS�д�");
		ERROR_TABLE.put("6987", " MAC��ʧ");
		ERROR_TABLE.put("6988", " MAC����ȷ");
		ERROR_TABLE.put("698D", "���� ");
		ERROR_TABLE.put("6A80", " �������������ȷ");
		ERROR_TABLE.put("6A81", " ���ܲ�֧�֣�����������Ŀ¼��Ч��Ӧ������");
		ERROR_TABLE.put("6A82", " ���ļ�δ�ҵ�");
		ERROR_TABLE.put("6A83", " �ü�¼δ�ҵ�");
		ERROR_TABLE.put("6A84", " �ļ�Ԥ���ռ䲻��");
		ERROR_TABLE.put("6A86", " P1��P2����ȷ");
		ERROR_TABLE.put("6A88", " ��������δ�ҵ�");
		ERROR_TABLE.put("6B00", " ��������");
		ERROR_TABLE.put("6Cxx", " Le���ȴ���ʵ�ʳ�����xx");
		ERROR_TABLE.put("6E00", " ��֧�ֵ��ࣺCLA�д�");
		ERROR_TABLE.put("6F00", " ������Ч");
		ERROR_TABLE.put("6F01", "�ף�֧���������������鷳������Ŭ��Ϊ������У���ȷ��֧���Ƿ�ɹ���");
		ERROR_TABLE.put("6D00", " ��֧�ֵ�ָ�����");
		ERROR_TABLE.put("9301", " �ʽ���");
		// ERROR_TABLE.put("9302", " MAC��Ч");
		ERROR_TABLE.put("9302", " ��֧�ָÿ�");
		ERROR_TABLE.put("9303", " Ӧ�ñ���������");
		ERROR_TABLE.put("9401", " ���׽���");
		ERROR_TABLE.put("9402", " ���׼������ﵽ���ֵ");
		ERROR_TABLE.put("9403", " ��Կ������֧��");
		ERROR_TABLE.put("9406", " ����MAC������");
		ERROR_TABLE.put("6900", " ���ܴ���");
		ERROR_TABLE.put("6901", "�ף�֧���������������鷳������Ŭ��Ϊ������У���ȷ��֧���Ƿ�ɹ���");// ������ܣ���Ч״̬��
		ERROR_TABLE.put("61xx", "���� �跢GET RESPONSE����");
		ERROR_TABLE.put("6600", " ����ͨѶ��ʱ");
		ERROR_TABLE.put("6601", " �����ַ���ż��");
		ERROR_TABLE.put("6602", " У��Ͳ���");
		ERROR_TABLE.put("6603", "���� ��ǰDF�ļ���FCI");
		ERROR_TABLE.put("6604", "���� ��ǰDF����SF��KF");
	}

}
