package com.qingkong.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
	public Activity mActivity;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// 获取当前Activity 方便子类使用
		mActivity = getActivity();
		// 子类必须重写initview方法
		View view = initview();

		return view;
	}

	public abstract View initview();

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// 如有数据初始化可以复写此方法
		initdata();
		super.onActivityCreated(savedInstanceState);
	}

	// 子类选择复写方法
	public void initdata() {

	}
}
