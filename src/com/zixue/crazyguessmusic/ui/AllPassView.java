package com.zixue.crazyguessmusic.ui;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.qingkong.transit.R;

/**
 * Í¨¹Ø»­Ãæ
 * @author FuYihuang
 *
 */
public class AllPassView extends Activity {
		@Override
		protected void onCreate(Bundle bundle) {
			// TODO Auto-generated method stub
			super.onCreate(bundle);
			setContentView(R.layout.all_pass_view);
			ImageButton im = (ImageButton) findViewById(R.id.btn_bar_back);
			im.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});
		}
}
