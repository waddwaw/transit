package com.qingkong.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.qingkong.bean.cnbus;

public class check {
	Context mContext;
	List<cnbus> qidian = new ArrayList<cnbus>();
	List<cnbus> zhongdian = new ArrayList<cnbus>();
	List<cnbus> fanhui = new ArrayList<cnbus>();

	DBManager dbm = new DBManager(mContext);

	int pmmin;
	int pmmax;

	public check(Context m) {
		mContext = m;
	}

	String sql = "select * from cnbus limit 100";
	String sql_qi = "select * from cnbus where zhan = '一字墙'";
	String sql_zh = "select * from cnbus where zhan = '铁道学院'";

	String sqla = "select * from "
			+ "(select * from cnbus where zhan='长沙市中心医院') A,"
			+ "(select * from cnbus where zhan='铁道学院') B"
			+ " where A.xid=B.xid";

	SQLiteDatabase db = dbm.openDatabase();

	public void chaxun() {
		Cursor cur = db.rawQuery(sqla, null);
		while (cur.moveToNext()) {
			cnbus c = new cnbus();
			c.setKid(cur.getInt(cur.getColumnIndex("kind")));
			c.setXid(cur.getInt(cur.getColumnIndex("xid")));
			c.setZhan(cur.getString(cur.getColumnIndex("zhan")));
			c.setPm(cur.getInt(cur.getColumnIndex("pm")));
			qidian.add(c);
			// System.out.println(c.getXid()+"***"+c.getPm());
		}
		for (int i = 0; i < qidian.size() - 1; i++) {
			cnbus c = qidian.get(i);
			cnbus d = qidian.get(i + 1);
			for (int j = c.getPm(); j <= d.getPm(); j++) {
				String sql = "select * from cnbus where xid=? and pm =? and kind=1";
				Cursor cur1 = db.rawQuery(sql, new String[] { c.getXid() + "",
						j + "" });
				System.out.println("---"+j+"---"+c.getXid());
				while (cur1.moveToNext()) {
					cnbus c1 = new cnbus();
					c1.setKid(cur1.getInt(cur1.getColumnIndex("kind")));
					c1.setXid(cur1.getInt(cur1.getColumnIndex("xid")));
					c1.setZhan(cur1.getString(cur1.getColumnIndex("zhan")));
					c1.setPm(cur1.getInt(cur1.getColumnIndex("pm")));
					fanhui.add(c1);
					// System.out.println(c.getXid()+"***"+c.getPm());
				}
			}
			i++;
		}

		for (cnbus c : fanhui) {

//			System.out.println(c.getZhan());

		}
	}
}
