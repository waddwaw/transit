package com.qingkong.utils;


import android.content.Context;
import android.content.SharedPreferences;

public class Utils {


	
	private static SharedPreferences sp;
	private static final String PREF_NAME = "qkdc";

	public static void setString(Context context, String key, String value) {
		if (sp == null) {
			sp = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
		}
		sp.edit().putString(key, value).commit();
	}

	
	public static String getString(Context context, String key, String defValue) {
		if (sp == null) {
			sp = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
		}

		return sp.getString(key, defValue);

	}

	
	public static void SetBoolean(Context context, String key, boolean value) {
		if (sp == null) {
			sp = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
		}
		sp.edit().putBoolean(key, value).commit();
	}

	public static boolean getBoolean(Context context, String key,
			boolean defValue) {
		if (sp == null) {
			sp = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
		}

		return sp.getBoolean(key, defValue);
	}

	public static void setInt(Context context, String key, int Value) {
		if (sp == null) {
			sp = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
		}

		sp.edit().putInt(key, Value).commit();
	}
}
