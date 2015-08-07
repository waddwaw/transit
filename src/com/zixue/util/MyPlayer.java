package com.zixue.util;

import java.io.FileDescriptor;
import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.provider.MediaStore.Audio.Media;

/**
 * ���ڹ�����������
 * ���ֲ�����
 * @author FuYihuang
 *
 */
public class MyPlayer {
	//
	public final static int INDEX_STONE_ENTER = 0;
	public final static int INDEX_STONE_CANCEL= 1;
	public final static int INDEX_STONE_COIN  = 2;
	
	//����һ����Ч����
	private final static String [] SONE_NAMES = 
		{"enter.mp3","cancel.mp3","coin.mp3"};
	//��Ч
	//����һ����Ч����
	private static MediaPlayer[] mToneMediaPlayer = new MediaPlayer[SONE_NAMES.length];
	//���Ÿ������ļ�
	private static MediaPlayer mMusicmMediaPlayer;
	//��Ч����
	public static void playTong(Context context, int index){
		//��������
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
				mToneMediaPlayer[index].prepare();//ʹ���������벥��״̬
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		mToneMediaPlayer[index].start();
		
		
	}
	//��������
	public static  void playSong(Context context ,String filename){
		//ͨ������жϿ�ʵ�ֲ������˶��ٴ�ֻ������һ��
		if (mMusicmMediaPlayer == null) {
			mMusicmMediaPlayer = new MediaPlayer();
		}
		//ǿ������
		mMusicmMediaPlayer.reset();
		
		//���������ļ�
		AssetManager assetManager = context.getAssets();
		//��ȡ����Դ
		try {
			AssetFileDescriptor fileDescriptor = assetManager.openFd(filename);
			mMusicmMediaPlayer.setDataSource
			(fileDescriptor.getFileDescriptor(),
					fileDescriptor.getStartOffset(),
					fileDescriptor.getLength());
			
			mMusicmMediaPlayer.prepare();
			//��������
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
