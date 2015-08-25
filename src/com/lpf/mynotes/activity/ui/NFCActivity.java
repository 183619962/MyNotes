package com.lpf.mynotes.activity.ui;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.widget.TextView;

import com.lpf.mynotes.R;
import com.lpf.mynotes.activity.base.BaseActivity;
import com.lpf.mynotes.activity.base.NfcBaseActivity.OnNfcResponseListener;
import com.lpf.mynotes.base.nfc.AbstractCardService;
import com.lpf.mynotes.exception.CardException;

public class NFCActivity extends BaseActivity implements OnNfcResponseListener {

	TextView balance;

	@Override
	public int getLayout() {
		// TODO Auto-generated method stub
		return R.layout.activity_nfcactivity;
	}

	@Override
	public void findView() {
		// TODO Auto-generated method stub
		balance = (TextView) findViewById(R.id.balance);
	}

	@Override
	public void setData() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean unConnectNetShowToast() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected boolean showOpenNFCLog() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void onResponse(Intent intent) {
		// TODO Auto-generated method stub

		try {
			Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			AbstractCardService cardService = new AbstractCardService(tag);
			int bal = cardService.getBalance();
			balance.setText((float) bal / 100 + "Ԫ");
		} catch (CardException e) {
			// TODO Auto-generated catch block
			balance.setText(e.getMessage());
			e.printStackTrace();
		}
	}

}
