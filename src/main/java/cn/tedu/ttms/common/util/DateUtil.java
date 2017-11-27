package cn.tedu.ttms.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	
	//静态同步方法默认使用的对象锁为类名.class
	public synchronized static String format(Date date){
		return sdf.format(date);
	}
	
	
	public synchronized static Date parse(String str) throws ParseException{
		return sdf.parse(str);
	}
}
