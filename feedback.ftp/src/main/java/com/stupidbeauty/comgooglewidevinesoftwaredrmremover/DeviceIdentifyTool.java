package com.skyroam.silver;

import java.util.Random;
import java.util.UUID;


import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.OptimizeRepairApp;


/**
 * 设备唯一标识工具。
 * @author root 蔡火胜。
 *
 */
public class DeviceIdentifyTool 
{
    private static final String TAG = "DeviceIdentifyTool"; //!<输出调试信息时使用的标记。

	/**
     * 获取IMEI。
     * @return IMEI。
     */
	@SuppressLint("HardwareIds")
	public static String getImei()
	{
		String result=""; //结果。
		
		Context appContext= OptimizeRepairApp.getAppContext(); //获取应用程序上下文。
		final TelephonyManager tm = (TelephonyManager) appContext.getSystemService(Context.TELEPHONY_SERVICE);

		String tmDevice;
	    tmDevice = "" + tm.getDeviceId();
	    
	    result=tmDevice; //记录结果。


		return result;
	} //public static String getImei()

	/**
	 * 生成随机的IMEI。
	 * @return 随机的IMEI。
	 */
	private static String generateRandomImei() 
	{
		String result="";
		Random random=new Random(); //创建随机数生成器。
		
		for(int imeiCounter=0;imeiCounter<15;imeiCounter++) //一个个字符地生成。
		{
			int currentInt= random.nextInt(10); //获取一个整数。
			
			result=result+currentInt; //拼接。
		} //for(int imeiCounter=0;imeiCounter<15;imeiCounter++) //一个个字符地生成。
		
		Log.d(TAG,"generateRandomImei,result:"+result); //Debug.
	
		return result;
	} //private static String generateRandomImei()


	public static byte[] shortToByteArray(short s) {
		byte[] shortBuf = new byte[2];
		for (int i = 0; i < 2; i++) {
			int offset = (shortBuf.length - 1 - i) * 8;
			shortBuf[i] = (byte) ((s >>> offset) & 0xff);
		}
		return shortBuf;
	}

	public static final int byteArrayToShort(byte[] b) {
		return (b[0] << 8) + (b[1] & 0xFF);
	}

	public static byte[] intToByteArray(int value) {
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			int offset = (b.length - 1 - i) * 8;
			b[i] = (byte) ((value >>> offset) & 0xFF);
		}
		return b;
	}

    /**
	 * 生成随机的设备编号。
	 * @return 随机的设备编号。
	 */
	private static String generateRandomDeviceId() 
	{
		String result="";
		Random random=new Random(); //创建随机数生成器。
		
		for(int imeiCounter=0;imeiCounter<25;imeiCounter++) //一个个字符地生成。
		{
			int currentInt= random.nextInt(10); //获取一个整数。
			
			result=result+currentInt; //拼接。
		} //for(int imeiCounter=0;imeiCounter<15;imeiCounter++) //一个个字符地生成。
		
		Log.d(TAG,"generateRandomDeviceId,result:"+result); //Debug.
	
		return result;
} //private static String generateRandomDeviceId()

} //public class DeviceIdentifyTool

