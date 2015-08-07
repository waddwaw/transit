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
	/** ��״̬-- ��ȷ*/
	public final static int STATUS_ANSWER_RIGHT = 1;
	/** ��״̬-- ����*/
	public final static int STATUS_ANSWER_WRONG = 2;
	/** ��״̬-- ������*/
	public final static int STATUS_ANSWER_LACK = 3;
	
	//��˸����
	public final static int SPASH_TIMES=6;
	
	public final int ID_DIALOG_DELETE_WORD = 1;
	
	public final int ID_DIALOG_TIP_ANSWER = 2;
	
	public final int ID_DIALOG_LACK_COINS = 3;
	//��Ƭ��ض���
	private Animation mPanAnim;
	private LinearInterpolator mPanlin;//�����˶��ٶ�
	
	private Animation mBarInAnim;
	private LinearInterpolator mBarInlin;
	
	private Animation mBarOutAnim;
	private LinearInterpolator mBarOutlin;
	
	private ImageView mViewPan;
	
	private ImageView mViewPanBar;
	//��ǰ�ص�����	
	private TextView mCurrentStageTextView;
	
	private TextView mCurrenStageView; 
	
	//��ǰ����������
	private TextView mCurrentSongNamePassView;
	//play�����¼�
	private ImageButton mBtnPlayStart;
	
	//���ؽ���
	private View mPassView;
	
	/**
	 * ���������Ƭ�Ƿ���������
	 */
	private boolean misRunning=false;
	//���ֿ�����
	//��ѡ����������
	private ArrayList<WordButton> mAllWords;
	//��ѡ����������
	private ArrayList<WordButton> mBtnSelectWord;
	
	private MyGridView mMyGridView;
	//��ѡ�����ֿ�UI����
	private LinearLayout mViewWordsContainer;
	//��ǰ�������Ӧ�ĸ����Ķ���
	private Song mCurrentSong;
	
	//��ǰ�ص�����
	private int mCurrentStageIndex =-1;
	//��ǰ��ҵ�����
	private int mCurrentCoins=Const.TOTAL_COINS;
	
	//���View
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
		//��ʼ���ؼ�
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
		//ע��
		mViewWordsContainer=(LinearLayout) findViewById(R.id.word_select_container);
		
		
		//��ʼ������
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
				//���������˳�����
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
				//��ʾ���Ű�ť
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
		//��ʼ����Ϸ����
		initCurrentStageData();
		
		/**
		 * ����ɾ����ť
		 */
		handleDeleteWord();
		handleTipWord();
		
	}
	@Override
	public void onWordButtonClick(WordButton wordButton) {
		setSelectWord(wordButton);
		//��ô�״̬
		int checkResult = checkTheAnswer();
		//�жϴ�״̬
		if (checkResult==STATUS_ANSWER_RIGHT) {
			//�����Ӧ�Ľ�����ͬʱ����
			handlePassEvent();
			
		}
		else if (checkResult==STATUS_ANSWER_LACK) {
			//��ʾ�𰸲�����,���ֳ���״̬����Ϊ����ɫ
			for (int i = 0; i < mBtnSelectWord.size(); i++) {
				mBtnSelectWord.get(i).mViewbutton.setTextColor(Color.WHITE);
			}
		}
		else if (checkResult==STATUS_ANSWER_WRONG) {
			//��ʾ�𰸴�����˸��
			sparkTheWords();
			
		}
//		Toast.makeText(this, "aaa"+mBtnSelectWord.size(), Toast.LENGTH_LONG).show();
	}
	/**
	 * ������ؽ��漰�¼�
	 */
	private void handlePassEvent(){
		//��ʾ���ؽ���
		mPassView=findViewById(R.id.pass_view);
		mPassView.setVisibility(View.VISIBLE);
		
		//ֹͣΪδ��ɵĶ���
		mViewPan.clearAnimation();
		//ֹͣ���ڲ��ŵ�����
		MyPlayer.stopTheSong(MainActivityto.this);
		
		//������Ч
		MyPlayer.playTong(MainActivityto.this, MyPlayer.INDEX_STONE_COIN);
		//��ǰ�ص�����
		mCurrentStageTextView=(TextView) findViewById(R.id.text_current_stage_pass);
		if (mCurrentStageTextView !=null) {
			mCurrentStageTextView.setText(mCurrentStageIndex+1 + "");
		}
		//��ʾ����������
		mCurrentSongNamePassView=(TextView) findViewById(R.id.text_current_song_name_pass);
		if (mCurrentSongNamePassView != null) {
			mCurrentSongNamePassView.setText(mCurrentSong.getSongname());
		}
		
		//��һ�ذ�������
		ImageButton btnpass =(ImageButton) findViewById(R.id.btn_next);
		btnpass.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (judeAppPassed()) {
					//����ͨ�ؽ���
					Util.startActivity(MainActivityto.this, AllPassView.class);
				}
				else {
					//��ʼ��һ��
					mPassView.setVisibility(View.GONE);
					
					//���عؿ�����
					initCurrentStageData();
				}
				
			}
		});
	}
	private boolean judeAppPassed(){
		//�жϵ�ǰ�ص������Ĵ�С
		return (mCurrentStageIndex == Const.SONG_INFO.length - 1);
	}
	/**
	 *  �����
	 * @param wordButton
	 */
	private void clearTheAnswer(WordButton wordButton){
		//������ѡ���Ϊ���ɼ�
		wordButton.mViewbutton.setText("");
		wordButton.mWordString="";
		wordButton.misVisiable=false;
		//���ô�ѡ���ɿɼ�
		setButtonVisiable(mAllWords.get(wordButton.mIndex), View.VISIBLE);
	}
	//���ô�
	private void setSelectWord(WordButton wordButton) {
		for (int i = 0; i < mBtnSelectWord.size(); i++) {
			if (mBtnSelectWord.get(i).mWordString.length() == 0) {
				// ���ô����ֿ����ݼ��ɼ���
				mBtnSelectWord.get(i).mViewbutton.setText(wordButton.mWordString);
				mBtnSelectWord.get(i).misVisiable = true;
				mBtnSelectWord.get(i).mWordString = wordButton.mWordString;
				// ��¼����
				mBtnSelectWord.get(i).mIndex = wordButton.mIndex;
				
				MyLog.d(TAG, mBtnSelectWord.get(i).mIndex + "");
				
				// ���ô�ѡ��ɼ���
				setButtonVisiable(wordButton, View.INVISIBLE);
				
				break;
			}
		}
	}
	/**
	 * ���ô�ѡ���ֿ��Ƿ�ɼ�
	 * @param button
	 * @param visibility
	 */
	private void setButtonVisiable(WordButton button,int visibility){
		button.mViewbutton.setVisibility(visibility);
		button.misVisiable = (visibility == View.VISIBLE) ? true : false;
		
		MyLog.d(TAG, button.misVisiable + "");
	}
	/**
	 * ����Բ���еĲ��Ű�ť
	 */
	private void handlePlayButton(){
		if (mViewPanBar!=null) {
	
		if (!misRunning) {
			misRunning=true;
			//��ʼ���˿�ʼ����
			mViewPanBar.startAnimation(mBarInAnim);
			//���ز��Ű�ť
			mBtnPlayStart.setVisibility(View.INVISIBLE);
			
			//��������
			MyPlayer.playSong(MainActivityto.this, mCurrentSong.getSongFileName());
				}		
			}
		}
	/**
	 * ��ȡ��ǰ�ظ�����Ϣ�ķ���
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
	 * ���ص�ǰ�ص�����
	 */
	@SuppressLint("NewApi")
	private void initCurrentStageData(){
		//�Զ�����
		//��ȡ��ǰ�صĸ�����Ϣ
		mCurrentSong=LoadStageSongInfo(++mCurrentStageIndex);
		//��ʼ����ѡ���
		mBtnSelectWord=initWordSelect();
		
		LayoutParams params=new LayoutParams(140,140);
		//���ԭ���Ĵ�
		mViewWordsContainer.removeAllViews();
		//�����µĴ𰸿�
		for (int i = 0; i < mBtnSelectWord.size(); i++) {
			mViewWordsContainer.addView(mBtnSelectWord.get(i).mViewbutton,params);
			
		}
		//��ʾ��ǰ������
		mCurrenStageView = (TextView) findViewById(R.id.text_current_stage);
		if (mCurrenStageView != null) {
			mCurrenStageView.setText(mCurrentStageIndex + 1 +"");
		}
		
		//�������
		mAllWords=initAllword();
		//��������-MYgridView
		mMyGridView.updateData(mAllWords);
		//һ��ʼ�Ͳ�������
		handlePlayButton();
	}
	/**
	 * ��ʼ����ѡ���ֿ�
	 * @return
	 */
	private ArrayList<WordButton> initAllword(){
		ArrayList<WordButton> data=new ArrayList<WordButton>();
		//������д�ѡ����
		String[] words=generateWords();
		for (int i = 0; i <MyGridView.COUNTS_WORDS; i++) {
			WordButton button=new WordButton();
			
			button.mWordString=words[i];
			data.add(button);
			
		}
		return data;
	}
	/**
	 * ��ʼ����ѡ�����ֿ�
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
			//���ӵ���¼�
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
	/**�������д�ѡ����
	 *
	 * @return
	 */
	private String [] generateWords(){
		Random random= new Random();
		String [] word = new String[MyGridView.COUNTS_WORDS];
		//�������
		for (int i = 0; i < mCurrentSong.getNameLength(); i++) {
			word[i]=mCurrentSong.getNameCharacters()[i]+ "";
		}
		//ʣ�µĴ洢������ɵ�����
		for (int i =  mCurrentSong.getNameLength(); 
				i < MyGridView.COUNTS_WORDS; i++) {
			word[i] =GetRandomChar() + "";
		}
		//���Ҹ�������˳�����ȴ����е�Ԫ�������ѡȡһ��Ԫ�����һ��Ԫ�ؽ���
		//Ȼ���ڵڶ�������֮����ѡ��һ��Ԫ�غ͵ڶ���Ԫ�ؽ�����ֱ��ѭ�������һ��Ԫ�ء�
		//�ܹ���֤ÿ��Ԫ����ÿ��λ�õĸ����ǹ̶���
		for (int i = MyGridView.COUNTS_WORDS-1; i >=0; i--) {
			int index= random.nextInt(i+1);//ȷ�����ֵ�������0��24֮��
			String  buf=word[index];
			word[index]=word[i];//λ���û�
			word[i]=buf;
			
		}
		
		return word;
	};
	/**
	 * �����������
	 * @return
	 */
	//ÿ�����ּ������������ֽ�����ʾ����һ���ֽڳ�Ϊ����λ�ֽڡ����ڶ����ֽڳ�Ϊ����λ�ֽڡ���
	//����λ�ֽڡ�ʹ���� 0xA1 - 0xF7���� 01 - 87 �������ż��� 0xA0����
	//����λ�ֽڡ�ʹ���� 0xA1 - 0xFE���� 01 - 94 λ��λ�ż��� 0xA0���� ����һ�����ִ� 16 ����ʼ��
	//�������ġ���λ�ֽڡ��ķ�Χ�� 0xB0 - 0xF7��
	//����λ�ֽڡ��ķ�Χ�� 0xA1 - 0xFE��
	//ռ�õ���λ�� 72 * 94 = 6768�������� 5 ����λ�� D7FA - D7FE�����硰�������ڴ���������У����������ֽڣ�
	//0xB0����һ���ֽڣ�0xA1���ڶ����ֽڣ����档������λ��Աȣ�0xB0 = 0xA0 + 16, 0xA1 = 0xA0 + 1����
	private char GetRandomChar(){
		String string="";
		int highPos;
		int lowPos;
		Random random=new Random();
		
		highPos=(176 + Math.abs(random.nextInt(39)));//�����λ
		lowPos=(161 + Math.abs(random.nextInt(93)));//�����λ
		
		byte[] b =new byte[2];
		b[0]=(Integer.valueOf(highPos)).byteValue();//����������ĸ�λ����������
		b[1]=(Integer.valueOf(lowPos)).byteValue();//����������ĵ�λ����������
		try {
			string= new String(b,"GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return string.charAt(0);
	}
	/**
	 * ���𰸵ķ���
	 * @return
	 */
	private int checkTheAnswer(){
		//�ȼ�鳤��
		for (int i = 0; i < mBtnSelectWord.size(); i++) {
			//����пյģ�˵���𰸲�����
			if (mBtnSelectWord.get(i).mWordString.length()==0) {
				return STATUS_ANSWER_LACK;
			}
		}
		//������ȷ���
		StringBuffer buff=new StringBuffer();
		for (int i = 0; i < mBtnSelectWord.size(); i++){
			buff.append(mBtnSelectWord.get(i).mWordString);//�����ݷ���buff
		}
		return (buff.toString().equals(mCurrentSong.getSongname()))
				?STATUS_ANSWER_RIGHT:STATUS_ANSWER_WRONG;
		
		
	}
	/**�𰸴�����˸����
	 * 
	 */
	private void sparkTheWords(){
		//��ʱ����� 
		TimerTask task = new TimerTask() {
			boolean mChange=false;
			int mSpardTimes=0;
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {
					public void run() {
						//��ʾ��˸�Ĵ���
						if (++mSpardTimes>SPASH_TIMES) {
							return;
						}
						//ִ����˸���߼�������ʵ�ֺ�ɫ�Ͱ�ɫ������
						for (int i = 0; i < mBtnSelectWord.size(); i++) {
							mBtnSelectWord.get(i).mViewbutton.setTextColor
							(mChange ? Color.RED : Color.WHITE);
						}
						mChange = ! mChange;
						
					}
				});
				
			}
		};
		//ִ�ж�ʱ��
		Timer timer=new Timer();
		timer.schedule(task, 1,150);
	}
	/**
	 * �ж��û���ǰ�Ƿ���Լ��ٽ��
	 * 
	 * @return ���ԣ�����true�����򷵻�false
	 */
	private boolean isAbleDelCoins(int data) {
	    // �жϵ�ǰ�ܵĽ�������Ƿ�ɱ�����
	    if (mCurrentCoins + data >= 0) {
	        return true;
	    } else {
	        return false;
	    }
	}
	/**
	 * �Զ�ѡ��һ����
	 */
	private void tipAnswer(){
		  if(!isAbleDelCoins(-getTipCoins())){
		        //���������������ʾ�����ҷ���
		       showConfigfirmDialog(ID_DIALOG_LACK_COINS);
		        return;
		    }
		boolean tipWord = false;
		for (int i = 0; i < mBtnSelectWord.size(); i++) {
			if (mBtnSelectWord.get(i).mWordString.length() == 0) {
				//���ݵ�ǰ�Ĵ𰸿������ѡ���Ӧ����������
				onWordButtonClick(findIsAnswerWord(i));
				tipWord=true;
				//���ٽ������
				if (!handlecoins(-getTipCoins())) {
					//��Ҳ�������ʾ�Ի���
					return;
				}
				break;
			}
		}
		if (!tipWord) {
			//��˸������ʾ�û�
			sparkTheWords();
		}
	}
	/**
	 * ɾ������
	 */
	private void delateOneWord(){
		//���ٽ��
		if (!handlecoins(-getDeleteCoins())) {
			//��Ҳ�������ʾ�Ի���
			showConfigfirmDialog(ID_DIALOG_LACK_COINS);
			return;
		}
		//�����������Ӧ�Ĵ�ѡ���ֿ�����Ϊ���ɼ�
		setButtonVisiable(findNotAnswerWord(),View.INVISIBLE);
	}
	/**
	 * �ҵ����Ǵ𰸵����֣����ҵ�ǰ�ǿɼ���
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
	 * �ҵ�һ���𰸵�����
	 * @param index ��ǰ��Ҫ����Ĵ𰸿������
	 * @return 
	 * 
	 */
	private WordButton findIsAnswerWord(int index){
		//�ӵ�ǰ������ȡ��һ������
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
	 * �ҵ��Ǵ𰸵�����
	 */
	/**
	 * �ж�ĳ�������Ƿ�Ϊ��
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
	 * ���ӻ��߼���ָ�������Ľ��
	 * @param data
	 * @return true ���ӻ���ٳɹ� false ʧ��
	 * 
	 */
	private  boolean handlecoins(int data){
		//�жϵ�ǰ�ܵĽ�������Ƿ�ᱻ����
		if (mCurrentCoins + data >= 0) {
			mCurrentCoins += data;
			
			mviewCurrentCoins.setText(mCurrentCoins +"");
			return true;
		}
		else {
			//��Ҳ���
			return false;
		}
		
	}
	/**
	 * ��ȡɾ���Ľ�ҳ���
	 * @return
	 */
	private int  getDeleteCoins(){
		return this.getResources().getInteger(R.integer.pay_delete_word);
	}
	/**
	 * ����Դ�ļ��л�ȡ��ʾ�Ľ������
	 * @return
	 */
	private int  getTipCoins(){
		return this.getResources().getInteger(R.integer.pay_tip_answer);
	}
	/**
	 * ����ɾ����ѡ�¼�
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
	 * ������ʾ���¼�
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
	 * �Զ���AlertDialog�¼���Ӧ
	 */
	//ɾ�������
	private IAlertDialogButtonListener mBtnOkDeleteWordListener = 
			new IAlertDialogButtonListener(){
		@Override
		public void onClick() {
			//ִ���¼�
			delateOneWord();
		};
	};
	//����ʾ
	private IAlertDialogButtonListener mBtnOkTipAnswerListener = 
			new IAlertDialogButtonListener(){
		@Override
		public void onClick() {
			//ִ���¼�
			tipAnswer();
		};
	};
	//��Ҳ���
	private IAlertDialogButtonListener mBtnOkLackcoinsListener = 
			new IAlertDialogButtonListener(){
		@Override
		public void onClick() {
			//ִ���¼�
			//��qq919161460
		};
	};
	private void showConfigfirmDialog(int id){
		switch (id) {
			case ID_DIALOG_DELETE_WORD:
				Util.showDialog(MainActivityto.this, "ȷ�ϻ���"+ getDeleteCoins()+ "�����ȥ��һ�������?", mBtnOkDeleteWordListener);
			break;

			case ID_DIALOG_TIP_ANSWER :
				Util.showDialog(MainActivityto.this, "ȷ�ϻ���"+ getTipCoins()+"�������ʾһ����?", mBtnOkTipAnswerListener);
			break;
			case ID_DIALOG_LACK_COINS:
				Util.showDialog(MainActivityto.this,"��Ҳ��㣬����ϵ���ߣ�",mBtnOkLackcoinsListener);
			break;
		}
	}
	@Override
	public void onPasue() {
		// TODO Auto-generated method stub
//		//������Ϸ����
		Util.saveData(MainActivityto.this, -1, 888);
		mViewPan.clearAnimation();
		
		//��ͣ����
		MyPlayer.stopTheSong(MainActivityto.this);
		super.onPause();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Util.saveData(MainActivityto.this, -1, 888);
		mViewPan.clearAnimation();
		
		//��ͣ����
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


