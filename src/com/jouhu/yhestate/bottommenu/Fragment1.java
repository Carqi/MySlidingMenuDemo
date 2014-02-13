package com.jouhu.yhestate.bottommenu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jouhu.yhestate.main.MainActivity;
import com.jouhu.yhestate.R;

public class Fragment1 extends Fragment implements OnClickListener{
	private TextView btnLeft;
	private Context context;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		context = getActivity();
		View parentView = inflater.inflate(R.layout.fragment1, container, false);
		Button backBtn = (Button)parentView.findViewById(R.id.button1);
		Button toOtherActivityBtn = (Button)parentView.findViewById(R.id.button2);
		backBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				if(mListener!=null)mListener.backEvent();
			}
		});
		
		toOtherActivityBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, OtherActivity1.class);
				startActivity(intent);
			}
		});
		
		
		return parentView;
		// return super.onCreateView(, container, savedInstanceState);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		btnLeft = (TextView) getView().findViewById(R.id.leftBtn);
		btnLeft.setVisibility(View.VISIBLE);
		btnLeft.setOnClickListener(this);
	}

	public interface OnDataReturnListener {
		public void onDataReturned(Bundle bundle);
	}
	
	
	public void upData(Bundle bundle) {
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.leftBtn:
					((MainActivity) getActivity()).showLeft();
				
			break;

		default:
			break;
		}
	}
}
