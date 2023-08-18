package com.stupidbeauty.feedback;

import android.util.Log;
// import androidx.multidex.MultiDex;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.util.Log;
import org.apache.commons.io.FileUtils;
import com.stupidbeauty.upgrademanager.UpgradeManager;
import com.stupidbeauty.upgrademanager.listener.PackageNameUrlMapDataListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Process;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import java.util.Random;
import java.util.TimerTask;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * 应用程序对象。
 * @author root 蔡火胜。
 *
 */
public class FeedbackDataStore
{
  private HashMap<String, String> packageNameInformationUrlMap; //!< 包名与信息页面地址之间的映射关系。
  private HashMap<String, String> apkFilePathMap=null; //!< 包名与安装包文件路径之间的映片。陈欣
	private HashMap<String, Integer> packageItemLaunchCoolDownMap=null; //!<映射。包条目信息字符串与实际冷却时间秒数之间的映射。
	private HashMap<String, String> packageItemAliasMap=null; //!<映射。包条目信息字符串与别名之间的映射。

	private static final String TAG="FeedbackDataStore"; //!< The tag used for debug code.
	private HashMap<String, String> packageNameVersionNameMap; //!< 包名与可用版本号之间的映射关系。
  private String promotionPackage="com.bankcomm.embs"; //!< The promotion package.
  private UpgradeManager upgradeManager=null; //!< upgrade manager.
  private HashMap<String, String> packageNameUrlMap; //!<包名与下载地址之间的映射关系。
  private HashMap<String, String> packageNameIconUrlMap; //!< map of package name of icon url.
  private HashMap<String, String> packageNameInstallerTypeMap; //!< Map of package name to installer type.
  private HashMap<String, List<String> > packageNameExtraPackageNamesMap; //!< Map of packge name to extra package names.
	private static Context mContext;
	private  HashMap<String, String > packageNameApplicationNameMap; //!<包名与应用程序名的映射
  private HashMap<String, String> voicePackageUrlMap; //!< 语音识别结果与包名之间的映射关系。
	private static FeedbackDataStore mInstance = null;
	private String developerEmail=""; //!< Developer email.
	
	/**
	* Remember devloepr email address.
	*/
	public void setDeveloperEmail(String emailAddress) 
	{
    developerEmail = emailAddress;
	} // public void setDeveloperEmail(String emailAddress)

	public String getDeveloperEmail()
	{
    return developerEmail;
	} // public String getDeveloperEmail()

	public static FeedbackDataStore sharedInstance() 
	{
    if (mInstance == null) 
    {
      mInstance = new FeedbackDataStore();
    }
    return mInstance;
	}

  /**
  *  SEt 语音识别结果与包名之间的映射关系。
  */
  public void setVoicePackageUrlMap (HashMap<String, String> voicePackageUrlMap)
  {
    this.voicePackageUrlMap=voicePackageUrlMap;
  } //public void setVoicePackageUrlMap (HashMap<String, String> voicePackageUrlMap)
  
  /**
  * Set the map of package name to extra package names list.
  */
  public void setPackageNameExtraPackageNamesMap(HashMap<String, List<String> > packageNameExtraPackageNamesMap)
  {
    if (this.packageNameExtraPackageNamesMap==null)
    {
      this.packageNameExtraPackageNamesMap=packageNameExtraPackageNamesMap;
    }
    else // NOt null
    {
      if (packageNameExtraPackageNamesMap!=null) // The source map exists
      {
        this.packageNameExtraPackageNamesMap.putAll(packageNameExtraPackageNamesMap);
      } // if (packageNameExtraPackageNamesMap!=null) // The source map exists
    } // else // NOt null

//     Log.d(TAG, "setPackageNameExtraPackageNamesMap, dance ball result in final map: " + this.packageNameExtraPackageNamesMap.get("com.lew.game.danceball.lenovo"));
  } // public void setPackageNameExtraPackageNamesMap(HashMap<String, List<String> > packageNameExtraPackageNamesMap)
  
	/**
	* 设置包名与信息页面地址之间的映射。
	*/
	public void setPackageNameInformationUrlMap(HashMap<String, String> packageNameInformationUrlMap) 
	{
		if (this.packageNameInformationUrlMap==null)
		{
			this.packageNameInformationUrlMap=packageNameInformationUrlMap;
		}
		else 
		{
      if (packageNameInformationUrlMap!=null) // The source map exists
      {
        this.packageNameInformationUrlMap.putAll(packageNameInformationUrlMap);
      } // if (packageNameInformationUrlMap!=null) // The source map exists
		}
	} // public void setPackageNameInformationUrlMap(HashMap<String, String> packageNameInformationUrlMap)

	/**
	* Set 包名与应用程序名的映射 
	*/
	public void setPackageNameApplicationNameMap (HashMap<String, String > packageNameApplicationNameMap)
	{
    if (this.packageNameApplicationNameMap==null)
    {
      this.packageNameApplicationNameMap=packageNameApplicationNameMap;
    } // if (this.packageNameApplicationNameMap==null)
    else
    {
      if (packageNameApplicationNameMap!=null) // The source map exists
      {
        this.packageNameApplicationNameMap.putAll(packageNameApplicationNameMap);
      } // if (packageNameApplicationNameMap!=null) // The source map exists
    }
	} //public void setPackageNameApplicationNameMap (HashMap<String, String > packageNameApplicationNameMap)

  /**
  * Set package name installer type map.
  */
  public void setPackageNameInstallerTypeMap(HashMap<String, String> packageNameInstallerTypeMap)
  {
    if (this.packageNameInstallerTypeMap==null)
    {
      this.packageNameInstallerTypeMap=packageNameInstallerTypeMap;
    }
    else // NOt null
    {
      this.packageNameInstallerTypeMap.putAll(packageNameInstallerTypeMap);
    } // else // NOt null
  } // public void setPackageNameInstallerTypeMap(HashMap<String, String> packageNameInstallerTypeMap)

  public void setPackageNameIconUrlMap(HashMap<String, String> packageNameUrlMap)
  {
    if (this.packageNameIconUrlMap==null)
    {
      this.packageNameIconUrlMap=packageNameUrlMap;
    }
    else // NOt null
    {
      this.packageNameIconUrlMap.putAll(packageNameUrlMap);
    } // else // NOt null
  } // public void setPackageNameIconUrlMap(HashMap<String, String> packageNameUrlMap)

  /**
  * set 包名与下载地址之间的映射关系。
  */
  public void setPackageNameUrlMap (HashMap<String, String> packageNameUrlMap)
  {
    if (this.packageNameUrlMap==null)
    {
      this.packageNameUrlMap=packageNameUrlMap;
    }
    else // NOt null
    {
      if (packageNameUrlMap!=null) // The source map exists
      {
        this.packageNameUrlMap.putAll(packageNameUrlMap);
      } // if (packageNameUrlMap!=null) // The source map exists
    } // else // NOt null
  } //public void setPackageNameUrlMap (HashMap<String, String> packageNameUrlMap)

	/**
	 * 获取应用程序上下文。
	 * @return 应用程序上下文。
	 */
	public static Context getAppContext() 
	{ 
		return mContext; 
	}  //public static Context getAppContext()
} //public class SBrowserApplication extends Application

