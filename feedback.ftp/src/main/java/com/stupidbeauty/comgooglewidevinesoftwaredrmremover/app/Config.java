package com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import org.json.JSONException;
import org.json.JSONObject;


 
/**
 * 配置信息类。
 * @author root
 *
 */
public class Config 
{
	private static final int ENVIRONMENT_RELEASE = 1; //!<当前是要打包出发布版本。
	private static final int ENVIRONMENT_BETA = 2; //!<当前是要打包出测试版本。
	private static final String TAG = "Config"; //!<输出调试信息时使用的标记。
	
	// control
	private static int environmentConfig = ENVIRONMENT_BETA; //!<所要使用的环境配置。发布/测试/开发。
	private static String SvnVersion = "xxxxx"; //build版本号。与SVN版本号一致。

	// define
	public boolean debug = true; //!<输出调试信息。
	public boolean verbose = false; //!<输出详细跟踪信息。
	public boolean info = false; //!<输出信息型调试消息。
	public boolean warn = false; //!<输出警告消息。
	public boolean error = true; //!<输出错误消息。

	// update url
	public String GMATE_UPDATE_URL; //!<OTA的检查更新路径。
	public String FAQ_UPDATE_URL_BASE; //!<FAQ的更新路径。
	public String CLIENT_UPDATE_URL_BASE; //!<APP的更新路径。
	
	
	//静态网页相关的参数：
	public  String STATIC_WEBPAGE_URL_PREFIX ; //!<静态网页URL前缀。
 
	
	// login url
	public String BASE_LOGIN_PATH; //!<BOSS的登录URL。
	//web login url
	public  String WEB_LOGIN_PATH; //!<BOSS的网页界面登录URL。当网厅中的会话到期时，会将网页跳转到这个地址，APP发现被跳转到这个地址，则退出登录。
	// account url
	public String ACCOUNT_TAB_URL; //!<BOSS的账户界面URL。
	
	//OTA 与客户端检查更新的时间间隔
	public static int    TIME_DELAY_BETWEEN_CHECK_UPDATA = 60* (1000 * 60); //!<OTA与客户端检查更新的时间间隔。
	
	public String CONFIG_URL; //!<配置文件检查更新的URL。
	public int CONFIG_UPDATE_INTERVAL; //!<配置文件检查更新的时间间隔，以分钟为单位。
	public String RENT_API_BASE_URL="http://test6.skyroam.com.cn/lenovo-ehall/"; //!<網厅接口的基准URL。
	
	// private
	private static Config mConfig; //!<实际的配置对象实例。

	/**
	 * 获取共享的单实例。
	 * @return 共享的单实例。
	 */
	public static Config shareInstance() 
	{
		if (mConfig == null) //配置信息实例还不存在。 
		{
			if (environmentConfig==ENVIRONMENT_RELEASE) //是发布版本。 
			{
				mConfig = new ReleaseConfig(); //创建发布版本的配置信息。
			}
			else //是开发版本。
			{
				mConfig = new DevelopConfig(); //创建开发版本的配置信息。
			} //else //是开发版本。
		}

		return mConfig;
	} //public static Config shareInstance()

	/**
	 * 将从JSON配置文件中读入的内容应用到配置信息对象中。
	 * @param configFileContent 要应用的JSON字符串。
	 */
	public void applyConfigJson(String configFileContent) 
	{
		try //尝试进行解析。 
		{
			JSONObject jsonObj = new JSONObject(configFileContent); // 创建JSON对象。
			
			applyConfigJson(jsonObj); //应用配置。
			

				
				
			
		} //try //尝试进行解析。
		catch (JSONException e) 
		{
			e.printStackTrace();
			
			
			System.err.println("等解析的数据："+configFileContent); //Debug.
		}				//catch (JSONException e)

		
	} //private void applyConfigJson(String configFileContent)

	public void applyConfigJson(JSONObject jsonObj)  
	{
		try {
			BASE_LOGIN_PATH = jsonObj.getString("login_url");
			WEB_LOGIN_PATH = jsonObj.getString("login_page_action");//網厅跳转要求重新登录的URL。
			ACCOUNT_TAB_URL = jsonObj.getString("account_url");//账户信息URL。
			
			
			
			
			STATIC_WEBPAGE_URL_PREFIX = jsonObj.getString("user_doc_url");//静态网页请求URL。
			FAQ_UPDATE_URL_BASE = jsonObj.getString("faq_update_url");//FAQ更新的URL。
			CLIENT_UPDATE_URL_BASE = jsonObj.getString("app_update_url"); //APP检查更新的URL。
			GMATE_UPDATE_URL = jsonObj.getString("ota_url"); //OTA检查更新的URL。
				
			CONFIG_URL=jsonObj.getString("config_url"); //配置文件检查更新的URL。
			
			
			LogHelper.d(TAG,"配置文件更新地址："+CONFIG_URL); //Debug.
			
			CONFIG_UPDATE_INTERVAL=jsonObj.getInt("update_timer"); //配置文件检查更新的时间间隔，以分钟为单位。
		
		} catch (JSONException e) {
			e.printStackTrace();
		}//客户端发送登录请求的URL。					
		
	}

	/**
	 * 是否输出话痨级别的调试信息。
	 * @return 是否输出话痨级别的调试信息。
	 */
	public static boolean verbose() 
	{
		return Config.shareInstance().verbose;
	} //public static boolean verbose()

	
	/**
	 * 是否输出调试级别的调试信息。
	 * @return 是否输出调试级别的调试信息。
	 */
	public static boolean debug() 
	{
		return Config.shareInstance().debug;
	} //public static boolean debug() 

	/**
	 * 是否输出信息级别的调试信息。
	 * @return 是否输出信息级别的调试信息。
	 */
	public static boolean info() 
	{
		return Config.shareInstance().info;
	} //public static boolean info() 

	/**
	 * 是否输出错误级别的调试信息。
	 * @return 是否输出错误级别的调试信息。
	 */
	public static boolean error() 
	{
		return Config.shareInstance().error;
	} //public static boolean error()

	/**
	 * 是否输出警告级别的调试信息。
	 * @return 是否输出警告级别的调试信息。
	 */
	public static boolean warn() 
	{
		return Config.shareInstance().warn;
	} //public static boolean warn() 
	
	/**
	 * 获取版本号字符串。
	 * @return 版本号字符串。
	 */
	public static String getVersion()
	{
		Context context = OptimizeRepairApp.getAppContext(); //获取上下文。
		
		try //尝试获取软件包信息。 
		{
			PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0); //获取软件包信息。			 
			return pi.versionName ;		  //返回版本号名字。
		} //try //尝试获取软件包信息。 
		catch (NameNotFoundException e) //捕获异常，名字未找到。 
		{
			e.printStackTrace(); //输出错误内容。
			return "0.0.0"; //版本号为0.0.0.
		} //catch (NameNotFoundException e) //捕获异常，名字未找到。
	} //public static String getVersion()

	/**
	 * 获取用于显示的版本号字符串。
	 * @return 用于显示的版本号字符串。
	 */
	public static String getDisplayVersion()// 获取版本号
	{
		if (environmentConfig!=ENVIRONMENT_RELEASE) //不是发布版本。 
		{
			return getVersion() + "(B"+ SvnVersion + ")";
		}
		else //是发布版本。 
		{
			return getVersion() + "(R"+ SvnVersion + ")";
		} //else //是发布版本。		 
	} //public static String getDisplayVersion()// 获取版本号

} //public class Config 

