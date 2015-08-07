package com.zixue.crazyguessmusic.ui;
import java.util.Timer;
import java.util.TimerTask;

import com.qingkong.transit.R;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Welcome extends Activity {
	Handler hander;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				startActivity(new Intent(Welcome.this,MainActivityto.class));
				finish();
			}
		}, 3000);
	}
		
}
