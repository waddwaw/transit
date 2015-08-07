package com.qingkong.Activity;


import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.qingkong.transit.R;
import com.qingkong.utils.iotext;
import com.qingkong.utils.textcha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class SplashActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splashactivity);
		tes();
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				Intent it = new Intent(SplashActivity.this, MainActivity.class);
				startActivity(it);
				finish();
			}
		}, 3000);
	}
	private void tes() {
		iotext io = new iotext(SplashActivity.this);
		io.du();
	}
}
