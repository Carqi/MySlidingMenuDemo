package com.jouhu.yhestate.main;

import java.util.Timer;
import java.util.TimerTask;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.Toast;

import com.jouhu.yhestate.R;
import com.jouhu.yhestate.bottommenu.Fragment1;
import com.jouhu.yhestate.bottommenu.Fragment1.OnDataReturnListener;
import com.jouhu.yhestate.bottommenu.MenuLeftFragment;
import com.jouhu.yhestate.bottommenu.MenuRightFragment;
import com.jouhu.yhestate.main.CenterFragment.MyPageChangeListener;
import com.jouhu.yhestate.widget.SlidingMenu;

public class MainActivity extends FragmentActivity implements OnDataReturnListener {

	private SlidingMenu mSlidingMenu;
	private MenuLeftFragment leftFragment;	// 左fragment
	private MenuRightFragment rightFragment;	// 右fragment
	private CenterFragment centerFragment;	// 中间复合fragment
	private SharedPreferences setPush;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.container_main);
		setPush = getSharedPreferences("push", 0);
		init();
		initListener();
		Editor editor = setPush.edit();
		editor.putInt("pushStatus", 1);
		editor.commit();
	}

	private void init() {
		mSlidingMenu = (SlidingMenu) findViewById(R.id.slidingMenu);
		mSlidingMenu.setLeftView(getLayoutInflater().inflate(R.layout.container_left, null));
		mSlidingMenu.setRightView(getLayoutInflater().inflate(R.layout.container_right, null));
		mSlidingMenu.setCenterView(getLayoutInflater().inflate(R.layout.container_center, null));

		FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
		leftFragment = new MenuLeftFragment();
		t.replace(R.id.left_frame, leftFragment);	// 用leftFragment替换布局

		rightFragment = new MenuRightFragment();		// 用rightFragment替换布局
		t.replace(R.id.right_frame, rightFragment);

		centerFragment = new CenterFragment();
		t.replace(R.id.center_frame, centerFragment);
		t.commit();
	}

	private void initListener() {
		centerFragment.setMyPageChangeListener(new MyPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				if(centerFragment.isFirst()){
					mSlidingMenu.setCanSliding(true,false);
				}else if(centerFragment.isEnd()){
					mSlidingMenu.setCanSliding(false,true);
				}else{
					mSlidingMenu.setCanSliding(false,false);
				}
			}
		});
	}

	public void showLeft() {
		mSlidingMenu.showLeftView();
	}

	public void showRight() {
		mSlidingMenu.showRightView();
	}

	@Override
	public void onDataReturned(Bundle bundle) {
		// TODO Auto-generated method stub
		Fragment1 home = (Fragment1)centerFragment.pagerItemList.get(0);
		if(home!=null)
		{
			centerFragment.setSelectOnItem(0);
			home.upData(bundle);
			showLeft();
		}
		else
		{
			CenterFragment viewPage = new CenterFragment();
			viewPage.setArguments(bundle);
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();  
			  
            // Replace whatever is in the fragment_container view with this fragment,  
            // and add the transaction to the back stack so the user can navigate back  
            transaction.replace(R.id.center_frame, viewPage);  
            transaction.addToBackStack(null);  
  
            // Commit the transaction  
            transaction.commit();  
		}
	}
	/** 
	 * 菜单、返回键响应 
	 */  
	@Override  
	public boolean onKeyDown(int keyCode, KeyEvent event) {  
	    // TODO Auto-generated method stub  
	    if(keyCode == KeyEvent.KEYCODE_BACK)  
	       {    
	           exitBy2Click();      //调用双击退出函数  
	       }  
	    return false;  
	}  
	/** 
	 * 双击退出函数 
	 */  
	private static Boolean isExit = false;  
	  
	private void exitBy2Click() {  
	    Timer tExit = null;  
	    if (isExit == false) {  
	        isExit = true; // 准备退出  
	        Toast.makeText(this, "再按一次退出程序...", Toast.LENGTH_SHORT).show();  
	        tExit = new Timer();  
	        tExit.schedule(new TimerTask() {  
	            @Override  
	            public void run() {  
	                isExit = false; // 取消退出  
	            }  
	        }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务  
	  
	    } else {  
	        finish();  
	        System.exit(0);  
	    }  
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
