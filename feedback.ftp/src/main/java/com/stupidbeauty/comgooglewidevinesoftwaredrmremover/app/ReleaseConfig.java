package com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app;





/**
 * 针对线上发布版本的配置信息。
 * @author root 蔡火胜。
 *
 */
public class ReleaseConfig extends Config 
{


	/**
	 * 构造函数。
	 */
	public ReleaseConfig() 
	{
		debug = true;
		verbose = true;
		info = true;
		warn = true;
		error = true;

		GMATE_UPDATE_URL = "http://www.skyroam.com/ota/OTACheckUpdate.php";
		FAQ_UPDATE_URL_BASE = "http://www.skyroam.com/faq/FaqFileVersion.php";
		CLIENT_UPDATE_URL_BASE = "http://www.skyroam.com/ota/CheckVersion.php";

//		String BaseUrl = "http://a.skyroam.com";

		BASE_LOGIN_PATH =  "http://a.skyroam.com/ismp-ehall/tologin.do";
		WEB_LOGIN_PATH =  "/login.do"; //用于监测網厅的跳转情况。
		ACCOUNT_TAB_URL =  "http://a.skyroam.com/ismp-ehall/in/account.do";
		
		


		// 静态网页相关的参数：
		STATIC_WEBPAGE_URL_PREFIX = "http://www.skyroam.com/appdoc/requestDocPage.php"; //静态网页URL前缀。
		
		CONFIG_URL="http://client.skyroam.com.cn/app/config"; //检查配置文件更新的URL。
		CONFIG_UPDATE_INTERVAL=60; //检查配置文件更新的时间间隔。
		
		
	} //public ReleaseConfig() 
} //public class ReleaseConfig extends Config
