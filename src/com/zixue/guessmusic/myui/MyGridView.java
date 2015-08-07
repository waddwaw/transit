package com.zixue.guessmusic.myui;

import java.util.ArrayList;


import com.qingkong.transit.R;
import com.zixue.crazyguessmusic.model.IWordButtonClickListenler;
import com.zixue.crazyguessmusic.model.WordButton;
import com.zixue.util.Util;

import android.R.integer;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

public class MyGridView extends GridView{
	public final static int COUNTS_WORDS=24;//��������
	
	private Animation mscaleAnimation;//����
	
	private ArrayList<WordButton> mArrayList= new ArrayList<WordButton>();//�洢�ļ�����
	
	private MyGridAdapter mAdapter;
	
	private Context mContext;
	//����һ�����buttonʵ�ֵķ���
	private IWordButtonClickListenler mIWordButtonClickListenler;
	
	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext=context;
		mAdapter=new MyGridAdapter();
		this.setAdapter(mAdapter);
	}
	public void  updateData(ArrayList<WordButton> list){
		mArrayList=list;
		//������������Դ
		setAdapter(mAdapter);
	}
	class MyGridAdapter extends BaseAdapter{
		public int getCount(){
			return mArrayList.size();
		}
		public Object getItem(int pos){
			return mArrayList.get(pos);
			
		}
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public View getView(int pos, View v, ViewGroup p) {
			
			final WordButton holder;
			
			if (v ==null) {
				v =(View) Util.getView(mContext,R.layout.self_ui_gridview_item);
				holder=mArrayList.get (pos);
				//���ض���
				mscaleAnimation=AnimationUtils.loadAnimation(mContext, R.anim.scale);
				//Ϊ���������ӳ�ʱ��
				mscaleAnimation.setStartOffset(pos * 100);
				holder.mIndex=pos;
				if(holder.mViewbutton==null){
				holder.mViewbutton=(Button)v.findViewById(R.id.item_btn);
				holder.mViewbutton.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						mIWordButtonClickListenler.onWordButtonClick(holder);
					}
				});
				}
				v.setTag(holder);
			}
			else {
				holder=(WordButton) v.getTag();
			}
			holder.mViewbutton.setText(holder.mWordString );
			//���Ŷ���
			v.startAnimation(mscaleAnimation);
			return v;
		}
	}
	/**
	 * ע������ӿ�
	 * ʵ����MyGridView�е����ť����MainAcitivity����
	 * @param listenler
	 */
	public void registOnWordButtonClick(IWordButtonClickListenler listenler){
		mIWordButtonClickListenler=listenler;
		
	}

}
