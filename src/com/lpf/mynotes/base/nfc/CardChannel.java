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
		throw new CardException("-1", "卡片已离开，或不支持此卡");
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
			throw new CardException("-1", "卡片已离开，请重新贴卡");
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
				throw new CardException("-1", "连接失败,请重试.");
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
			msg = "该卡暂未支持，敬请期待";
		}
		return msg;
	}

	static Map<String, String> ERROR_TABLE = new HashMap<String, String>();

	static {
		ERROR_TABLE.put("61xx", "亲，支付过程中遇到点麻烦，我们努力为您解决中，请确认支付是否成功。");
		ERROR_TABLE.put("6200", "警告 信息未提供");
		ERROR_TABLE.put("6281", "警告 回送数据可能");
		ERROR_TABLE.put("6282", "警告 文件长度小于Le");
		ERROR_TABLE.put("6283", "警告 选中的文件无效");
		ERROR_TABLE.put("6284", "警告 FCI格式与P2指定的不符");
		ERROR_TABLE.put("6300", "警告 鉴别失败");
		ERROR_TABLE.put("63Cx", "警告 校验失败（x－允许重试次数）");
		ERROR_TABLE.put("6400", " 状态标志位没有变");
		ERROR_TABLE.put("6581", " 内存失败");
		ERROR_TABLE.put("6700", " 长度错误");
		ERROR_TABLE.put("6882", " 不支持安全报文");
		ERROR_TABLE.put("6981", " 命令与文件结构不相容，当前文件非所需文件");
		ERROR_TABLE.put("6982", " 操作条件（AC）不满足，没有校验PIN");
		ERROR_TABLE.put("6983", "卡已被锁定，请联系发卡方解锁");
		ERROR_TABLE.put("6984", " 随机数无效，引用的数据无效");
		ERROR_TABLE.put("6985", " 使用条件不满足");
		ERROR_TABLE.put("6986", " 不满足命令执行条件（不允许的命令，INS有错）");
		ERROR_TABLE.put("6987", " MAC丢失");
		ERROR_TABLE.put("6988", " MAC不正确");
		ERROR_TABLE.put("698D", "保留 ");
		ERROR_TABLE.put("6A80", " 数据域参数不正确");
		ERROR_TABLE.put("6A81", " 功能不支持；创建不允许；目录无效；应用锁定");
		ERROR_TABLE.put("6A82", " 该文件未找到");
		ERROR_TABLE.put("6A83", " 该记录未找到");
		ERROR_TABLE.put("6A84", " 文件预留空间不足");
		ERROR_TABLE.put("6A86", " P1或P2不正确");
		ERROR_TABLE.put("6A88", " 引用数据未找到");
		ERROR_TABLE.put("6B00", " 参数错误");
		ERROR_TABLE.put("6Cxx", " Le长度错误，实际长度是xx");
		ERROR_TABLE.put("6E00", " 不支持的类：CLA有错");
		ERROR_TABLE.put("6F00", " 数据无效");
		ERROR_TABLE.put("6F01", "亲，支付过程中遇到点麻烦，我们努力为您解决中，请确认支付是否成功。");
		ERROR_TABLE.put("6D00", " 不支持的指令代码");
		ERROR_TABLE.put("9301", " 资金不足");
		// ERROR_TABLE.put("9302", " MAC无效");
		ERROR_TABLE.put("9302", " 不支持该卡");
		ERROR_TABLE.put("9303", " 应用被永久锁定");
		ERROR_TABLE.put("9401", " 交易金额不足");
		ERROR_TABLE.put("9402", " 交易计数器达到最大值");
		ERROR_TABLE.put("9403", " 密钥索引不支持");
		ERROR_TABLE.put("9406", " 所需MAC不可用");
		ERROR_TABLE.put("6900", " 不能处理");
		ERROR_TABLE.put("6901", "亲，支付过程中遇到点麻烦，我们努力为您解决中，请确认支付是否成功。");// 命令不接受（无效状态）
		ERROR_TABLE.put("61xx", "正常 需发GET RESPONSE命令");
		ERROR_TABLE.put("6600", " 接收通讯超时");
		ERROR_TABLE.put("6601", " 接收字符奇偶错");
		ERROR_TABLE.put("6602", " 校验和不对");
		ERROR_TABLE.put("6603", "警告 当前DF文件无FCI");
		ERROR_TABLE.put("6604", "警告 当前DF下无SF或KF");
	}

}
