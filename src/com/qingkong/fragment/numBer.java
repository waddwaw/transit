package com.qingkong.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qingkong.transit.R;
import com.qingkong.utils.iotext;
import com.qingkong.utils.textcha;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class numBer extends BaseFragment {
	Button bt1;
	ListView lv;
	EditText xian;
	List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
	SimpleAdapter sap;

	@Override
	public View initview() {
		View view = View.inflate(mActivity, R.layout.num_ber, null);
		bt1 = (Button) view.findViewById(R.id.xiancha);
		lv = (ListView) view.findViewById(R.id.cha_number);
		xian = (EditText) view.findViewById(R.id.cha_lu);
		sap = new SimpleAdapter(mActivity, lists, R.layout.chalistitem,
				new String[] { "tit" }, new int[] { R.id.itme_list_text });
		lv.setAdapter(sap);
		bt1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				lists.clear();
				textcha c = new textcha(mActivity);
				String s = c.xiancha(xian.getText().toString());
				HashMap<String, Object> m = new HashMap<>();
				m.put("tit", s);
				lists.add(m);
				sap.notifyDataSetChanged();
			}
		});
		return view;
	}
}
