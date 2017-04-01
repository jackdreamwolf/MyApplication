package com.sec.mp.myapplication.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/** ����http����Ĺ����� */
public class HttpUtils {

	/**
	 * ����get���� ��ȡ���ص���Ӧ������  
	 * @param path  ������Դ·��
	 * @return
	 * @throws Exception
	 */
	public static InputStream get(String path) throws Exception{
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		InputStream is = conn.getInputStream();
		return is;
	}
	
	/**
	 * ͨ������������  ��ȡһ���ַ���
	 * @param is
	 * @return
	 * @throws IOException 
	 */
	public static String isToString(InputStream is) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = "";
		while((line=reader.readLine())!=null){
			sb.append(line+"\n");
		}
		return sb.toString();
	}
	
}




