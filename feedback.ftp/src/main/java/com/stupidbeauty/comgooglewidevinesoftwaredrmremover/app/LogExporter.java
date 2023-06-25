package com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.util.Log;

import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.Constants;


/**
 * 用于将客户端本身的日志输出到SD卡上的文件。
 * 1,if has not sdcard save log file to memory,else save to sdcard 2,when insert
 * into sdcard copy logfile from memory to sdcard and del them. 3,when file is
 * bigger then 5M,creat new log file
 */
public class LogExporter 
{
	public static final String TAG = "ClientLogFileWriter"; //!<输出调试信息时使用的标记。
	
	//======================================
	//静态常量
	//======================================
	private String LOG_PATH_MEMORY_DIR; // 日志文件在内存中的路径(日志文件在安装目录中的路径)
	private String LOG_PATH_SDCARD_DIR; // 日志文件在sdcard中的路径
	private static final int SDCARD_TYPE = 0; // 当前的日志记录类型为存储在SD卡下面
	private static final int MEMORY_TYPE = 1; // 当前的日志记录类型为存储在内存中
	private static final long LOG_FILE_MAX_SIZE = 1024 * 1024 * 2;// 2M
	private static String strPriority[]; //!<表示优先级的字符串数组。
	
	
	static //各个优先级。 
	{
		strPriority = new String[8]; //创建字符串数组。
		strPriority[0] = ""; //无优先级。
		strPriority[1] = ""; //无优先级。
		strPriority[2] = "verbose"; //详尽。
		strPriority[3] = "debug"; //调试。
		strPriority[4] = "info"; //信息。
		strPriority[5] = "warn"; //警告。
		strPriority[6] = "error"; //错误。
		strPriority[7] = "ASSERT"; //断言。
	} //static //各个优先级。
	
	//======================================
	//变量
	//======================================
	private String mDeviceMacAddr;
	private FileWriter fileWriter; //!<文件输出器。
	private String CURR_LOG_NAME;
	
	//======================================
	//构造单例
	//======================================
	private static LogExporter mInstance;
	
	/**
	 * 获取单实例。
	 * @return 此类的单实例对象。
	 */
	public static LogExporter getInstance()
	{
		if(mInstance == null) //实例还不存在。
		{
			mInstance = new LogExporter(); //创建实例。
		} //if(mInstance == null) //实例还不存在。
		
		return mInstance;
	} //public static ClientLogFileWriter getInstance()
	
	public LogExporter() {
		initDirPath();
		//createLogFile();
	}
	/**
	 * init the file store path in sdcard or phone memory
	 */
	private void initDirPath() 
	{
		// 获取设备的Mac 地址
		WifiManager wifi = (WifiManager) OptimizeRepairApp.getAppContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE); //获取无线網信息管理器。
		WifiInfo info = wifi.getConnectionInfo(); //获取无线網信息。
		mDeviceMacAddr = info.getMacAddress(); //获取无线网卡的物理地址。

		if (mDeviceMacAddr!=null) //获取到了网卡物理地址。
		{
			mDeviceMacAddr = mDeviceMacAddr.replaceAll(":", ""); //将冒号去掉。
		} //if (mDeviceMacAddr!=null) //获取到了网卡物理地址。
		else //未获取到网卡物理地址。
		{
			mDeviceMacAddr= Constants.FilePath.UnknownDeviceMacAddr; //设备的網卡物理地址。
		} //else //未获取到网卡物理地址。
		

		SimpleDateFormat format_day = new SimpleDateFormat("yyyy-MM-dd",Locale.US); //创建日期格式化器。
		
		
		LOG_PATH_SDCARD_DIR = LanImeBaseDef.LOG_BASE_DIR + File.separator+ format_day.format(new Date()) + File.separator+ LanImeBaseDef.LOG_ANDROID_DIR + File.separator+ mDeviceMacAddr; //SD卡目录路径。
		LOG_PATH_MEMORY_DIR = OptimizeRepairApp.getAppContext().getFilesDir().getAbsolutePath()+ File.separator+ LanImeBaseDef.LOG_DIR_FILE+ File.separator+ format_day.format(new Date())+ File.separator+ LanImeBaseDef.LOG_ANDROID_DIR + File.separator+ mDeviceMacAddr; //内存目录路径。
		
		return;
	} //private void initDirPath()

	/**
	 * 
	 * @param fileName 文件名称
	 * @return 文件大小
	 */
	private long getFileSize(String fileName) 
	{
		if (fileName != null && !"".equals(fileName)) //日志文件名字有效。 
		{
			File file = new File(fileName); //创建文件信息对象。
			
			if (!file.exists()) //文件不存在。 
			{
				return 0; //返回默认值，0.
			} //if (!file.exists()) //文件不存在。
			else  //文件存在。
			{
				return file.length(); //返回文件的尺寸。
			} //else  //文件存在。
		} //if (fileName != null && !"".equals(fileName)) //日志文件名字有效。
		
		return 0;
	} //private long getFileSize(String fileName)
	
	/**
	 * 判断是否需要从新创建文件
	 */
	private void createLogFile() 
	{
		if (CURR_LOG_NAME != null && getFileSize(CURR_LOG_NAME) < LOG_FILE_MAX_SIZE && fileWriter!=null) //日志文件存在。 
		{
		} //if (CURR_LOG_NAME != null && getFileSize(CURR_LOG_NAME) < LOG_FILE_MAX_SIZE && fileWriter!=null) //日志文件存在。
		else //日志文件不存在。 
		{
			//文件名
			SimpleDateFormat format_time = new SimpleDateFormat("yyyy-MM-dd HHmmss",Locale.US); //创建日期格式化器。
			String fileName = format_time.format(new Date()) + ".txt"; //文件名。
			
			//层级文件夹
			String dirPath; //日志文件的路径。
			if (getStoreType() == MEMORY_TYPE) //存储类型是内存。 
			{
				dirPath = LOG_PATH_MEMORY_DIR; //使用内存的路径。
			} //if (getStoreType() == MEMORY_TYPE) //存储类型是内存。
			else //存储类型是SD卡。 
			{
				dirPath = LOG_PATH_SDCARD_DIR; //使用SD卡的路径。
			} //else //存储类型是SD卡。
			
			//文件的绝对路径
			String filepath = dirPath + File.separator + fileName; //文件的路径。
			CURR_LOG_NAME = filepath; //记录当前日志名字。

			File file = new File(dirPath); //创建文件对象。
			
			if (!file.exists()) //目录不存在。 
			{
				try //尝试创建路径层级，并且捕获可能的异常。 
				{
					file.mkdirs(); //创建目录层级。
				} //try //尝试创建路径层级，并且捕获可能的异常。
				catch (Exception e) //捕获异常。 
				{
					LogHelper.e(TAG, "createNewFile mkdirs:"+e.toString()); //报告错误。
				} //catch (Exception e) //捕获异常。
			} //if (!file.exists()) //文件不存在。
			
			File dir = new File(filepath); //创建文件对象。
			if (!dir.exists()) //文件不存在。
			{
				try //创建文件，并且捕获可能的异常。 
				{
					dir.createNewFile(); //创建文件。
				} //try //创建文件，并且捕获可能的异常。
				catch (Exception e) //捕获异常。 
				{
					LogHelper.e(TAG, "createNewFile createNewFile:"+e.toString()); //报告错误。
				} //catch (Exception e) //捕获异常。
			} //if (!dir.exists()) //文件不存在。
			
			LogHelper.d(TAG, "createLogFile else  filepath:" + filepath); //Debug.
			
			try //尝试创建文件输出器，并且捕获可能的异常。 
			{
				if(fileWriter!=null) //文件输出器已存在。//before new one close the last first
				{
					fileWriter.close(); //关闭文件输出器。
					fileWriter = null; //重置指针。
				} //if(fileWriter!=null) //文件输出器已存在。
				
				fileWriter = new FileWriter(filepath, true); //创建文件输出器。
			} //try //尝试创建文件输出器，并且捕获可能的异常。
			catch (Exception e) //捕获异常。 
			{
				e.printStackTrace(); //报告错误。
			} //catch (Exception e) //捕获异常。
		} //else //日志文件不存在。
	} //private void createLogFile()
	
	/**
	 * 获取当前应存储在内存中还是存储在SDCard中
	 * 
	 * @return
	 */
	private int getStoreType() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return SDCARD_TYPE;
		} else {
			return MEMORY_TYPE;
		}
	}

	
	
	public void writeFileLog(String msg){
		createLogFile();
		try {
			fileWriter.write(msg);
			fileWriter.flush();
		} catch (Exception e) {
			fileWriter = null;
			LogHelper.e("FileLog", "", e);
		}
	}

	/**
	 * 关闭文件。
	 */
	public void closeFile() 
	{
		try //尝试关闭文件，并且捕获异常。 
		{
			if (fileWriter != null) //文件输出器存在。 
			{
				fileWriter.close(); //关闭文件。
				fileWriter = null; //释放。
			} //if (fileWriter != null) //文件输出器存在。
		} //try //尝试关闭文件，并且捕获异常。
		catch (Exception e) //捕获异常。 
		{
			LogHelper.e(TAG, e.toString()); //Debug.
		} //catch (Exception e) //捕获异常。
	} //public void closeFile()
	
	
	/**
	 * MOVE LOG FILES TO SD CARD
	 */
	private void moveLogfiles() {
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			// Log.d(TAG,"move file failed, sd card does not mount");
			return;
		}
		File file = new File(LOG_PATH_SDCARD_DIR);
		if (!file.isDirectory()) {
			boolean mkOk = file.mkdirs();
			if (!mkOk) {
				// Log.d(TAG,"move file failed,dir is not created succ");
				return;
			}
		}

		file = new File(LOG_PATH_MEMORY_DIR);
		if (file.isDirectory()) {
			File[] allFiles = file.listFiles();
			for (File logFile : allFiles) {
				String fileName = logFile.getName();
				boolean isSucc = copy(logFile, new File(LOG_PATH_SDCARD_DIR
						+ File.separator + fileName));
				if (isSucc) {
					logFile.delete();
					// Log.d(TAG,"move file success,log name is:"+fileName);
				}
			}
		}
	}

	/**
	 * COPY FILE
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	private boolean copy(File source, File target) {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			if (!target.exists()) {
				boolean createSucc = target.createNewFile();
				if (!createSucc) {
					return false;
				}
			}
			in = new FileInputStream(source);
			out = new FileOutputStream(target);
			byte[] buffer = new byte[8 * 1024];
			int count;
			while ((count = in.read(buffer)) != -1) {
				out.write(buffer, 0, count);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG,"copy 1" +  e.getMessage(), e);
			return false;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.e(TAG, "copy 2" +  e.getMessage(), e);
				return false;
			}
		}
	}

	/**
	 * transfer logs file from memory to sdcard
	 */
	class LogFilesTransferThread extends Thread {

		public LogFilesTransferThread() {
			super("LogFilesTransferThread");
		}

		@Override
		public void run() {
			moveLogfiles();
		}
	}

}
