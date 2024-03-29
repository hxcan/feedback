package com.stupidbeauty.feedback;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.stupidbeauty.feedback.activity.FeedbackActivity;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import android.os.Debug;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import io.github.g00fy2.versioncompare.Version;
import com.stupidbeauty.upgrademanager.listener.PackageNameUrlMapDataListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PowerManager;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.StrictMode;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import android.util.Log;
import android.media.MediaDataSource;
import com.google.gson.Gson;
import com.upokecenter.cbor.CBORObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import com.google.gson.Gson;
import com.upokecenter.cbor.CBORObject;
import com.google.gson.Gson;
import android.media.MediaPlayer;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashMap;
import android.content.Context;
import com.stupidbeauty.victoriafresh.VFile;
import com.stupidbeauty.upgrademanager.asynctask.UmLoadVoicePackageUrlMapTask;
import com.stupidbeauty.upgrademanager.asynctask.LoadVoicePackageUrlMapInterface;

public class Feedback
{
  private long lastLoadDataTime=0; //!< Remember load time stamp. Avoid loading corrupt cache files too frequently.
  private long checkingStartTime=0; //!< 记录开始时间戳。
  private boolean checkingUpgrade=false; //!< if we are already checking for upgrade.
  private int checkCounter=0; //!< Check counter.
  private HashMap<String, String> packageNameInformationUrlMap; //!< 包名与信息页面地址之间的映射关系。
  private int recognizeCounter=0; //!<识别计数器．
  private int port=1421; //!< Port.
  private boolean allowActiveMode=true; //!<  Whether to allow active mode.
  private static final String TAG = "Feedback"; //!< The tag used for debug code.
  private String recordSoundFilePath; //!< 录音文件路径．
  private MediaPlayer mediaPlayer;
  private static final float BEEP_VOLUME = 0.20f;
  private HashMap<String, String> voiceUiTextSoundFileMap=null; //!< 声音内容与声音文件名之间的映射关系。
  private HashMap<String, String> packageNameUrlMap; //!< 包名与下载地址之间的映射关系。
	private HashMap<String, String> voicePackageUrlMap; //!<语音识别结果与包名之间的映射关系。
  private HashMap<String, String> packageNameInstallerTypeMap; //!< Map of package name to installer type.
  private HashMap<String, String> packageNameIconUrlMap; //!< Map of package name to icon url.
  private HashMap<String, String> packageNameApplicationNameMap; //!< Map of package name to application name.
  private HashMap<String, List<String> > packageNameExtraPackageNamesMap; //!< Map of packge name to extra package names.
  private PackageNameUrlMapDataListener packageNameUrlMapDataListener; //!< Package name url map data listener.
  private Context context; //!< Context.

  /**
  * Show feedback ui.
  */
  public void showFeedbackUi()
  {
    // Chen xin.
    
    Log.d(TAG, "gotoLoginActivity, 119."); //Debug.
    Intent launchIntent=new Intent(context, FeedbackActivity.class); // 启动意图。

    context.startActivity(launchIntent); //启动活动。

    Log.d(TAG, "gotoLoginActivity, 122."); //Debug.
  } // public void setPackageNameUrlMapDataListener(PackageNameUrlMapDataListener listener)

  /**
  * SEt the map of package naem to icon url.
  */
  public void setPackageNameIconUrlMap(HashMap<String, String> packageNameUrlMap)
  {
    this.packageNameIconUrlMap=packageNameUrlMap;
    
    if (packageNameUrlMapDataListener!=null)
    {
      packageNameUrlMapDataListener.setPackageNameIconUrlMap(packageNameUrlMap);
    }
  } // public void setPackageNameIconUrlMap(HashMap<String, String> packageNameUrlMap)

	/**
	* Set 包名与应用程序名的映射 
	*/
	public void setPackageNameApplicationNameMap (HashMap<String, String > packageNameApplicationNameMap)
	{
    this.packageNameApplicationNameMap=packageNameApplicationNameMap;

    if (packageNameUrlMapDataListener!=null) // There is a listener.
    {
      packageNameUrlMapDataListener.setPackageNameApplicationNameMap(packageNameApplicationNameMap);

      long currentTimeMillis=System.currentTimeMillis(); // Get the curent time stamp.
      lastLoadDataTime=currentTimeMillis; // Remember load time stamp.
    } // if (packageNameUrlMapDataListener!=null) // There is a listener.
	} //public void setPackageNameApplicationNameMap (HashMap<String, String > packageNameApplicationNameMap)
  
  private HashMap<String, String> packageNameVersionNameMap; //!< 包名与可用版本号之间的映射关系。
  
	/**
	 *  // 获取可用的版本名字。
	 * @param packageName
	 * @return
	 */
	public String getAvailableVersionName(String packageName)
	{
    String result= ""; // 获取可用 版本号名字。
      
    if (packageNameVersionNameMap!=null) // The map exists
    {
      result= packageNameVersionNameMap.get(packageName); // 获取可用 版本号名字。
    } // if (packageNameVersionNameMap!=null) // The map exists
      
    return result;
	} //public String getAvailableVersionName(String packageName)
	
  /**
  * 载入语音识别结果与包下载地址之间的映射。
  */
  private void loadVoicePackageUrlMap(String filePath)
  {
    long currentTimeMillis=System.currentTimeMillis(); // Get the curent time stamp.
    
    if ((currentTimeMillis-lastLoadDataTime) >= 19*1000) // Only load once in every 19 seconds
    {
      UmLoadVoicePackageUrlMapTask translateRequestSendTask =new UmLoadVoicePackageUrlMapTask(); // 创建异步任务。

      translateRequestSendTask.execute(this, filePath); //执行任务。
      
      // lastLoadDataTime=currentTimeMillis; // Remember load time stamp.
    } // if ((currentTimeMillis-lastLoadDataTime) >= 19*1000) // Only load once in every 19 seconds
  } //private void loadVoicePackageUrlMap()

  /**
  * Set to allow or not allow active mode.
  */
  public void setAllowActiveMode(boolean allowActiveMode)
  {
    this.allowActiveMode=allowActiveMode;
  } //private void setAllowActiveMode(allowActiveMode)
    
  public void setPort(int port)
  {
    this.port=port;
  } //public void setPort(int port)
        
  public Feedback(Context context, String emailAddress)
  {
    this.context = context;
    
    FeedbackDataStore feedbackDataStore = FeedbackDataStore.sharedInstance(); // Get the feedback data store.
    
    feedbackDataStore.setDeveloperEmail(emailAddress); // Remember devloepr email address.
  } // public UpgradeManager(Context context) 

  /**
  * Mark stuck checking upgrade
  */
  private void markStuckCheckingUpgrade()
  {
    if (checkingUpgrade) // It is already checking.
    {
      Log.d(TAG, "markStuckCheckingUpgrade, 353, already checking upgrade, checking the time started"); // Debug.
      
      long currentTime=System.currentTimeMillis(); // 记录开始时间戳。

      if ((currentTime-checkingStartTime) > (1*60*60*1000)) // started 1 hour ago
      {
        checkingUpgrade=false;
      } // if ((currentTime-checkingStartTime) > (1*60*60*1000)) // started 1 hour ago
    } // if (checkingUpgrade) // It is already checking.
  } // private void markStuckCheckingUpgrade()
} // public class UpgradeManager implements DownloadRequestorInterface, LoadVoicePackageUrlMapInterface
