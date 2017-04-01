package com.sec.mp.myapplication.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private static SimpleDateFormat SDF = new SimpleDateFormat("mm:ss");
	private static Date TEMP_DATE = new Date();
	
	public static String parseTime(int time){
		TEMP_DATE.setTime(time);
		return SDF.format(TEMP_DATE);
	}
	
}





