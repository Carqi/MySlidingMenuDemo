package com.jouhu.yhestate.utils;

import android.view.View;
import android.view.animation.TranslateAnimation;

/**
 * 图片移动的动画效果
 * @Description: 图片移动的动画效果

 * @File: ImageAnimatioin.java

 * @Author 边

 * @Date 2012-12-28 

 * @Version V1.0
 */
public class ImageAnimation {
	/**
	 * 设置图像移动动画效果
	 * @param v
	 * @param startX
	 * @param toX
	 * @param startY
	 * @param toY
	 */
	public static void SetImageSlide(View v, int startX, int toX, int startY, int toY) {
		TranslateAnimation anim = new TranslateAnimation(startX, toX, startY, toY);
		anim.setDuration(250);
		anim.setFillAfter(true);
		v.startAnimation(anim);
	}
}
