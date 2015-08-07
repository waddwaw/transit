package com.zixue.crazyguessmusic.model;

public interface IWordButtonClickListenler {
	/**
	 * 当按键点击时实现的逻辑
	 * Mygridview与mainActivity之间的桥梁
	 */
	void onWordButtonClick(WordButton wordButton);

	void onPasue();
}
