package com.zixue.crazyguessmusic.model;

public class Song {
	//��������
	private String mSongname;
	//�����ļ�����
	private String mSongFileName;
	//�������Ƴ���
	private int mNameLength;
	//���ַ���ת��������
	public char[] getNameCharacters(){
		return mSongname.toCharArray();
	}
	public String getSongname() {
		return mSongname;
	}
	
	public void setSongname(String songname) {
		this.mSongname = songname;
		this.mNameLength=mSongname.length();
	}
	public String getSongFileName() {
		return mSongFileName;
		
	}
	public void setSongFileName(String songFileName) {
		this.mSongFileName = songFileName;
	}
	public int getNameLength() {
		return mNameLength;
	}
}
