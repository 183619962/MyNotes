package com.lpf.mynotes.activity.ui;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.lpf.mynotes.R;
import com.lpf.mynotes.activity.base.BaseActivity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class MyCycleActivity extends BaseActivity implements
		OnPageChangeListener {
	private ViewPager mViewPager;
	private ViewGroup mPointViewGroup;
	private ArrayList<View> mViewPagerList;
	private boolean mIsChanged = false;
	private int mCurrentIndex;
	private static final int POINT_LENGTH = 3;
	private static final int FIRST_ITEM_INDEX = 1;
	// ��ʼ���±��1��ʼ ���Ǵ������ 1 2 3����3���ؼ�
	private int mCurrentPagePosition = FIRST_ITEM_INDEX;
	private static final String TAG = "MOTO";

	private Handler mHandler = new Handler();

	private int[] myImageIds = { R.drawable.gallery01, R.drawable.bgp_login,
			R.drawable.game_card_900 };

	private void initUI() {
		// ��ʼ��viewpager
		mViewPager = (ViewPager) findViewById(R.id.vp_cycle);

		// ��ʼ����Ĳ���
		mPointViewGroup = (ViewGroup) findViewById(R.id.point_layout);

		// Ӣ��װҳ���list
		mViewPagerList = new ArrayList<View>();

		getAdv(myImageIds);

		PagerAdapter pagerAdapter = new CustomPagerAdapter(mViewPagerList);

		mViewPager.setAdapter(pagerAdapter);

		mViewPager.setOnPageChangeListener(this);

		mViewPager.setPageTransformer(true, new DepthPageTransformer());
		// mViewPager.setCurrentItem(mCurrentPagePosition, false);
		mHandler.postDelayed(heartBeatRunnable, 3000);
	}

	// ���ڼ�ʱѭ�����߳�
	private Runnable heartBeatRunnable = new Runnable() {

		@Override
		public void run() {
			// ��ǰλ��С���������ϵĳ��� λ��+1
			if (mCurrentPagePosition < myImageIds.length) {
				mViewPager.setCurrentItem(mCurrentPagePosition, false);
				Log.i(TAG, "�ж�λ��1-->" + mCurrentPagePosition);
				mCurrentPagePosition++;
			}

			// �����ǰλ�õ��ڼ��ϵĳ��� ��λ������Ϊ��һ��ҳ��
			else if (mCurrentPagePosition == myImageIds.length) {
				mViewPager.setCurrentItem(mCurrentPagePosition, false);
				Log.i(TAG, "�ж�λ��2-->" + mCurrentPagePosition);
				mCurrentPagePosition = 1;
			}
			mHandler.postDelayed(this, 3000);
		}
	};

	private void getAdv(int[] imgAdvs) {
		// ��ʼ��Ĭ��ֵ

		// ���±�Ϊ0��������һ��������ͼƬ ���������л�ʱ��Ķ���Ч��
		addImageView(imgAdvs[imgAdvs.length - 1]);

		for (int i = 0; i < imgAdvs.length; i++) {
			// addTextView(i);
			addImageView(imgAdvs[i]);
			addPoint(i);
		}
		// �������ĵ�5�����棬ʵ��������ʾ���ǵ�һ������
		addImageView(imgAdvs[0]);
	}

	private void addTextView(int pIndex) {
		TextView textview = new TextView(this);
		textview.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		textview.setGravity(Gravity.CENTER);
		textview.setTextSize(50);
		mViewPagerList.add(textview);
	}

	/**
	 * ��ʼ��ͼƬ�ؼ�
	 * 
	 * @param pIndex
	 *            ͼƬ������id
	 */
	private void addImageView(int pIndex) {
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(pIndex);
		mViewPagerList.add(imageView);
	}

	private void addPoint(int pIndex) {
		ImageView pointImageView = new ImageView(this);
		LayoutParams layoutParams = new LayoutParams(20, 20);
		layoutParams.setMargins(10, 0, 10, 0);
		pointImageView.setLayoutParams(layoutParams);
		pointImageView.setBackgroundResource(R.drawable.adv_bg_change);
		if (0 == pIndex) {
			pointImageView.setEnabled(false);
		}
		mPointViewGroup.addView(pointImageView);
	}

	private void setCurrentDot(int positon) {
		// ����ʵ����ʾ������ǵ�1, 2, 3����������Ӧ����0, 1, 2.���Լ�1.
		positon = positon - 1;
		if (positon < 0 || positon > mViewPagerList.size() - 1
				|| mCurrentIndex == positon) {
			return;
		}
		mPointViewGroup.getChildAt(positon).setEnabled(false);
		mPointViewGroup.getChildAt(mCurrentIndex).setEnabled(true);
		mCurrentIndex = positon;
	}

	@Override
	public void onPageScrollStateChanged(int pState) {
		if (ViewPager.SCROLL_STATE_IDLE == pState) {
			if (mIsChanged) {
				mIsChanged = false;
				mViewPager.setCurrentItem(mCurrentPagePosition, false);
			}
		}
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int pPosition) {
		mIsChanged = true;
		if (pPosition > myImageIds.length) {
			mCurrentPagePosition = FIRST_ITEM_INDEX;
		} else if (pPosition < FIRST_ITEM_INDEX) {
			mCurrentPagePosition = myImageIds.length;
		} else {
			mCurrentPagePosition = pPosition;
		}
		Log.i(TAG, "��ǰ��λ����" + mCurrentPagePosition);
		setCurrentDot(mCurrentPagePosition);
	}

	class CustomPagerAdapter extends PagerAdapter {

		private ArrayList<View> views;

		public CustomPagerAdapter(ArrayList<View> views) {
			super();
			this.views = views;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(views.get(position));
			return views.get(position);
		}

		// Ϊ�˽�ʡ��Դ��������Ҫ��Imageview����
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(views.get(position));
		}

	}

	@Override
	public int getLayout() {
		// TODO Auto-generated method stub
		return R.layout.activity_mycycle;
	}

	@Override
	public void findView() {
		// TODO Auto-generated method stub
		initUI();
	}

	@Override
	public void setData() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean unConnectNetShowToast() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean showOpenNFCLog() {
		// TODO Auto-generated method stub
		return false;
	}
}
