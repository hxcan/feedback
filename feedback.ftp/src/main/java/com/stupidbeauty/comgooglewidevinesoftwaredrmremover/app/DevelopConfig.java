package com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app;





/**
 * 开发环境的配置信息。
 * @author root 蔡火胜。
 *
 */
public class DevelopConfig extends Config 
{

	/**
	 * 构造函数。
	 */
	public DevelopConfig() 
	{
		debug = true;
		verbose = false;
		info = false;
		warn = false;
		error = true;

		GMATE_UPDATE_URL = "http://www.skyroam.com/ota_test/OTACheckUpdate.php";
		FAQ_UPDATE_URL_BASE = "http://www.skyroam.com/faq_test/FaqFileVersion.php";
		CLIENT_UPDATE_URL_BASE = "http://www.skyroam.com/ota_test/CheckVersion.php";

//		String BaseUrl = "http://test5.skyroam.com.cn:8083/ismp-ehall"; //!<开发部门的测试版的BOSS地址。
		//String BaseUrl = "http://test5.skyroam.com.cn:8083/ismp-ehall";
//		String BaseUrl = "http://192.168.5.93:8080/ismp-ehall";
//		String BaseUrl = "https://192.168.5.93:8443/ismp-ehall";
//		String BaseUrl = "https://simowireless.sinaapp.com/appdoc"; //新浪网站上的HTTPS测试地址。 
		
		BASE_LOGIN_PATH =  "http://test5.skyroam.com.cn:8083/ismp-ehall/tologin.do";		
//		BASE_LOGIN_PATH = BaseUrl+ "/requestLogin.php?module=a"; //新浪网站上的HTTPS测试路径。
		WEB_LOGIN_PATH  =  "/login.do";
		//WEB_LOGIN_PATH  = BaseUrl+ "/recharge/recharge.do";		
		ACCOUNT_TAB_URL =  "http://test5.skyroam.com.cn:8083/ismp-ehall/in/account.do";		
		
		

		
		
		//静态网页相关的参数：
		STATIC_WEBPAGE_URL_PREFIX = "http://www.skyroam.com/appdoc_test/requestDocPage.php"; //!<静态网页URL前缀。
 
		
		CONFIG_URL="http://client.skyroam.com.cn/app/config"; //检查配置文件更新的URL。
		CONFIG_UPDATE_INTERVAL=60; //检查配置文件更新的时间间隔。


	} //public DevelopConfig()
} //public class DevelopConfig extends Config
