package com.lpf.mynotes.activity.base;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.nfc.NfcAdapter;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.lpf.mynotes.utils.ToastUtils;

public abstract class NfcBaseActivity extends Activity {

	private NfcAdapter mAdapter;
	private PendingIntent mPendingIntent;
	private IntentFilter[] mFilters;

	/**
	 * �Ƿ���Ҫ������ʾ��NFC�Ŀ�
	 * 
	 * @return true:��ʾ false:����ʾ
	 */
	abstract protected boolean showOpenNFCLog();

	static final String[][] TECHLISTS = { { IsoDep.class.getName() },
			{ NfcA.class.getName() }, { NfcB.class.getName() },
			{ MifareClassic.class.getName() }, { NfcV.class.getName() },
			{ NfcF.class.getName() } };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// ��ȡNFC������
		mAdapter = NfcAdapter.getDefaultAdapter(this);

		// �ж��ֻ��Ƿ�֧��NFC
		if (mAdapter != null) {
			mPendingIntent = PendingIntent.getActivity(this, 0,
					new Intent(this, getClass())
							.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

			IntentFilter ndef = new IntentFilter(
					NfcAdapter.ACTION_NDEF_DISCOVERED);
			try {
				ndef.addDataType("*/*");
			} catch (MalformedMimeTypeException e) {
				throw new RuntimeException("fail", e);
			}
			mFilters = new IntentFilter[] { ndef, };

			onNewIntent(getIntent());
		} else {
			if (showOpenNFCLog()) {
				ToastUtils.showShort(this, "���ֻ���֧��NFC,ֻ��NFC�ֻ���֧��ˢ��");
			}

		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		checkNfcIsOpen();
	}

	private void checkNfcIsOpen() {
		// TODO Auto-generated method stub
		if (mAdapter != null) {
			if (!mAdapter.isEnabled()) {
				if (showOpenNFCLog()) {
					Toast.makeText(this, "�뿪��NFC����", Toast.LENGTH_LONG).show();
					// ���Ե��ø÷���ȥ����NFC
					// jump();
				}
			} else {
				mAdapter.enableForegroundDispatch(this, mPendingIntent,
						mFilters, TECHLISTS);
			}
		}
	}

	void jump() {
		if (Build.VERSION.SDK_INT >= 14) {
			// startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
		} else {
			startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
		}
		finish();
	}

	public interface OnNfcResponseListener {
		/**
		 * ���ֻ���Ӧ���豸���߿�Ƭ
		 * 
		 * @param intent
		 */
		void onResponse(Intent intent);
	}

	@Override
	final protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		String action = intent.getAction();
		if (action == NfcAdapter.ACTION_TECH_DISCOVERED
				|| action == NfcAdapter.ACTION_NDEF_DISCOVERED
				|| action == NfcAdapter.ACTION_TAG_DISCOVERED) {
			if (this instanceof OnNfcResponseListener) {
				((OnNfcResponseListener) this).onResponse(intent);
			}
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (mAdapter != null) {
			mAdapter.disableForegroundDispatch(this);
		}
	}

}
