package com.jouhu.yhestate.bottommenu;

import com.jouhu.yhestate.R;
import com.jouhu.yhestate.core.AppConfig;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.TabSpec;

public class FragmentBottomTab extends FragmentActivity  {

	// 定义FragmentTabHost对象
	private FragmentTabHost mTabHost;
	private RadioGroup mTabRg;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bottom_tab);
		initView();
	}

	private void initView() {
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		// 得到fragment的个数
		int count = AppConfig.BOTTOM_MENU.length;
		for (int i = 0; i < count; i++) {
			// 为每一个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = mTabHost.newTabSpec(i + "").setIndicator(i + "");
			// 将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec, AppConfig.BOTTOM_MENU[i], null);
		}

		mTabRg = (RadioGroup) findViewById(R.id.tab_rg_menu);
		mTabRg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.tab_rb_1:
					mTabHost.setCurrentTab(0);
					break;
				case R.id.tab_rb_2:
					mTabHost.setCurrentTab(1);

					break;
				case R.id.tab_rb_3:

					mTabHost.setCurrentTab(2);
					break;
				case R.id.tab_rb_4:

					mTabHost.setCurrentTab(3);
					break;

				default:
					break;
				}
			}
		});

		mTabHost.setCurrentTab(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

//	@Override
//	public void backEvent() {
//		Toast.makeText(this, "back", Toast.LENGTH_SHORT).show();
//	}

}
