package com.qingkong.fragment;



import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.qingkong.transit.R;
import com.zixue.crazyguessmusic.ui.MainActivityto;

public class staTion extends BaseFragment {

	@Override
	public View initview() {
		View view = View.inflate(mActivity, R.layout.game_layout,null);
//		TextView text = new TextView(mActivity);
//		text.setText("ªª≥À≤È—Ø");
//		text.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent in = new Intent(mActivity, MainActivityto.class);
//				startActivity(in);
//			}
//		});
		Button bt = (Button) view.findViewById(R.id.game_bt);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(mActivity, MainActivityto.class);
				startActivity(in);
			}
		});
		return view;
	}

}
