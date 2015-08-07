package com.qingkong.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.qingkong.bean.textbean;

public class textcha {

	public textcha(Context con) {
		iotext io = new iotext(con);
		io.du();
	}

	public List cha(String qidian, String zhogndian) {
		List<String> lis = new ArrayList<String>();
		for (textbean b : iotext.lists) {
			String[] zhan = b.getZhanming().split(",");
			for (int i = 0; i < zhan.length; i++) {
				if (zhan[i].contains(qidian)) {
					for (int j = i; j < zhan.length; j++) {
						if (zhan[j].contains(zhogndian)) {

							StringBuffer sb = new StringBuffer();
							for (; i <= j; i++) {
								sb.append(zhan[i] + "\n");
							}
							// System.out.println(b.getXianlu()+"|"+sb.toString());
							lis.add(b.getXianlu() + "\n" + sb.toString());
						}
					}
				}
			}
		}
		if (lis.size() > 0) {

		} else {
			List s=	huanyi(qidian, zhogndian);
			if(s.size()>0)
				return s;
			
		}
		if (lis.size() > 0) {

		} else {
			lis.add("没有找到您的线路，请尝试更新数据库");
		}
		return lis;
	}

	public String xiancha(String c) {
		for (textbean b : iotext.lists) {
			if (b.getXianlu().contains(c)) {
				String[] s = b.getZhanming().split(",");
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < s.length; i++) {
					sb.append(s[i] + "\n");
				}
				return b.getXianlu() + "#" + "\n" + sb.toString();
			}
		}
		return "没有找到线路";
	}

	public List huanyi(String qidian, String zhogndian) {
		List<String> lis = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		for (textbean bb : iotext.lists) {
			String[] zhan = bb.getZhanming().split(",");
			for (int i = 0; i < zhan.length; i++) {
				if (zhan[i].contains(qidian)) {
					for (textbean cc : iotext.lists) {
						String[] zhong = cc.getZhanming().split(",");
						for (int j = 0; j < zhong.length; j++) {
							if (zhong[j].contains(zhogndian)) {
								for (int z = i; z < zhan.length; z++) {
									for (textbean dd : iotext.lists) {
										String[] zhan2 = dd.getZhanming()
												.split(",");
										for (int x = 0; x < zhan2.length; x++) {
											if (zhan2[x].contains(qidian)) {
												for (textbean ee : iotext.lists) {
													String[] zhong2 = ee
															.getZhanming()
															.split(",");
													for (int y = 0; y < zhong2.length; y++) {
														if (zhong2[y]
																.contains(zhogndian)) {
															System.out
																	.println(x+"--"+y);
															if(x>y)
															for(int l =x;l>y;l--){
																sb.append(zhan2[l]+"\n");
															}
															for(int s =x;s<y;s++){
																sb.append(zhan2[s]+"\n");
															}
															//方法
															List s =hehe(zhong[2], zhogndian);
															for(int k =0;k<s.size();k++){
																
																lis.add(dd.getXianlu()+"\n"
																	+sb.toString()+zhong[2]+"--站--换乘"+"\n"+s.get(k).toString());
															}
															return lis;
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		lis.add("没有找到您的线路，请尝试更新数据库");
		return lis;

	}
	public List hehe(String qidian,String zhogndian){
		List<String> lis = new ArrayList<String>();
		for (textbean b : iotext.lists) {
			String[] zhan = b.getZhanming().split(",");
			for (int i = 0; i < zhan.length; i++) {
				if (zhan[i].contains(qidian)) {
					for (int j = i; j < zhan.length; j++) {
						if (zhan[j].contains(zhogndian)) {

							StringBuffer sb = new StringBuffer();
							for (; i <= j; i++) {
								sb.append(zhan[i] + "\n");
							}
							// System.out.println(b.getXianlu()+"|"+sb.toString());
							lis.add(b.getXianlu() + "\n" + sb.toString());
						}
						
					}
				}
			}
		}
		return lis;
	}

}
