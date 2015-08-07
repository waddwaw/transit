package com.qingkong.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class DBManager {
	private final int BUFFER_SIZE = 1024;
	private SQLiteDatabase database;
	private Context context;

	public DBManager(Context context) {
		this.context = context;
	}

	public SQLiteDatabase openDatabase() {
		File sdFile = Environment.getExternalStorageDirectory();
		File gpsPath = new File(sdFile.getPath() + "/pms/gps.db");

		if (!gpsPath.exists()) {
			try {
				// ´´½¨Ä¿Â¼
				File pmsPaht = new File(sdFile.getPath() + "/pms");
				Log.i("pmsPaht", "pmsPaht: " + pmsPaht.getPath());
				pmsPaht.mkdirs();

				AssetManager am = this.context.getAssets();
				InputStream is = am.open("changsha.mp3");

				FileOutputStream fos = new FileOutputStream(gpsPath);

				byte[] buffer = new byte[BUFFER_SIZE];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.flush();

				fos.close();
				is.close();
				am.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		database = SQLiteDatabase.openOrCreateDatabase(gpsPath, null);

		return database;
	}

	public void close() {
		if (database != null) {
			this.database.close();
		}
	}

}
