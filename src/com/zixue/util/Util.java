package com.zixue.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import com.qingkong.transit.R;
import com.zixue.crazyguessmusic.model.IAlertDialogButtonListener;
import com.zixue.crazyguessmusic.ui.MainActivityto;
import com.zixue.guessmusic.data.Const;

import android.R.integer;
import android.R.raw;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.location.GpsStatus.Listener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Util {
	private static AlertDialog mAlertDialog;
	public  static View getView(Context context,int layoutId) {
		LayoutInflater inflater=(LayoutInflater) context.
				getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout=inflater.inflate(layoutId, null);
		return layout;
	}
	/**
	 * 
	 * @param context
	 * @param desti
	 */
	public static void startActivity(Context context, Class desti){
		Intent intent =  new Intent();
		intent.setClass(context, desti);
		context.startActivity(intent);
		
		//关闭当前的activity
		((Activity)context).finish();
	}
	/**
	 * 显示自定义的对话框
	 * @param context
	 * @param message
	 * @param listener
	 */
	public static void showDialog
	(final Context context, String message,final IAlertDialogButtonListener listener){
		View dialogView =null;
		AlertDialog.Builder dialog = new AlertDialog.Builder(context,R.style.Theme_Transparent);
		dialogView = getView(context, R.layout.dialog_view);
		ImageButton btnokView = (ImageButton) dialogView.findViewById(R.id.btn_dialog_ok);
		ImageButton btncancelView = (ImageButton) dialogView.findViewById(R.id.btn_dialog_cancel);
		TextView textView =(TextView) dialogView.findViewById(R.id.text_dialog_message);
		textView.setText(message);
		btnokView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//关闭对话框
				if (mAlertDialog != null) {
					mAlertDialog.cancel();
				}
				//事件回调
				if (listener != null) {
					
					listener.onClick();
				}
				//播放音效
				MyPlayer.playTong(context,MyPlayer.INDEX_STONE_ENTER);
			}
		});
			btncancelView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//关闭对话框
				if (mAlertDialog != null) {
					mAlertDialog.cancel();
				}
				//播放音效
				MyPlayer.playTong(context, MyPlayer.INDEX_STONE_CANCEL);
			}
		});
		//为dialog设置view
			dialog.setView(dialogView);
			mAlertDialog = dialog.create();
			//显示对话框
			mAlertDialog.show();
	}
	//保存游戏数据
	public  static void saveData(Context context, int stageIndex , int coins){
		FileOutputStream fis= null; 
		
		try {
			fis = context.openFileOutput
					(Const.FILE_NAME_SAVE_DATA, context.MODE_PRIVATE);
			DataOutputStream dos = new DataOutputStream(fis);
				dos.writeInt(stageIndex);
				dos.writeInt(coins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	/**
	 * 读取游戏数据
	 * @param context
	 * @return
	 */
	public static int[] loadData(Context context){
		FileInputStream fis=null;
		int[] datas ={-1,Const.TOTAL_COINS};
		try {
			fis=context.openFileInput(Const.FILE_NAME_SAVE_DATA);
			DataInputStream dis = new DataInputStream(fis);
				datas[Const.INDEX_LOAD_DATA_STAGE]=dis.readInt();
				datas[Const.INDEX_LOAD_DATA_CONIS]=dis.readInt();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return datas;
	}
}
