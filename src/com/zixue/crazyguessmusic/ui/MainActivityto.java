package com.zixue.crazyguessmusic.ui;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import com.qingkong.transit.R;
import com.zixue.crazyguessmusic.model.IAlertDialogButtonListener;
import com.zixue.crazyguessmusic.model.IWordButtonClickListenler;
import com.zixue.crazyguessmusic.model.Song;
import com.zixue.crazyguessmusic.model.WordButton;
import com.zixue.guessmusic.data.Const;
import com.zixue.guessmusic.myui.MyGridView;
import com.zixue.util.MyLog;
import com.zixue.util.MyPlayer;
import com.zixue.util.Util;

import android.R.color;
import android.R.integer;
import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityto extends Activity implements IWordButtonClickListenler{
	public final static String TAG= "MainActivity";
	/** 答案状态-- 正确*/
	public final static int STATUS_ANSWER_RIGHT = 1;
	/** 答案状态-- 错误*/
	public final static int STATUS_ANSWER_WRONG = 2;
	/** 答案状态-- 不完整*/
	public final static int STATUS_ANSWER_LACK = 3;
	
	//闪烁次数
	public final static int SPASH_TIMES=6;
	
	public final int ID_DIALOG_DELETE_WORD = 1;
	
	public final int ID_DIALOG_TIP_ANSWER = 2;
	
	public final int ID_DIALOG_LACK_COINS = 3;
	//唱片相关动画
	private Animation mPanAnim;
	private LinearInterpolator mPanlin;//动画运动速度
	
	private Animation mBarInAnim;
	private LinearInterpolator mBarInlin;
	
	private Animation mBarOutAnim;
	private LinearInterpolator mBarOutlin;
	
	private ImageView mViewPan;
	
	private ImageView mViewPanBar;
	//当前关的索引	
	private TextView mCurrentStageTextView;
	
	private TextView mCurrenStageView; 
	
	//当前歌曲的名称
	private TextView mCurrentSongNamePassView;
	//play按键事件
	private ImageButton mBtnPlayStart;
	
	//过关界面
	private View mPassView;
	
	/**
	 * 用来检测盘片是否在与运行
	 */
	private boolean misRunning=false;
	//文字框容器
	//待选框文字容器
	private ArrayList<WordButton> mAllWords;
	//已选择文字容器
	private ArrayList<WordButton> mBtnSelectWord;
	
	private MyGridView mMyGridView;
	//已选择文字框UI容器
	private LinearLayout mViewWordsContainer;
	//当前这关所对应的歌曲的对象
	private Song mCurrentSong;
	
	//当前关的索引
	private int mCurrentStageIndex =-1;
	//当前金币的数量
	private int mCurrentCoins=Const.TOTAL_COINS;
	
	//金币View
	private TextView mviewCurrentCoins;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//Util.startActivity(MainActivity.this, Welcome.class);
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		int[] datas = Util.loadData(this);
		mCurrentStageIndex = datas[Const.INDEX_LOAD_DATA_STAGE];
		mCurrentCoins = datas[Const.INDEX_LOAD_DATA_CONIS];
		//初始化控件
		ImageButton bt = (ImageButton) findViewById(R.id.btn_bar_back);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		mViewPan=(ImageView) findViewById(R.id.imageView1);
		mViewPanBar=(ImageView) findViewById(R.id.img_pin);
		
		mMyGridView=(MyGridView) findViewById(R.id.gridview);
		mMyGridView.registOnWordButtonClick(this);
		
		mviewCurrentCoins=(TextView) findViewById(R.id.txt_btn_coins);
		mviewCurrentCoins.setText(mCurrentCoins+"");
		//注册
		mViewWordsContainer=(LinearLayout) findViewById(R.id.word_select_container);
		
		
		//初始化动画
		mPanAnim=AnimationUtils.loadAnimation(this, R.anim.rotate);
		mPanlin=new LinearInterpolator();
		mPanAnim.setInterpolator(mPanlin);
		mPanAnim.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				//开启拨杆退出动画
				mViewPanBar.startAnimation(mBarOutAnim);
				
			}
		});
		
		mBarInAnim=AnimationUtils.loadAnimation(this, R.anim.rotete_45);
		mBarInlin=new LinearInterpolator();
		mBarInAnim.setFillAfter(true);
		mBarInAnim.setInterpolator(mBarInlin);
		mBarInAnim.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				mViewPan.startAnimation(mPanAnim);
				
			}
		});
		
		
		mBarOutAnim=AnimationUtils.loadAnimation(this, R.anim.rotate_d_45);
		mBarOutlin=new LinearInterpolator();
		mBarOutAnim.setFillAfter(true);
		mBarOutAnim.setInterpolator(mBarOutlin);
		mBarOutAnim.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				misRunning=false;
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				//显示播放按钮
				misRunning=false;
				mBtnPlayStart.setVisibility(View.VISIBLE);
			}
		});
		
		
		
		mBtnPlayStart=(ImageButton) findViewById(R.id.btn_play_start);
		mBtnPlayStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 handlePlayButton();
			}
		});
		//初始化游戏数据
		initCurrentStageData();
		
		/**
		 * 处理删除按钮
		 */
		handleDeleteWord();
		handleTipWord();
		
	}
	@Override
	public void onWordButtonClick(WordButton wordButton) {
		setSelectWord(wordButton);
		//获得答案状态
		int checkResult = checkTheAnswer();
		//判断答案状态
		if (checkResult==STATUS_ANSWER_RIGHT) {
			//获得相应的奖励，同时过关
			handlePassEvent();
			
		}
		else if (checkResult==STATUS_ANSWER_LACK) {
			//提示答案不完整,文字常规状态设置为白颜色
			for (int i = 0; i < mBtnSelectWord.size(); i++) {
				mBtnSelectWord.get(i).mViewbutton.setTextColor(Color.WHITE);
			}
		}
		else if (checkResult==STATUS_ANSWER_WRONG) {
			//提示答案错误（闪烁）
			sparkTheWords();
			
		}
//		Toast.makeText(this, "aaa"+mBtnSelectWord.size(), Toast.LENGTH_LONG).show();
	}
	/**
	 * 处理过关界面及事件
	 */
	private void handlePassEvent(){
		//显示过关界面
		mPassView=findViewById(R.id.pass_view);
		mPassView.setVisibility(View.VISIBLE);
		
		//停止为未完成的动画
		mViewPan.clearAnimation();
		//停止正在播放的声音
		MyPlayer.stopTheSong(MainActivityto.this);
		
		//播放音效
		MyPlayer.playTong(MainActivityto.this, MyPlayer.INDEX_STONE_COIN);
		//当前关的索引
		mCurrentStageTextView=(TextView) findViewById(R.id.text_current_stage_pass);
		if (mCurrentStageTextView !=null) {
			mCurrentStageTextView.setText(mCurrentStageIndex+1 + "");
		}
		//显示歌曲的名称
		mCurrentSongNamePassView=(TextView) findViewById(R.id.text_current_song_name_pass);
		if (mCurrentSongNamePassView != null) {
			mCurrentSongNamePassView.setText(mCurrentSong.getSongname());
		}
		
		//下一关按键处理
		ImageButton btnpass =(ImageButton) findViewById(R.id.btn_next);
		btnpass.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (judeAppPassed()) {
					//进入通关界面
					Util.startActivity(MainActivityto.this, AllPassView.class);
				}
				else {
					//开始新一关
					mPassView.setVisibility(View.GONE);
					
					//加载关卡数据
					initCurrentStageData();
				}
				
			}
		});
	}
	private boolean judeAppPassed(){
		//判断当前关的索引的大小
		return (mCurrentStageIndex == Const.SONG_INFO.length - 1);
	}
	/**
	 *  清除答案
	 * @param wordButton
	 */
	private void clearTheAnswer(WordButton wordButton){
		//设置已选框的为不可见
		wordButton.mViewbutton.setText("");
		wordButton.mWordString="";
		wordButton.misVisiable=false;
		//设置待选框变成可见
		setButtonVisiable(mAllWords.get(wordButton.mIndex), View.VISIBLE);
	}
	//设置答案
	private void setSelectWord(WordButton wordButton) {
		for (int i = 0; i < mBtnSelectWord.size(); i++) {
			if (mBtnSelectWord.get(i).mWordString.length() == 0) {
				// 设置答案文字框内容及可见性
				mBtnSelectWord.get(i).mViewbutton.setText(wordButton.mWordString);
				mBtnSelectWord.get(i).misVisiable = true;
				mBtnSelectWord.get(i).mWordString = wordButton.mWordString;
				// 记录索引
				mBtnSelectWord.get(i).mIndex = wordButton.mIndex;
				
				MyLog.d(TAG, mBtnSelectWord.get(i).mIndex + "");
				
				// 设置待选框可见性
				setButtonVisiable(wordButton, View.INVISIBLE);
				
				break;
			}
		}
	}
	/**
	 * 设置待选文字框是否可见
	 * @param button
	 * @param visibility
	 */
	private void setButtonVisiable(WordButton button,int visibility){
		button.mViewbutton.setVisibility(visibility);
		button.misVisiable = (visibility == View.VISIBLE) ? true : false;
		
		MyLog.d(TAG, button.misVisiable + "");
	}
	/**
	 * 处理圆盘中的播放按钮
	 */
	private void handlePlayButton(){
		if (mViewPanBar!=null) {
	
		if (!misRunning) {
			misRunning=true;
			//开始拨杆开始动画
			mViewPanBar.startAnimation(mBarInAnim);
			//隐藏播放按钮
			mBtnPlayStart.setVisibility(View.INVISIBLE);
			
			//播放音乐
			MyPlayer.playSong(MainActivityto.this, mCurrentSong.getSongFileName());
				}		
			}
		}
	/**
	 * 读取当前关歌曲信息的方法
	 * @param stageindex
	 * @return
	 */
	private Song LoadStageSongInfo(int stageindex){
		Song song=new Song();
		String[] stage =Const.SONG_INFO[stageindex];
		song.setSongFileName(stage[Const.INDEX_FILE_NAME]);
		song.setSongname(stage[Const.INDEX_SONG_NAME]);
		
		
		return song;
	}
	/**
	 * 加载当前关的数据
	 */
	@SuppressLint("NewApi")
	private void initCurrentStageData(){
		//自动播放
		//读取当前关的歌曲信息
		mCurrentSong=LoadStageSongInfo(++mCurrentStageIndex);
		//初始化已选择框
		mBtnSelectWord=initWordSelect();
		
		LayoutParams params=new LayoutParams(140,140);
		//清空原来的答案
		mViewWordsContainer.removeAllViews();
		//增加新的答案框
		for (int i = 0; i < mBtnSelectWord.size(); i++) {
			mViewWordsContainer.addView(mBtnSelectWord.get(i).mViewbutton,params);
			
		}
		//显示当前的索引
		mCurrenStageView = (TextView) findViewById(R.id.text_current_stage);
		if (mCurrenStageView != null) {
			mCurrenStageView.setText(mCurrentStageIndex + 1 +"");
		}
		
		//获得数据
		mAllWords=initAllword();
		//更新数据-MYgridView
		mMyGridView.updateData(mAllWords);
		//一开始就播放音乐
		handlePlayButton();
	}
	/**
	 * 初始化待选文字框
	 * @return
	 */
	private ArrayList<WordButton> initAllword(){
		ArrayList<WordButton> data=new ArrayList<WordButton>();
		//获得所有待选文字
		String[] words=generateWords();
		for (int i = 0; i <MyGridView.COUNTS_WORDS; i++) {
			WordButton button=new WordButton();
			
			button.mWordString=words[i];
			data.add(button);
			
		}
		return data;
	}
	/**
	 * 初始化已选择文字框
	 * @return
	 */
	private ArrayList<WordButton> initWordSelect(){
		ArrayList<WordButton> data =new ArrayList<WordButton>();
		
		for (int i = 0; i < mCurrentSong.getNameLength(); i++) {
			View view=Util.getView(MainActivityto.this, R.layout.self_ui_gridview_item);
			final WordButton holder= new WordButton();
			holder.mViewbutton=(Button)view.findViewById(R.id.item_btn);
			holder.mViewbutton.setTextColor(Color.WHITE);
			holder.mViewbutton.setText("");
			
			
			holder.misVisiable=false;
			holder.mViewbutton.setBackgroundResource(R.drawable.game_wordblank);
			//增加点击事件
			holder.mViewbutton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					clearTheAnswer(holder);
				}
			});
			data.add(holder);
			
		}
		return data;
	}
	/**生成所有待选文字
	 *
	 * @return
	 */
	private String [] generateWords(){
		Random random= new Random();
		String [] word = new String[MyGridView.COUNTS_WORDS];
		//存入歌名
		for (int i = 0; i < mCurrentSong.getNameLength(); i++) {
			word[i]=mCurrentSong.getNameCharacters()[i]+ "";
		}
		//剩下的存储随机生成的文字
		for (int i =  mCurrentSong.getNameLength(); 
				i < MyGridView.COUNTS_WORDS; i++) {
			word[i] =GetRandomChar() + "";
		}
		//打乱歌曲文字顺序：首先从所有的元素中随机选取一个元素与第一个元素交换
		//然后在第二个数据之后再选择一个元素和第二个元素交换，直到循环到最后一个元素。
		//能够保证每个元素在每个位置的概率是固定的
		for (int i = MyGridView.COUNTS_WORDS-1; i >=0; i--) {
			int index= random.nextInt(i+1);//确保出现的数据在0到24之间
			String  buf=word[index];
			word[index]=word[i];//位置置换
			word[i]=buf;
			
		}
		
		return word;
	};
	/**
	 * 生成随机汉字
	 * @return
	 */
	//每个汉字及符号以两个字节来表示。第一个字节称为“高位字节”，第二个字节称为“低位字节”。
	//“高位字节”使用了 0xA1 - 0xF7（把 01 - 87 区的区号加上 0xA0），
	//“低位字节”使用了 0xA1 - 0xFE（把 01 - 94 位的位号加上 0xA0）。 由于一级汉字从 16 区起始，
	//汉字区的“高位字节”的范围是 0xB0 - 0xF7，
	//“低位字节”的范围是 0xA1 - 0xFE，
	//占用的码位是 72 * 94 = 6768。其中有 5 个空位是 D7FA - D7FE。例如“啊”字在大多数程序中，会以两个字节，
	//0xB0（第一个字节）0xA1（第二个字节）储存。（与区位码对比：0xB0 = 0xA0 + 16, 0xA1 = 0xA0 + 1）。
	private char GetRandomChar(){
		String string="";
		int highPos;
		int lowPos;
		Random random=new Random();
		
		highPos=(176 + Math.abs(random.nextInt(39)));//随机高位
		lowPos=(161 + Math.abs(random.nextInt(93)));//随机低位
		
		byte[] b =new byte[2];
		b[0]=(Integer.valueOf(highPos)).byteValue();//把随机出来的高位放入数组中
		b[1]=(Integer.valueOf(lowPos)).byteValue();//把随机出来的低位放入数组中
		try {
			string= new String(b,"GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return string.charAt(0);
	}
	/**
	 * 检测答案的方法
	 * @return
	 */
	private int checkTheAnswer(){
		//先检查长度
		for (int i = 0; i < mBtnSelectWord.size(); i++) {
			//如果有空的，说明答案不完整
			if (mBtnSelectWord.get(i).mWordString.length()==0) {
				return STATUS_ANSWER_LACK;
			}
		}
		//检查答案正确与否
		StringBuffer buff=new StringBuffer();
		for (int i = 0; i < mBtnSelectWord.size(); i++){
			buff.append(mBtnSelectWord.get(i).mWordString);//把数据放入buff
		}
		return (buff.toString().equals(mCurrentSong.getSongname()))
				?STATUS_ANSWER_RIGHT:STATUS_ANSWER_WRONG;
		
		
	}
	/**答案错误闪烁文字
	 * 
	 */
	private void sparkTheWords(){
		//定时器相关 
		TimerTask task = new TimerTask() {
			boolean mChange=false;
			int mSpardTimes=0;
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {
					public void run() {
						//显示闪烁的次数
						if (++mSpardTimes>SPASH_TIMES) {
							return;
						}
						//执行闪烁的逻辑：交替实现红色和白色的文字
						for (int i = 0; i < mBtnSelectWord.size(); i++) {
							mBtnSelectWord.get(i).mViewbutton.setTextColor
							(mChange ? Color.RED : Color.WHITE);
						}
						mChange = ! mChange;
						
					}
				});
				
			}
		};
		//执行定时器
		Timer timer=new Timer();
		timer.schedule(task, 1,150);
	}
	/**
	 * 判断用户当前是否可以减少金币
	 * 
	 * @return 可以：返回true，否则返回false
	 */
	private boolean isAbleDelCoins(int data) {
	    // 判断当前总的金币数量是否可被减少
	    if (mCurrentCoins + data >= 0) {
	        return true;
	    } else {
	        return false;
	    }
	}
	/**
	 * 自动选择一个答案
	 */
	private void tipAnswer(){
		  if(!isAbleDelCoins(-getTipCoins())){
		        //金币数量不够，提示错误并且返回
		       showConfigfirmDialog(ID_DIALOG_LACK_COINS);
		        return;
		    }
		boolean tipWord = false;
		for (int i = 0; i < mBtnSelectWord.size(); i++) {
			if (mBtnSelectWord.get(i).mWordString.length() == 0) {
				//根据当前的答案框的条件选择对应的文字填入
				onWordButtonClick(findIsAnswerWord(i));
				tipWord=true;
				//减少金币数量
				if (!handlecoins(-getTipCoins())) {
					//金币不够，显示对话框
					return;
				}
				break;
			}
		}
		if (!tipWord) {
			//闪烁文字提示用户
			sparkTheWords();
		}
	}
	/**
	 * 删除文字
	 */
	private void delateOneWord(){
		//减少金币
		if (!handlecoins(-getDeleteCoins())) {
			//金币不够，显示对话框
			showConfigfirmDialog(ID_DIALOG_LACK_COINS);
			return;
		}
		//将这个索引对应的待选文字框设置为不可见
		setButtonVisiable(findNotAnswerWord(),View.INVISIBLE);
	}
	/**
	 * 找到不是答案的文字，并且当前是可见的
	 * @return
	 */
	private WordButton findNotAnswerWord(){
		Random random = new Random();
		WordButton buf= null;
		
		while(true){
			int index = random.nextInt(MyGridView.COUNTS_WORDS);
			buf = mAllWords.get(index);
			if (buf.misVisiable && !isTheAnswerWord(buf) ) {
				return buf;
			}
		}
	}
	/**
	 * 找到一个答案的文字
	 * @param index 当前需要填入的答案框的索引
	 * @return 
	 * 
	 */
	private WordButton findIsAnswerWord(int index){
		//从当前歌曲中取出一个即可
		WordButton buf = null;
		for (int i = 0; i < MyGridView.COUNTS_WORDS; i++) {
			buf = mAllWords.get(i);
			if (buf.mWordString.equals(""+mCurrentSong.getNameCharacters()[index])) {
				return buf;
			}
		}
		return null;
	}
	/**
	 * 找到是答案的文字
	 */
	/**
	 * 判断某个文字是否为答案
	 * @param wordButton
	 * @return
	 */
	private boolean isTheAnswerWord(WordButton wordButton){
		boolean result = false;
		for (int i = 0; i < mCurrentSong.getNameLength(); i++) {
			if (wordButton.mWordString.equals
					(""+mCurrentSong.getNameCharacters()[i])) {
				result= true;
				break;
			}
		}
		return result;
	}
	/**
	 * 增加或者减少指定数量的金币
	 * @param data
	 * @return true 增加或减少成功 false 失败
	 * 
	 */
	private  boolean handlecoins(int data){
		//判断当前总的金币数量是否会被减少
		if (mCurrentCoins + data >= 0) {
			mCurrentCoins += data;
			
			mviewCurrentCoins.setText(mCurrentCoins +"");
			return true;
		}
		else {
			//金币不够
			return false;
		}
		
	}
	/**
	 * 获取删除的金币常量
	 * @return
	 */
	private int  getDeleteCoins(){
		return this.getResources().getInteger(R.integer.pay_delete_word);
	}
	/**
	 * 从资源文件中获取提示的金币数量
	 * @return
	 */
	private int  getTipCoins(){
		return this.getResources().getInteger(R.integer.pay_tip_answer);
	}
	/**
	 * 处理删除待选事件
	 */
	public void handleDeleteWord(){
		ImageButton button = (ImageButton) findViewById(R.id.Btn_delete);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				delateOneWord();
				showConfigfirmDialog(ID_DIALOG_DELETE_WORD);
			}
		});
	}
	/**
	 * 处理提示的事件
	 */
	private void handleTipWord(){
		ImageButton button = (ImageButton) findViewById(R.id.Btn_tip);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				tipAnswer();
				showConfigfirmDialog(ID_DIALOG_TIP_ANSWER);
			}
		});
	}
	/**
	 * 自定义AlertDialog事件相应
	 */
	//删除错误答案
	private IAlertDialogButtonListener mBtnOkDeleteWordListener = 
			new IAlertDialogButtonListener(){
		@Override
		public void onClick() {
			//执行事件
			delateOneWord();
		};
	};
	//答案提示
	private IAlertDialogButtonListener mBtnOkTipAnswerListener = 
			new IAlertDialogButtonListener(){
		@Override
		public void onClick() {
			//执行事件
			tipAnswer();
		};
	};
	//金币不足
	private IAlertDialogButtonListener mBtnOkLackcoinsListener = 
			new IAlertDialogButtonListener(){
		@Override
		public void onClick() {
			//执行事件
			//加qq919161460
		};
	};
	private void showConfigfirmDialog(int id){
		switch (id) {
			case ID_DIALOG_DELETE_WORD:
				Util.showDialog(MainActivityto.this, "确认花掉"+ getDeleteCoins()+ "个金币去掉一个错误答案?", mBtnOkDeleteWordListener);
			break;

			case ID_DIALOG_TIP_ANSWER :
				Util.showDialog(MainActivityto.this, "确认花掉"+ getTipCoins()+"个金币提示一个答案?", mBtnOkTipAnswerListener);
			break;
			case ID_DIALOG_LACK_COINS:
				Util.showDialog(MainActivityto.this,"金币不足，请联系作者！",mBtnOkLackcoinsListener);
			break;
		}
	}
	@Override
	public void onPasue() {
		// TODO Auto-generated method stub
//		//保存游戏数据
		Util.saveData(MainActivityto.this, -1, 888);
		mViewPan.clearAnimation();
		
		//暂停音乐
		MyPlayer.stopTheSong(MainActivityto.this);
		super.onPause();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Util.saveData(MainActivityto.this, -1, 888);
		mViewPan.clearAnimation();
		
		//暂停音乐
		MyPlayer.stopTheSong(MainActivityto.this);
		super.onDestroy();
	}
//	private void startWelcome(){
//		new Handler().postDelayed(r, 1000);	
//		}
//	Runnable r = new Runnable() {
//		
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			Intent intent = new Intent();
//			intent.setClass(MainActivity.this,Welcome.class);
//			startActivity(intent);
//			finish();
//		}
//	};
}


