package com.jouhu.yhestate.main;

import java.util.ArrayList;

import com.jouhu.yhestate.R;
import com.jouhu.yhestate.bottommenu.Fragment1;
import com.jouhu.yhestate.bottommenu.Fragment2;
import com.jouhu.yhestate.bottommenu.Fragment3;
import com.jouhu.yhestate.bottommenu.Fragment4;
import com.jouhu.yhestate.utils.ImageAnimation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class CenterFragment extends Fragment {
	private MyAdapter mAdapter;
	private ViewPager mViewPage;
	public ArrayList<Fragment> pagerItemList = new ArrayList<Fragment>();
	private RadioGroup menue = null;
	private ImageView mImageView;
	private RelativeLayout mButtomLayout;
	private int startLeft;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.container_tab, null);
		mButtomLayout = (RelativeLayout) mView.findViewById(R.id.bottom_layout);
		mImageView = new ImageView(getActivity());
		mImageView.setMinimumWidth(getSlideWidth());
		//mImageView.setImageResource(R.drawable.tab_menu_select);
		mImageView.setPadding(0, 10, 0, 0);
		mButtomLayout.addView(mImageView);
		// 添加导航烂
		menue = (RadioGroup) mView.findViewById(R.id.tab_rg_menu);
		menue.setOnCheckedChangeListener(meanueChecked);

		mViewPage = (ViewPager) mView.findViewById(R.id.pager);
		
		Fragment1 home1 = new Fragment1();
		Fragment2 home2 = new Fragment2();
		Fragment3 home3 = new Fragment3();
		Fragment4 home4 = new Fragment4();
		Fragment4 home5 = new Fragment4();
		pagerItemList.add(home1);
		pagerItemList.add(home2);
		pagerItemList.add(home3);
		pagerItemList.add(home4);
		pagerItemList.add(home5);

		// /在此可以动态添加尝试radiobutton

		mAdapter = new MyAdapter(getFragmentManager());
		mViewPage.setAdapter(mAdapter);

		mViewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {

				if (myPageChangeListener != null) {
					myPageChangeListener.onPageSelected(position);

					switch (position) {
					case 0:
						menue.check(R.id.tab_rb_1);
						break;
					case 1:
						menue.check(R.id.tab_rb_2);
						break;
					case 2:
						menue.check(R.id.tab_rb_3);
						break;
					case 3:
						menue.check(R.id.tab_rb_4);
						break;
					case 4:
						menue.check(R.id.tab_rb_5);
						break;
					}
					// ///设置导航栏的radio切换
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int position) {

			}
		});
		return mView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
	}
	
	// /导航栏radiogroup监听器
	private OnCheckedChangeListener meanueChecked = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup arg0, int arg1) {
			// TODO Auto-generated method stub
			int index = 0;
			switch (arg1) {
			case R.id.tab_rb_1:
				index = 0;
				ImageAnimation.SetImageSlide(mImageView, startLeft, 0, 0, 0);
				startLeft = 0;
				break;
			case R.id.tab_rb_2:
				index = 1;
				ImageAnimation.SetImageSlide(mImageView, startLeft,
						getSlideWidth(), 0, 0);
				startLeft = getSlideWidth();
				break;
			case R.id.tab_rb_3:
				index = 2;
				ImageAnimation.SetImageSlide(mImageView, startLeft,
						getSlideWidth() * 2, 0, 0);
				startLeft = getSlideWidth() * 2;
				break;
			case R.id.tab_rb_4:
				index = 3;
				ImageAnimation.SetImageSlide(mImageView, startLeft,
						getSlideWidth() * 3, 0, 0);
				startLeft = getSlideWidth() * 3;
				break;
			case R.id.tab_rb_5:
				index = 4;
				ImageAnimation.SetImageSlide(mImageView, startLeft,
						getSlideWidth() * 4, 0, 0);
				startLeft = getSlideWidth() * 4;
				break;
			}
			mViewPage.setCurrentItem(index);

		}
	};

	@SuppressWarnings("deprecation")
	private int getSlideWidth() {
		WindowManager windowManager = getActivity().getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		int screenWidth = display.getWidth();
		return screenWidth / 5;
	}

	public boolean isFirst() {
		if (mViewPage.getCurrentItem() == 0)
			return true;
		else
			return false;
	}

	public boolean isEnd() {
		if (mViewPage.getCurrentItem() == pagerItemList.size() - 1)
			return true;
		else
			return false;
	}

	public class MyAdapter extends FragmentPagerAdapter {
		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return pagerItemList.size();
		}

		@Override
		public Fragment getItem(int position) {

			Fragment fragment = null;
			if (position < pagerItemList.size())
				fragment = pagerItemList.get(position);
			else
				fragment = pagerItemList.get(0);

			return fragment;

		}
	}

	private MyPageChangeListener myPageChangeListener;

	public void setMyPageChangeListener(MyPageChangeListener l) {

		myPageChangeListener = l;

	}

	public interface MyPageChangeListener {

		public void onPageSelected(int position);

	}

	public void setSelectOnItem(int i) {
		// TODO Auto-generated method stub
		mViewPage.setCurrentItem(i);
	}
}
