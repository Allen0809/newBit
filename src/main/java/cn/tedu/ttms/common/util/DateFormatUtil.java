package cn.tedu.ttms.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ThreadLocal是java中的一个api，此对象提供了这样的一种机制，
 * 能够将某个对象绑定到当前线程，也可以从当前线程获取某个对象，
 * 目的是保证线程内部单例（某个类的实例在当前线程中只有一份）
 * @author Administrator
 *
 */
public class DateFormatUtil {
	private static ThreadLocal<SimpleDateFormat> td = new ThreadLocal<SimpleDateFormat>();
	
	private static SimpleDateFormat getInstance(){
		//获取当前线程中format对象
		SimpleDateFormat sdf=td.get();
		//当前线程假如没有format对象，则先创建，然后绑定
		if(sdf==null){
			sdf = new SimpleDateFormat("yyyy/MM/dd");
			td.set(sdf);//绑定put(key,value)
		}
		return sdf;
	}
	
	//静态同步方法默认使用的对象锁为类名.class
	public static String format(Date date){
		return getInstance().format(date);
	}
	
	
	public static Date parse(String str) throws ParseException{
		return getInstance().parse(str);
	}
	
	
}
