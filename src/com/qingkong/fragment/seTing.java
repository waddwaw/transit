package com.qingkong.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.qingkong.Activity.guanyu;
import com.qingkong.transit.R;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class seTing extends BaseFragment {
	ListView lv = null;
	SimpleAdapter adapter = null;
	ArrayList<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
	AlertDialog dialog;

	@Override
	public View initview() {
		View view = View.inflate(mActivity, R.layout.setting, null);
		lv = (ListView) view.findViewById(R.id.slv);
		initData();
		adapter = new SimpleAdapter(mActivity, datas, R.layout.new_style,
				new String[] { "pic", "title" },
				new int[] { R.id.iv1, R.id.tv1 });
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position == 1) {
					Intent in = new Intent(mActivity, guanyu.class);
					mActivity.startActivity(in);

				}
				if (position == 0) {
					showguanyu();
				}
			}
		});
		return view;
	}

	private void initData() {
		// 添加数据

		HashMap<String, Object> h2 = new HashMap<String, Object>();
		// 声明一个map集合
		h2.put("pic", R.drawable.us);
		h2.put("title", "   关于我们");

		datas.add(h2);
		// 把map集合h放入list集合datas
		HashMap<String, Object> h = new HashMap<String, Object>();
		// 声明一个map集合
		// 以键值对的形式向map集合存入数据
		h.put("pic", R.drawable.dibiao);
		h.put("title", "   使用说明");
		datas.add(h);
		// 把map集合h放入list集合datas
	}

	public void showguanyu() {
		AlertDialog.Builder builder = new Builder(mActivity);
		View view = View.inflate(mActivity, R.layout.showguanyu, null);

		Button bt_setup_close = (Button) view.findViewById(R.id.bt_close);
		final ImageView im1 = (ImageView) view.findViewById(R.id.nuber_1);
		final ImageView im2 = (ImageView) view.findViewById(R.id.nuber_2);
		final ImageView im3 = (ImageView) view.findViewById(R.id.nuber_3);
		final ImageView im4 = (ImageView) view.findViewById(R.id.nuber_4);
		
		im1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				im1.setBackgroundResource(R.drawable.a1);
				im3.setBackgroundResource(R.drawable.c3);
				im2.setBackgroundResource(R.drawable.c2);
				im4.setBackgroundResource(R.drawable.b1);
			}
		});
		im2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				im3.setBackgroundResource(R.drawable.c3);
				im1.setBackgroundResource(R.drawable.c1);
				im2.setBackgroundResource(R.drawable.a2);
				im4.setBackgroundResource(R.drawable.b2);
			}
		});
		im3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				im2.setBackgroundResource(R.drawable.c2);
				im1.setBackgroundResource(R.drawable.c1);
				im3.setBackgroundResource(R.drawable.a3);
				im4.setBackgroundResource(R.drawable.b3);
			}
		});
		im4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(mActivity, "晴空Bus 关于", 8000).show();

			}
		});

		bt_setup_close.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();

			}
		});

		dialog = builder.create();
		dialog.setView(view, 0, 0, 0, 0);
		dialog.show();
	}
}
