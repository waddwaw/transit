package com.qingkong.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class dataBaseHelper extends SQLiteOpenHelper {

	public dataBaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		System.out.println("��ִ����");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("û���ҵ����ݿ⣡");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("���Լ�������");
	}

}
