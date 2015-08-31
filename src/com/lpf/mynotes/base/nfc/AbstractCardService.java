package com.lpf.mynotes.base.nfc;

import org.json.JSONException;
import org.json.JSONObject;

import android.nfc.Tag;
import android.nfc.tech.IsoDep;

import com.lpf.mynotes.command.APDUCommand;
import com.lpf.mynotes.exception.CardException;
import com.lpf.mynotes.net.Future1;
import com.lpf.mynotes.net.ThreeDES;
import com.lpf.mynotes.utils.ByteUtil;

public class AbstractCardService {
	private CardChannel channel;

	final byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10, 0x40,
			0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD, 0x55, 0x66,
			0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36, (byte) 0xE2 }; // 24字节的密钥

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
	 * 选择文件
	 */
	private void select_mf() throws CardException {
		switch (0) {
		case 0:
			// 选择00号文件
			channel.transceive(APDUCommand.SELECT_MF);
		case 1:
			// 选择00号文件
			channel.transceive(APDUCommand.SELECT_MF1);

		default:
			break;
		}
	}

	/**
	 * 读取余额
	 * 
	 * @return 余额
	 */
	private int getBal() throws CardException {
		int bal = 0;
		// 读余额
		bal = ByteUtil.hexToInt(channel.transceive(APDUCommand.GET_BALANCE), 0,
				4);
		if (bal > 100000 || bal < -100000)
			bal -= 0x80000000;
		return bal;
	}

	// 测试卡充值流程
	private void topup() throws CardException {
		channel.transceive(ByteUtil.hexStr2Byte("00A4040009A00000000386980701"));

		channel.transceive(ByteUtil.hexStr2Byte("00A40000021001"));

		channel.transceive(ByteUtil.hexStr2Byte("0020000003123456"));//

		byte[] data = channel.transceive(ByteUtil
				.hexStr2Byte("805000020B0100000100010101010101"));

		// 交易计数器
		String tradeNo = ByteUtil.hexToStr(data, 4, 2);

		// 取随机数 从第八个字节到第12个
		String PRN = ByteUtil.hexToStr(data, 8, 4);

		String MAC_KEY = PRN + tradeNo + "8000";
		String MAC_DATA = "000001000201010101010120150709105900";
		String DESKEY = "00000000000000000000000000000000";
		String jsonString = "{\"REQ_CODE\":2222,\"ENCRY_FLAG\":1,\"APP_MODE\":\"U\",\"PROTOCOL_VER\":\"0.1\",\"IS_TEST\":\"0\",\"UUID\":\"d38cd932-682c-45b9-8f14-e45a8f28dc23\",\"MAC_NO\":\"F0:25:B7:EC:64:1B\",\"DEV_VER\":\"GT-I9507V5.0.1\",\"USERNAME\":\"13288888888\",\"DATA\":{\"MAC_KEY\":\""
				+ MAC_KEY
				+ "\",\"MAC_DATA\":\""
				+ MAC_DATA
				+ "\",\"DESKEY\":\"" + DESKEY + "\"}}";

		Future1 ft = new Future1(ThreeDES.encryptMode(keyBytes,
				jsonString.getBytes()));
		String obj = ft.get(1000l);

		String mac1 = null;
		try {
			JSONObject object = new JSONObject(obj);
			JSONObject dataObject = object.getJSONObject("DATA");
			mac1 = dataObject.getString("MAC1");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Des des = new Des();
		// String ccc = des.getHintKey(PRN + tradeNo + "8000",
		// "D3D6E8836832FDD4706D0671BB8BD28B");
		// String macTest = des.PBOC_DES_MAC(ccc, "0000000000000000",
		// "000001000201010101010120150709115901", 0).substring(0, 8);

		data = channel.transceive(ByteUtil
				.hexStr2Byte("805200000B20150709115901" + mac1));//
	}

	// 台州绍兴充值基本流程
	private void topup2() throws CardException {
		// 00文件
		channel.transceive(ByteUtil.hexStr2Byte("00a40000023f00"));

		// 01文件
		channel.transceive(ByteUtil.hexStr2Byte("00a40000023f01"));

		// 校验pin
		channel.transceive(ByteUtil.hexStr2Byte("0020000003596872"));//

		// 805000020B 01 00000002 318001000310
		byte[] data = channel.transceive(ByteUtil
				.hexStr2Byte("805000020B0100000002318001000310"));

		// 交易计数器
		String tradeCount = ByteUtil.hexToStr(data, 4, 2);
		String keyVersion = ByteUtil.hexToStr(data, 6, 1);
		String alglndMark = ByteUtil.hexToStr(data, 7, 1);

		// 伪随机数
		String PRN = ByteUtil.hexToStr(data, 8, 4);
		// mac1
		String MAC1 = ByteUtil.hexToStr(data, 12, 4);

		String seqno = "20150828114641509";

		String requestJson = "{\"REQ_CODE\":1101,\"ENCRY_FLAG\":1,\"APP_MODE\":\"U\",\"PROTOCOL_VER\":\"0.1\",\"IS_TEST\":\"0\",\"UUID\":\"b2f110b6-c860-4731-b1a3-0c5a3228a317\",\"MAC_NO\":\"F0:25:B7:EC:64:1B\",\"DEV_VER\":\"GT-I9507V5.0.1\",\"USERNAME\":\"13288888888\",\"UID\":\"e2882112-311b-11e5-902b-d43d7ee427da\",\"SID\":\"e2882112-311b-11e5-902b-d43d7ee427da\",\"DATA\":{\"city\":\"3180\",\"cardno\":\"3180050401145468\",\"cardver\":\"3180\",\"cardmtype\":\"09\",\"cardstype\":\"14\",\"deposit\":\"0000\",\"cardvaldate\":\"20501231\",\"txmamt\":2,\"cardcnt\":\""
				+ tradeCount
				+ "\",\"random\":\""
				+ PRN
				+ "\",\"befamt\":10790,\"mac1\":\""
				+ MAC1
				+ "\",\"posid\":\"318001000310\",\"posseq\":\"1\",\"keyver\":\""
				+ keyVersion
				+ "\",\"computtag\":\"00\",\"imei\":\"351670063325380\",\"CARD_05_FILE\":\"594431800001000031800504011454688030201505070000030000010005302015050709553799000900011420501231\",\"CARD_15_FILE\":\"594431800001000002023180318005040114546820101001205012310914\",\"UID\":\"b6bdc3c0\",\"BUSI_TYPE\":\"2\",\"BUSI_CODE\":100231,\"CITY_CODE\":\"3180\",\"CARD_NO\":\"3180050401145468\",\"BANK_NO\":\"0\",\"ORDER_ID\":\"20150825134238464\",\"BUSI_TIME\":\"nullnull\",\"PAY_MONEY\":2,\"BEFORE_BALANCE\":10790,\"MAC1\":\""
				+ MAC1
				+ "\",\"CARD_TYPE\":1,\"SERVICE_CODE\":\"0\",\"SEQ_NO\":\""
				+ seqno
				+ "\",\"DESKEY\":\"00000000000000000000000000000000\"}}";

		Future1 ft = new Future1(ThreeDES.encryptMode(keyBytes,
				requestJson.getBytes()));
		String obj = ft.get();

		String mac2 = null;
		String d = null;
		String t = null;
		try {
			JSONObject object = new JSONObject(obj);
			JSONObject dataObject = object.getJSONObject("DATA");
			mac2 = dataObject.getString("MAC2");
			d = object.getString("SYS_DATE");
			t = object.getString("SYS_TIME");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 805200000b2015082513484547ad76bc
		// 805200000b+d+t+mac2
		data = channel.transceive(ByteUtil.hexStr2Byte("805200000b" + d + t
				+ mac2));

		// data =
		// channel.transceive(ByteUtil.hexStr2Byte("805200000b2015082513484500000000"));

		// // 交易计数器
		// String tradeNo = ByteUtil.hexToStr(data, 4, 2);

		// // 取随机数 从第八个字节到第12个
		// String PRN = ByteUtil.hexToStr(data, 8, 4);
		//
		// Des des = new Des();
		// String ccc = des.getHintKey(PRN + tradeNo + "8000",
		// "D3D6E8836832FDD4706D0671BB8BD28B");
		// String macTest = des.PBOC_DES_MAC(ccc, "0000000000000000",
		// "000001000201010101010120150709115901", 0).substring(0, 8);
		//
		// data = channel.transceive(ByteUtil
		// .hexStr2Byte("805200000B20150709115901" + macTest));//
	}

	public int getBalance() throws CardException {
		// select_mf();
		// int bal = getBal();
		topup();
		return 0;
	}
}
