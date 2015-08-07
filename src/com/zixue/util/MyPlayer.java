package com.zixue.util;

import java.io.FileDescriptor;
import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.provider.MediaStore.Audio.Media;

/**
 * 用于管理声音的类
 * 音乐播放器
 * @author FuYihuang
 *
 */
public class MyPlayer {
	//
	public final static int INDEX_STONE_ENTER = 0;
	public final static int INDEX_STONE_CANCEL= 1;
	public final static int INDEX_STONE_COIN  = 2;
	
	//定义一个音效名字
	private final static String [] SONE_NAMES = 
		{"enter.mp3","cancel.mp3","coin.mp3"};
	//音效
	//定义一个音效数组
	private static MediaPlayer[] mToneMediaPlayer = new MediaPlayer[SONE_NAMES.length];
	//播放歌曲的文件
	private static MediaPlayer mMusicmMediaPlayer;
	//音效播放
	public static void playTong(Context context, int index){
		//加载声音
		AssetManager assetManager = context.getAssets();
		if (mToneMediaPlayer[index] == null) {
			mToneMediaPlayer[index] = new MediaPlayer();
			try {
				AssetFileDescriptor assetFileDescriptor = 
						assetManager.openFd(SONE_NAMES[index]);
				mToneMediaPlayer[index].setDataSource
				(assetFileDescriptor.getFileDescriptor(),
						assetFileDescriptor.getStartOffset(),
						assetFileDescriptor.getLength());
				mToneMediaPlayer[index].prepare();//使播放器进入播放状态
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		mToneMediaPlayer[index].start();
		
		
	}
	//歌曲播放
	public static  void playSong(Context context ,String filename){
		//通过这个判断可实现不管用了多少次只创建了一次
		if (mMusicmMediaPlayer == null) {
			mMusicmMediaPlayer = new MediaPlayer();
		}
		//强制重置
		mMusicmMediaPlayer.reset();
		
		//加载声音文件
		AssetManager assetManager = context.getAssets();
		//获取数据源
		try {
			AssetFileDescriptor fileDescriptor = assetManager.openFd(filename);
			mMusicmMediaPlayer.setDataSource
			(fileDescriptor.getFileDescriptor(),
					fileDescriptor.getStartOffset(),
					fileDescriptor.getLength());
			
			mMusicmMediaPlayer.prepare();
			//声音播放
			mMusicmMediaPlayer.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void stopTheSong(Context context){
		if (mMusicmMediaPlayer	!= null ) {
			mMusicmMediaPlayer.stop();
		}
	}
}
