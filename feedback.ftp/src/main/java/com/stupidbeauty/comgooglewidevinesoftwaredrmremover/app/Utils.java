package com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app;

import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;



/**
 * Some util functions.
 * @author root Hxcan.
 *
 */
public class Utils 
{
	public static final String TAG = "Utils";
	/**
	 * @param time The time stamp , millisecond.
	 * @param format 要转化的时间格式 例如：yyyy-MM-dd HH:mm:ss
	 * @return The formatted string.
	 */
	public static String dateFormat(long time, String format) 
	{
		TimeUnit milliSecondUnit=TimeUnit.MILLISECONDS; //The time unit , millisecond.
		
		SimpleDateFormat dateformat=new SimpleDateFormat(format,Locale.US); //Create the date format object.
		Date d = new Date(); //Create the date object.
		d.setTime( milliSecondUnit.toMillis(time) ); //Set the time.
		return dateformat.format(d); //Return the formatted result.
	} //public static String dateFormat(long time, String format)

	public static String matchNumber(String number) {
		if(number != null && number.length() > 8){
			number = "%" + number.substring(number.length() - 8, number.length());
		} else {
			number = null;
		}
		return number;
	}
	
	/**
	 * 获取用于显示账户信息的URL。
	 * @return 用于显示账户信息的URL。
	 */
	public static String getAccountURL() 
	{
		String lang = Utils.getLocalLanguage(); //获取本地语言。
		String url = Config.shareInstance().ACCOUNT_TAB_URL; //获取基本的账户页面URL。
		
		
		LogHelper.d(TAG,"被查询账户页面URL："+url+"?lang=" +lang); //Debug.
		return url+"?lang=" +lang;
		
	} //public static String getAccountURL()
	
 
	public static String getServiceURL() {
		LogHelper.i(TAG, "-- getServiceURL() --");
		String url = "http://www.skyroam.com/goToLive800.php?lan=zh&™=1377761992612";
		LogHelper.i(TAG, url);
		return url;
	}
	
	public static String getCoverageURL() {
		String url = "http://coverage.skyroam.com/package-";
		String lang = Locale.getDefault().toString();
		if ("zh_CN".equals(lang)) {
			lang = "zh";
		} else if ("zh_TW".equals(lang)) {
			lang = "zh";
		} else {
			lang = "en";
		}
		url += lang; 
		url += "2.html"; 
		return url;
	}
	
	/**
	 * 获取本地语言。
	 * @return 本地语言代号字符串。
	 */
	public static String getLocalLanguage() 
	{
		String lang = Locale.getDefault().toString(); //获取本地语言字符串。
		
		if ("zh_CN".equals(lang)) //简体中文。 
		{
			lang = Constants.FAQLangKey.LANG_KEY_ZH_CN; //简体中文。
		} //if ("zh_CN".equals(lang)) //简体中文。
		else if ("zh_TW".equals(lang)) //繁体中文。 
		{
			lang = Constants.FAQLangKey.LANG_KEY_ZH_TW; //繁体中文。
		} //else if ("zh_TW".equals(lang)) //繁体中文。
		else //其它语言。 
		{
			lang = Constants.FAQLangKey.LANG_KEY_EN; //英文。
		} //else //其它语言。
		
		return lang;
	} //public static String getLocalLanguage()
	
	public static int getLocalLanguageID() {
		String lang = Locale.getDefault().toString();
		int langID = -1;
		if ("zh_CN".equals(lang)) {
			langID = Constants.LangId.LANG_ZH_CN;
		} else if ("zh_TW".equals(lang)) {
			langID = Constants.LangId.LANG_ZH_TW;
		} else {
			langID = Constants.LangId.LANG_EN;
		}
		return langID;
	}

	public static String formatCallNumber(String number) {
		return null;
	}
} //public class Utils

