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
		// ��ȡ��ǰActivity ��������ʹ��
		mActivity = getActivity();
		// ���������дinitview����
		View view = initview();

		return view;
	}

	public abstract View initview();

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// �������ݳ�ʼ�����Ը�д�˷���
		initdata();
		super.onActivityCreated(savedInstanceState);
	}

	// ����ѡ��д����
	public void initdata() {

	}
}
