package com.qingkong.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.qingkong.bean.textbean;

import android.content.Context;
import android.content.res.AssetManager;

public class iotext {
	Context mContext;
	static List<textbean> lists = new ArrayList<textbean>();

	public iotext(Context c) {
		mContext = c;
	}

	public void du() {
		lists.clear();
		try {
			AssetManager am = mContext.getAssets();
			InputStream is = am.open("out.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					"utf-8"));
			String line = "";
			while ((line = br.readLine()) != null) {
//				System.out.println(line+"---");
				textbean t = new textbean();
				String[] s = line.split("=");
				t.setXianlu(s[0].substring(0, s[0].indexOf("|")));
				t.setZhanming(s[1]);
				lists.add(t);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
