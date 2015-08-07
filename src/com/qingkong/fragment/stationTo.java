package com.qingkong.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.qingkong.Activity.SplashActivity;
import com.qingkong.transit.R;
import com.qingkong.utils.iotext;
import com.qingkong.utils.textcha;

public class stationTo extends BaseFragment{

	Button bt1;
	ListView lv;
	EditText qi,zhong;
	ImageView huan;
	List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
	SimpleAdapter sap ;
	@Override
	public View initview() {
		View view =View.inflate(mActivity, R.layout.station_a, null);
		lv = (ListView) view.findViewById(R.id.cha_listview);
		bt1=(Button) view.findViewById(R.id.cha_bt);
		qi =(EditText) view.findViewById(R.id.start);
		zhong =(EditText) view.findViewById(R.id.end);
		huan = (ImageView) view.findViewById(R.id.change);
		sap=new SimpleAdapter(mActivity, lists, R.layout.chalistitem, new String[]{"tit"}, new int[]{R.id.itme_list_text});
		lv.setAdapter(sap);
		bt1.setFocusable(true);
		TextView t = (TextView) view.findViewById(R.id.fantxt);
		t.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String tmp =qi.getText().toString();
				qi.setText(zhong.getText().toString());
				zhong.setText(tmp);
			}
		});
		
		huan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String tmp =qi.getText().toString();
				qi.setText(zhong.getText().toString());
				zhong.setText(tmp);
			}
		});
		bt1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				lists.clear();
				textcha c = new textcha(mActivity);
				List<String> s =c.cha(qi.getText().toString(), zhong.getText().toString()); 
//				List<String> s =c.cha("铁道学院", "湖南农大"); 
				for(String c1 :s){
					HashMap<String,Object> m = new HashMap<>();
					m.put("tit", c1);
					lists.add(m);
//					System.out.println(c1);
				}
				sap.notifyDataSetChanged();
			}
		});
		return view;
	}

}
