package com.qingkong.Activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.qingkong.fragment.BaseFragment;
import com.qingkong.fragment.numBer;
import com.qingkong.fragment.seTing;
import com.qingkong.fragment.staTion;
import com.qingkong.fragment.stationTo;
import com.qingkong.transit.R;

public class MainActivity extends FragmentActivity {

	private ViewPager mPager;
	private List<Fragment> mlists = new ArrayList<>();
	private FragmentPagerAdapter fpa;
	private RadioGroup ragdioGroup;
	private RadioButton rh_yidiancai, rb_diancai, rb_lishidiancai, rb_qita;
	private LinearLayout mLinearLayout;
	BaseFragment number,seting,station,stationto;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_activity);
		initdata();
		iniaview();
	}
	private void iniaview() {
		mPager = (ViewPager) findViewById(R.id.mainViewpager);
		ragdioGroup = (RadioGroup) findViewById(R.id.rg_group);
		rb_diancai = (RadioButton) findViewById(R.id.rb_diancai);
		rb_lishidiancai = (RadioButton) findViewById(R.id.rb_lishidingdan);
		rh_yidiancai = (RadioButton) findViewById(R.id.rb_yidiancai);
		rb_qita = (RadioButton) findViewById(R.id.rb_qita);
		mLinearLayout = (LinearLayout) findViewById(R.id.mLinearLayout);
		fpa = new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public Object instantiateItem(ViewGroup container, int position) {

				return super.instantiateItem(container, position);
			}

			@Override
			public int getCount() {
				return mlists.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				return mlists.get(arg0);
			}

		};
		//设置适配器
		mPager.setAdapter(fpa);
		//防止Fragment自动销毁
		mPager.setOffscreenPageLimit(4);
		//默认点击第一个RadioButton
		rb_diancai.setChecked(true);
		//设置RadioButton的点击事件
		ragdioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				//进行viewpager的页面切换逻辑处理
				switch (checkedId) {
				case R.id.rb_diancai:
					mPager.setCurrentItem(0);
					break;
				case R.id.rb_yidiancai:
					mPager.setCurrentItem(2);
					break;
				case R.id.rb_lishidingdan:
					mPager.setCurrentItem(1);
					break;
				case R.id.rb_qita:
					mPager.setCurrentItem(3);
					break;
				default:
					break;
				}
			}
		});
		//监听viewpager的页面滑动处理，然后设置按钮点击事件
		mPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
				case 0:
					rb_diancai.setChecked(true);
					break;
				case 1:
					rb_lishidiancai.setChecked(true);
					break;
				case 2:
					rh_yidiancai.setChecked(true);
					break;
				case 3:
					rb_qita.setChecked(true);
					break;
				default:
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}
	private void initdata() {
		number = new numBer();
		seting = new seTing();
		station = new staTion();
		stationto = new stationTo();
		mlists.add(stationto);
		mlists.add(number);
		mlists.add(station);
		mlists.add(seting);
		
	}
}
