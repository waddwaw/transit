package com.zixue.crazyguessmusic.model;

public class Song {
	//歌曲名称
	private String mSongname;
	//歌曲文件名称
	private String mSongFileName;
	//歌曲名称长度
	private int mNameLength;
	//把字符串转换成文字
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
