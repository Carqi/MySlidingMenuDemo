package com.jouhu.yhestate.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

/**
 * 
 *  Class Name: EventReceiver.java
 *  注册广播监听，监测网络状态
 *  @author Yu Liu  DateTime 2013-10-26 上午8:51:47    
 *  @version 1.0
 *  @company 长春优狐科技开发有限公司
 */
public class EventReceiver extends BroadcastReceiver {
	private static final String tag = Log.getTag(EventReceiver.class);
	private static boolean isNetworkUnavailable = false;

	public EventReceiver() {
		super();
	}
	
	public EventReceiver(Context context) {
		isNetworkUnavailable = !NetworkUtils.isNetworkConnected(context);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
			isNetworkUnavailable = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
			Log.i(tag, "========================网络状态变更======isNetworkUnavailable==" + isNetworkUnavailable);
		}
	}

	public static boolean isNetworkUnavailable() {
		return isNetworkUnavailable;
	}

}
