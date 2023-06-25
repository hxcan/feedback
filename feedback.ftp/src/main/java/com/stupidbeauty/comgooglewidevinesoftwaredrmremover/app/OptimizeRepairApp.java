package com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

/**
 * 应用程序对象。
 * @author root 蔡火胜。
 *
 */
public class OptimizeRepairApp extends Application
{
	private ApplicationListData applicationListData; //!<应用程序列表数据。

	public ApplicationListData getApplicationListData() {
		return applicationListData;
	}

	@SuppressLint("StaticFieldLeak")
	private static OptimizeRepairApp mInstance = null;

//	private static Context mContext;
	private static final String TAG="OptimizeRepairApp"; //!<输出调试信息时使用的标记。
	
	@Override
	/**
	 * 程序被创建。
	 */
	public void onCreate() 
	{
		super.onCreate(); //创建超类。
		mInstance = this; //获取应用程序上下文。


		//启用严格模式：
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads()
				.detectDiskWrites()
				.detectNetwork()
				.penaltyLog()
				.build()
		);

		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
						.detectLeakedSqlLiteObjects()
						.detectLeakedClosableObjects()
						.penaltyLog()
						.penaltyDeath()
						.build()
		);

		loadApplicationList(); //载入应用程序列表。

//		setCustomLocale(); //设置自定义语系。
	} //public void onCreate()

	/**
	 * 载入应用程序列表。
	 */
	private void loadApplicationList()
	{
		applicationListData =new ApplicationListData(this); //创建数据对象。

		applicationListData.loadApplicationList(); //载入本地服务器列表。
	} //private void loadApplicationList()


	public static OptimizeRepairApp getInstance() {
		if (mInstance == null) {
			mInstance = new OptimizeRepairApp();
		}
		return mInstance;
	}

	/**
	 * 获取应用程序上下文。
	 * @return 应用程序上下文。
	 */
	public static Context getAppContext() 
	{ 
		return mInstance;
	}  //public static Context getAppContext()
} //public class SimoApp extends Application

