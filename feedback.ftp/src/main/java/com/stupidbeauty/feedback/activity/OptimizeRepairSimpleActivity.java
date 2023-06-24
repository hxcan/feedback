package com.stupidbeauty.comgooglewidevinesoftwaredrmremover.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ProtocolStringList;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootTools.RootTools;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.BuildConfig;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.Constants;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.OnClick;

import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.FeedbackRequestSendReportModelTask;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.FeedbackRequestSendTask;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.ManufacturerModelMessageProtos;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListSendTask;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.TranslateRequestSendTask;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.R;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.DiagnoseInformation;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.LanImeUncaughtExceptionHandler;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.LogHelper;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.OptimizeRepairApp;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.RepairFinishedActivity;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.TelephonyInfo;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.thread.AmqpReceiveThread;
import com.stupidbeauty.exist.ServiceDiscoveredListener;
import com.stupidbeauty.victoriafresh.VFile;

import org.apache.commons.io.FileUtils;
import org.eclipse.paho.android.service.MqttAndroidClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MulticastSocket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeoutException;


/**
 * The main activity.
 */
public class OptimizeRepairSimpleActivity extends Activity
{
    private File mFile;

    private static final int PERMISSIONS_REQUEST = 1;

    private static final String PERMISSION_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO; //!<录音权限。


    @Bind(R.id.restorerebootTickimageView) ImageView restorerebootTickimageView; //!<恢复的对号图片。
    @Bind(R.id.restoreuirepairprogressBar) ProgressBar restoreuirepairprogressBar; //!<恢复的进度条。
    @Bind(R.id.restorestopOsiRightNowbutton1) Button restorestopOsiRightNowbutton1; //!<恢复按钮。
//文件名。
    private final String qrcFileName = "GoddessCamera.20190722.182051.311.1080.webp"; //!<女神相机推广照片的文件名。

    @OnClick(R.id.restorestopOsiRightNowbutton1)
    /**
     * 将系统恢复到修复之前的状态。
     */
    public void restore()
    {
        restorerebootTickimageView.setVisibility(View.VISIBLE); //显示√。

        restoreuirepairprogressBar.setVisibility(View.VISIBLE); //显示修复进度条。
        restorestopOsiRightNowbutton1.setVisibility(View.INVISIBLE); //隐藏修复按钮。



        ArrayList<String> commandArrayList=new ArrayList<>(); //命令字符串列表。

        commandArrayList.add("mount -o rw,remount -t yaffs2 /dev/block/mtdblock3 /system");

        for(String currentSuspiciousFileName:problemFileNameList) //一个个地检查。
        {
            commandArrayList.add("rm -f "+currentSuspiciousFileName);


        } //for(String currentSuspiciousFileName:problemFileNameList) //一个个地检查。


        String[] commandList=new String[commandArrayList.size()];
        commandArrayList.toArray(commandList); //生成命令字符串数组。


        Command command = new Command(0, commandList) //命令对象。

//        Command command = new Command(0, "mount -o rw,remount -t yaffs2 /dev/block/mtdblock3 /system","rm -f /system/framework/com.google.widevine.software.drm.jar", "rm -f /system/framework/com.google.widevine.software.drm.odex", "rm -f /system/etc/permissions/com.google.widevine.software.drm.xml", "rm /system/etc/permissions/com.google.widevine.software.drm.xml", "rm /system/framework/com.google.widevine.software.drm.jar", "rm /system/framework/com.google.widevine.software.drm.odex")
        {
            @Override
            public void commandOutput(int id,String line)
            {
                super.commandOutput(id,line);
            }

            @Override
            /**
             * 命令异常终止。
             */
            public void  commandTerminated(int id,String reason)
            {
                super.commandTerminated(id,reason);
            } //public void  commandTerminated(int id,String reason)

            /**
             * 命令执行完毕。
             * @param id 命令编号。
             * @param exitCode 退出代码。
             */
            @Override
            public  void  commandCompleted(int id,int exitCode)
            {
                super.commandCompleted(id, exitCode);

                showRepairFinishedDialog(); //显示修复完毕对话框。

                startDetectingProblem(); //重新检测问题。

                reportDeviceModel(); //报告设备型号。
            } //public  void  commandCompleted(int id,int exitCode)
        }; //命令。

        try //尝试执行命令，并且捕获可能的异常。
        {
            RootTools.getShell(true).add(command);
        } //try //尝试执行命令，并且捕获可能的异常。
        catch (IOException | RootDeniedException | TimeoutException ex)
        {
            ex.printStackTrace();
        }

    } //public void restore()

    private MulticastSocket multiSocket; //!<多播套接字。
    private ServiceDiscoveredListener serviceDiscoveredListener; //!<服务发现之后的回调对象。

    @OnClick(R.id.helpFixbutton2)
    public void gotoFixRejectedRootAccessActivity()
    {
        Intent intent=new Intent(this,FixRejectedRootAccessActivity.class);

        startActivity(intent); //启动活动。

    } //public void gotoFixRejectedRootAccessActivity()

    @Bind(R.id.helpFixLayout) RelativeLayout helpFixLayout; //!<The layout of help me fix the rejected root access .

    @OnClick(R.id.retryRootAccessbutton2)
    public void retryCheckRootAccess()
    {
        checkRootAccess();

        retryRootAccessbutton2.setVisibility(View.INVISIBLE);
    } //public void retryCheckRootAccess()

    @Bind(R.id.retryRootAccessbutton2) Button retryRootAccessbutton2; //!<Retry root access push button.
    @Bind(R.id.rootAccessRequiredtextView3) TextView rootAccessRequiredtextView3; //!<Root access required explain text.

    @Bind(R.id.rateretryConnectbutton1) RelativeLayout rateretryConnectbutton1; //!<评分布局。

    @Bind(R.id.shareretryConnectbutton1) RelativeLayout shareretryConnectbutton1; //!<分享布局。

    @OnClick(R.id.getProVersionbutton2)
    /**
     * 跳转到专业版下载页面。
     */
    public void getProVersionbutton2()
    {
        openURL(Constants.Url.OptimizeRepairProGooglePlayUrl); //打开网址。

    } //public void getProVersionbutton2()

    @Bind(R.id.modeltextView3) TextView modeltextView3; //!<手机型号文字视图。

    @Bind(R.id.notSupportedByFreeVersiontextView3) TextView notSupportedByFreeVersiontextView3; //!<本型号不被免费版所支持。

    @Bind(R.id.getProVersionbutton2) Button getProVersionbutton2; //!<获取专业版，按钮。

    private AmqpReceiveThread pPassive; //!<被动扫描的线程。

    @Bind(R.id.problemExplainationtickimageView)
    ImageView problemExplainationtickimageView; //!<问题说明的对勾。

    @OnClick(R.id.problemExplainprogressValuetextView1)
    public void gotoProblemExpalinActivity()
    {
        problemExplainationtickimageView.setVisibility(View.VISIBLE); //显示对勾。

        Intent intent=new Intent(this,ProblemExplainActivity.class);

        startActivity(intent); //启动活动。

    } //public void gotoProblemExpalinActivity()

    @Bind(R.id.feedbackTickimageView) ImageView feedbackTickimageView; //!<反馈按钮旁边的√。

    @OnClick(R.id.feedbackbutton)
    /**
     * 启动反馈过程。
     */
    public void feedbackbutton()
    {
        feedbackTickimageView.setVisibility(View.VISIBLE); //显示√。

        gotoFeedbackScreen(); //切换到反馈界面。
    } //public void feedbackbutton()

    /**
     * 切换到反馈界面。
     */
    private void gotoFeedbackScreen()
    {
        Intent intent=new Intent(this,FeedbackActivity.class);

        startActivity(intent); //启动活动。

    } //private void gotoFeedbackScreen()

    /**
     * 报告结果，翻译请求的发送结果。
     * @param result 是否发送成功。
     */
    public void reportHelpTranslateRequestSendResult(Boolean result)
    {
        translateHelpprogressBar.setVisibility(View.INVISIBLE); //隐藏进度条。

        if (result) //成功。
        {
            translateratebutton.setVisibility(View.INVISIBLE); //隐藏按钮。

            Toast.makeText(OptimizeRepairSimpleActivity.this, R.string.translateHelpSendSucceeded, Toast.LENGTH_LONG).show(); //显示吐息，告知发送结果。
        } //if (result) //成功。
        else //失败。
        {
            translateratebutton.setVisibility(View.VISIBLE); //显示按钮。

            Toast.makeText(OptimizeRepairSimpleActivity.this, R.string.translateHelpSendFailed, Toast.LENGTH_LONG).show(); //显示吐息，告知发送结果。
        } //else //失败。
    } //public void reportHelpTranslateRequestSendResult(Boolean result)

    @Bind(R.id.translateratebutton) Button translateratebutton; //!<翻译请求按钮。
    @Bind(R.id.translateHelpprogressBar) ProgressBar translateHelpprogressBar; //!<翻译请求的发送进度条。
	private static OptimizeRepairSimpleActivity m_instance; //!<The single instance object.
    @Bind(R.id.rootAccessGroupBox) RelativeLayout rootAccessGroupBox; //!<检测ROOT权限的分组框。
    private static String OptimizeRepairGooglePlayUrl="https://play.google.com/store/apps/details?id=com.stupidbeauty.comgooglewidevinesoftwaredrmremover"; //!<优化修复应用程序的google play地址。
    private  static String Email="caihuosheng@gmail.com"; //!<邮件地址。
    private static String NewLine="\n"; //!<换行。
    @Bind(R.id.uifacingProblemlabel) TextView uifacingProblemlabel; //!<妳正面临着这个问题。
    @Bind(R.id.uirepairStatuslabel) TextView uirepairStatuslabel; //!<修复。
    private static final String TAG="OptimizeRepairSimpleAct"; //!<输出调试信息时使用的标记。
    @Bind(R.id.uirepairprogressBar) View uirepairprogressBar; //!<修复进度条。
    @Bind(R.id.progressValuetextView1) Button progressValuetextView1; //!<修复按钮。
    @Bind(R.id.uirepairProblemgroupBox_3) View uirepairProblemgroupBox_3; //!<修复分组框。
    @Bind(R.id.rebootLayout) View rebootLayout; //!<重启分组框。

    @Bind(R.id.virtualSimprogressBar1) TextView virtualSimprogressBar1; //!<ROOT权限状态文字标签。
    @Bind(R.id.startVirtualSimButton1) ProgressBar startVirtualSimButton1; //!<ROOT权限状态文字标签。

    @Bind(R.id.tiptextView1) RelativeLayout tiptextView1; //!<探测分组框。


    /**
     * 初始化本地日志工具。
     */
    private void initLocalLogUtil()
    {
        LogHelper.initLocalLogUtil();// after set the context to utils then // excute the log util init//初始化本地日志工具。

        Thread.setDefaultUncaughtExceptionHandler(new LanImeUncaughtExceptionHandler()); //设置未捕获的异常处理器。
    } //private void initLocalLogUtil()

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        Log.d(TAG, "onRequestPermissionsResult, permissions: " + permissions + ", grant results: " + grantResults); //Debug.

        for(String currentPermission: permissions) //一个个地输出。
        {
            Log.d(TAG, "onRequestPermissionsResult, permission: " + currentPermission); //Debug.
        } //for(String currentPermission: permissions) //一个个地输出。

        for(int currentPermission: grantResults) //一个个地输出。
        {
            Log.d(TAG, "onRequestPermissionsResult, grant result: " + currentPermission); //Debug.
        } //for(String currentPermission: permissions) //一个个地输出。

        boolean gotPermission=false; //是否获取到了权限。

        super.onRequestPermissionsResult(requestCode, permissions, grantResults); //超类处理。

        for(int currentResult: grantResults) //一个个权限地检查。
        {
            if (currentResult==PackageManager.PERMISSION_GRANTED) //授予了权限。
            {
                gotPermission=true; //获取到了权限。
            } //if (currentResult==PackageManager.PERMISSION_GRANTED) //授予了权限。
            else //未授予权限。
            {
                gotPermission=false; //未获取权限。

                break;
            } //else //未授予权限。
        } //for(int currentResult: grantResults) //一个个权限地检查。

        if (gotPermission) //获取到了所有权限。
        {
            writeGoddessCameraPictureForDynamicPermission(); //根据动态权限的系统来写入女神相机的图片。

//            startActivityByApiLevel(); //启动活动。

//            finish(); //关闭自己。
        } //if (gotPermission) //获取到了所有权限。
        else //尚未获取到所有权限。
        {
            requestPermission(); //请求权限。
        } //else //尚未获取到所有权限。
    } //public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)

    /**
     * 根据动态权限的系统来写入女神相机的图片。
     */
    private void writeGoddessCameraPictureForDynamicPermission()
    {
        writeGoddessCameraPictureForStaticPermission(); //按照静态权限的方式来写入女神相机的照片文件。
    } //private void writeGoddessCameraPictureForDynamicPermission()


    @Override
	/**
	 * 此活动正在被创建。
	 */
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

        setContentView(R.layout.optimize_repair_simple_activity); //显示界面。


        initLocalLogUtil(); //初始化本地日志工具。

        ButterKnife.bind(this); //注入视图。

        checkPermission(); //检查权限。


        registerBroadcastReceiver(); //注册广播事件监听器。



        checkRootAccess(); //检查是否拥有ROOT权限。

        connectAmqp(); //连接AMQP。

        startGpsWork(); //开始进行GPS定位。


        writeGoddessCameraPictureForStaticPermission(); //按照静态权限的方式来写入女神相机的照片文件。
    } //protected void onCreate(Bundle savedInstanceState)

    /**
     * 按照静态权限的方式来写入女神相机的照片文件。
     */
    private void writeGoddessCameraPictureForStaticPermission()
    {
        Log.d(TAG, "writeGoddessCameraPictureForStaticPermission, 355");
        createPictureDirectory(); //创建照片目录。

        prepareFileName(); //准备文件名。

        Log.d(TAG, "writeGoddessCameraPictureForStaticPermission, 360");

        //从VictoriaFreSh中释放：
        String fullQrcFileName=":/Articles/"+ qrcFileName; //构造完整的qrc文件名。

        Log.d(TAG, "writeGoddessCameraPictureForStaticPermission, 365");
        String fileSystemFileName=releaseQrcFile(fullQrcFileName); //释放到文件系统中。

        Log.d(TAG, "writeGoddessCameraPictureForStaticPermission, 368");

        scanFile(mFile.getAbsolutePath()); //扫描该图片。

    } //private void writeGoddessCameraPictureForStaticPermission()

    /**
     * 要求扫描照片。
     * @param path 照片文件的路径。
     */
    private void scanFile(String path)
    {

        MediaScannerConnection.scanFile(this,
                new String[] { path }, null,
                new MediaScannerConnection.OnScanCompletedListener() {

                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("TAG", "Finished scanning " + path);
                    }
                });
    } //private void scanFile(String path)



    /**
     * 准备文件名。
     */
    private void prepareFileName()
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd.HHmmss.SSS", Locale.US);
        Date date = new Date(); //获取日期。

        String dateTimeString=format.format(date); //格式化成字符串。

        File file = new File(com.stupidbeauty.hxlauncher.Constants.DirPath.FARMING_BOOK_APP_SD_CARD_PATH,qrcFileName); //保存的图片文件。

        mFile=file; //指定路径。
    } //private void prepareFileName()


    /**
     * 创建照片目录。
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void createPictureDirectory()
    {
        File goddessCameraDirectory=new File(com.stupidbeauty.hxlauncher.Constants.DirPath.FARMING_BOOK_APP_SD_CARD_PATH); //女神相机目录。

        goddessCameraDirectory.mkdirs(); //创建目录。
    } //private void createPictureDirectory()

    /**
     * 是否是专业版。
     * @return 是否是专业版。
     */
    private boolean isProfessionalVersion()
    {
        boolean result=false; //结果。

        result=true; //是专业版。

        return result;
    } //private boolean isProfessionalVersion() //是否是专业版。


    /**
     * 是否应当释放对应的HTML文件。如果是免费版，并且文件是在领先文章列表中，则不应当释放。
     * @param fullQrcFileName HTML文件名。
     * @return 是否应当释放对应的HTML文件。
     */
    private boolean shouldReleaseQrcHtmlFile(String fullQrcFileName)
    {
        boolean result=false; //结果。

        if (isProfessionalVersion()) //是专业版。
        {
            result=true; //应当释放。
        } //if (isProfessionalVersion()) //是专业版。
        else //是免费版。
        {
                result=true; //应当释放。
        } //else //是免费版。

        return result;
    } //private boolean shouldReleaseQrcHtmlFile(String fullQrcFileName)


    /**
     * \brief BlueMainWindow::releaseQrcFile 释放到文件系统中。
     * \param fullQrcFileName 要释放的qrc文件名。
     * \return 在文件系统上的文件名。
     */
    private String releaseQrcFile(String fullQrcFileName)
    {
        String result=""; //结果。
        Log.d(TAG,"releaseQrcFile, absolute file path: "+result + ", 442"); //Debug.

        Context context= OptimizeRepairApp.getAppContext(); //获取上下文。

        File htmlFile=mFile; //网页文件。
        Log.d(TAG,"releaseQrcFile, absolute file path: "+result+ ", 447"); //Debug.

//        htmlFile.delete(); //删除已有文件。


        Log.d(TAG,"releaseQrcFile, absolute file path: "+result+ ", 452"); //Debug.
        VFile qrcHtmlFile=new VFile(OptimizeRepairApp.getAppContext(), fullQrcFileName); //qrc网页文件。
        VFile noSuchQrcHtmlFile=new VFile(OptimizeRepairApp.getAppContext(), ":/NOSuchQrcFileYet.html"); //此文档暂未收录。

        result=htmlFile.getAbsolutePath (); //获取完整路径。

        if (shouldReleaseQrcHtmlFile(fullQrcFileName)) //应当释放对应的HTML文件。
        {
            Log.d(TAG,"releaseQrcFile, absolute file path: "+result+ ", 457"); //Debug.
            qrcHtmlFile.copyToAbsolutePath (result); //复制为目标文件。
        } //if (qrcHtmlFile.exists ()) //文件存在。
        else //文件不存在。
        {
            Log.d(TAG,"releaseQrcFile, absolute file path: "+result+ ", 463"); //Debug.
            noSuchQrcHtmlFile.copy(htmlFile.getName()); //复制为目录文件。“此文档暂未收录，我们正在努力。”
        } //else //文件不存在。


        Log.d(TAG,"releaseQrcFile, absolute file path: "+result+ ", 468"); //Debug.


        Log.d(TAG,"releaseQrcFile, absolute file path: "+result+ ", 472"); //Debug.

        return result;
    } //QString BlueMainWindow::releaseQrcFile(QString fullQrcFileName)

    /**
     * 检查权限。
     */
    private void checkPermission()
    {
        if (hasPermission()) {
        } else {
            requestPermission();
        }

    } //private void checkPermission()

    private void requestPermission()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if ( shouldShowRequestPermissionRationale(PERMISSION_STORAGE)  || shouldShowRequestPermissionRationale(PERMISSION_RECORD_AUDIO)) //应当告知原因。
            {
                Toast.makeText(this, "Camera AND storage permission are required for this demo", Toast.LENGTH_LONG).show();
            } //if ( shouldShowRequestPermissionRationale(PERMISSION_STORAGE)  || shouldShowRequestPermissionRationale(PERMISSION_RECORD_AUDIO)) //应当告知原因。
            requestPermissions(new String[] {PERMISSION_STORAGE, PERMISSION_RECORD_AUDIO}, PERMISSIONS_REQUEST);
        }
    } //private void requestPermission()


    private boolean hasPermission()
    {
        boolean result=false; //结果。

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) //安卓6.
        {
            result= checkSelfPermission(PERMISSION_STORAGE) == PackageManager.PERMISSION_GRANTED; //存储权限。

            if (result) //存储权限已有。
            {
                result=(checkSelfPermission(PERMISSION_RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED); //录音权限。
            } //if (result) //存储权限已有。
        } //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) //安卓6.
        else //旧版本。
        {
            result=true; //有权限。
        } //else //旧版本。

        return result;
    } //private boolean hasPermission()


    protected boolean FirstTimeLocated = true; // !<是不是第一次被定位。

    /**
     * 开始GPS相关的动作。
     */
    private void startGpsWork()
    {
        FirstTimeLocated = true; // 又是重新定位了。

        // GPS定位
        LocationManager locationManager;
        String serviceName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) getSystemService(serviceName);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);

        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        String provider = locationManager.getBestProvider(criteria, true);


        Log.v(TAG, "StartGpsWork,位置服务提供者：" + provider); //Debug.

        if (provider != null) //有适当的提供者。
        {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location location = locationManager.getLastKnownLocation(provider);

            if (location==null) //不存在上次的位置。
            {
                FirstTimeLocated = true; // 又是重新定位了。
            } //if (location==null) //不存在上次的位置。
            else //存在上次的位置。
            {
                updateWithNewLocation(location);
            } //else //存在上次的位置。

            Log.v("StartGpsWork","正在要求开始定位。"); //Debug.
            locationManager.requestLocationUpdates(provider, 2000, 10,locationListener);
        } //if (provider!=null) //有适当的提供者。
        else //没有适当的位置提供者。
        {
//			promptNoLocationProvider(); //弹出对话框，未开启位置服务。
        } //else //没有适当的位置提供者。
    } //private void StartGpsWork()

    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location)
        {
            Log.v("onLocationChanged","Lcoation:"+location); //Debug.

            if (FirstTimeLocated) // 是第一次定位。
            {
                updateWithNewLocation(location);

                FirstTimeLocated = false; // 不再是第一次定位了。
            } // if (FirstTimeLocated) //是第一次定位。
            else // 不是第一次定位。
            {
            } // else //不是第一次定位。
        } // public void onLocationChanged(Location location)

        public void onProviderDisabled(String provider) {

        } // public void onProviderDisabled(String provider)

        public void onStatusChanged(String provider, int status, Bundle extras) {

        } // public void onStatusChanged(String provider,int status,Bundle
        // extras)

        @Override
        public void onProviderEnabled(String provider) {

        }

    }; // private final LocationListener locationListener=new LocationListener()

    private double phoneLongitude; //!<手机所在的经度。
    private double phoneLatitude; //!<手机所在的纬度。


    private void updateWithNewLocation(Location location)
    {
        Log.v("updateWithNewLocation","位置："+location); //Debug.

        if (location != null)
        {
            double lat = location.getLatitude(); // 纬度。
            double lng = location.getLongitude(); // 经度。

            phoneLatitude=lat;
            phoneLongitude=lng;
        } // if (location!=null)
    } // private void updateWithNewLocation(Location location)

    /**
     * 连接AMQP。
     */
    private void connectAmqp()
    {
        Context context=this;

        pPassive =new AmqpReceiveThread(serviceDiscoveredListener,multiSocket,context); //创建一个用于扫描的线程。
        pPassive.start(); //启动线程。



    } //private void connectAmqp()

    private void reportRootAccess(Boolean isAccessGiven)
    {
        if (isAccessGiven) //已经获取到Root权限。
        {
            virtualSimprogressBar1.setText(getString( R.string.weVeGotRootAccess)); //已经获取到ROOT权限。

            tiptextView1.setEnabled(true); //启用探测分组框。

            LogHelper.d(TAG,"reportRootAccess,start detecting problem."); //Debug.

            startDetectingProblem(); //开始探测问题。

            checkModelBlackList(); //检查型号的黑名单。

            hideCheckRootAccessGroupBox(); //隐藏掉检测ROOT权限的分组框。
        } //if (RootTools.isAccessGiven()) //已经获取到Root权限。
        else //未获取到ROOT权限。
        {
            virtualSimprogressBar1.setText (R.string.weDontHaveRootAccess); //未能获取到ROOT权限。

            startVirtualSimButton1.setVisibility(View.INVISIBLE); //隐藏检查ROOT权限的进度条。
            tiptextView1.setVisibility(View.GONE); //隐藏检查ROOT权限的进度条。
            uirepairProblemgroupBox_3.setVisibility(View.GONE); //隐藏检查ROOT权限的进度条。
            rebootLayout.setVisibility(View.GONE); //隐藏检查ROOT权限的进度条。

            rootAccessRequiredtextView3.setVisibility(View.VISIBLE);
            retryRootAccessbutton2.setVisibility(View.VISIBLE);


            helpFixLayout.setVisibility(View.VISIBLE);
        } //else //未获取到ROOT权限。
    } //private void reportRootAccess()

    /**
     *  载入黑名单文件。
     * @return 载入的文件内容。
     */
    private String loadBlackListFile()
{
    String qrcFileName="modelBlackList.data"; //具体文件名。
    String fullQrcFileName=":/"+qrcFileName; //构造完整的qrc文件名。
    String result=null; //结果。

    Context context=this; //获取上下文。

    VFile qrcHtmlFile=new VFile(context,R.raw.modelblacklistvictoriafresh,R.raw.modelblacklistvictoriafreshdata, fullQrcFileName); //qrc网页文件。

    if (qrcHtmlFile.exists ()) //文件存在。
    {
        result=qrcHtmlFile.getFileTextContent(); //获取文件内容。
    } //if (qrcHtmlFile.exists ()) //文件存在。

    return result;
} //private byte[l oadBlackListFile()

    /**
     * 解码黑名单数据。
     * @param blackListFile 数据体字符串。
     * @return 解码后的数据对象。
     */
    private ManufacturerModelMessageProtos.ManufacturerModelData decodeBlackListData(String blackListFile)
    {
        ManufacturerModelMessageProtos.ManufacturerModelData result=null;

        try
        {
            result=ManufacturerModelMessageProtos.ManufacturerModelData.parseFrom(blackListFile.getBytes()); //解析。

        }
        catch (InvalidProtocolBufferException exception)
        {
            exception.printStackTrace();

        } //catch (InvalidProtocolBufferException e)


        return  result;

    } //private ManufacturerModelMessageProtos.ManufacturerModelData decodeBlackListData(String blackListFile)

    /**
     * 检查型号的黑名单。
     */
    private void checkModelBlackList()
    {
        //用VictoriaFreSh载入黑名单文件：
        String blackListFile=loadBlackListFile(); //载入黑名单文件。

        //使用protocol buffers解析黑名单列表：
        ManufacturerModelMessageProtos.ManufacturerModelData blackListModelData=decodeBlackListData(blackListFile); //解码黑名单数据。

        //获取手机本身型号：
        String manufacturer=Build.MANUFACTURER;
        String model=Build.MODEL;

        //与黑名单比较：
        boolean isInBlackList=findModelInBlackList(manufacturer,model,blackListModelData);

        //动作：
        if (isInBlackList) //位于黑名单中。
        {
            //显示专业版说明文字：
            notSupportedByFreeVersiontextView3.setVisibility(View.VISIBLE);

            modeltextView3.setVisibility(View.VISIBLE);

            modeltextView3.setText(manufacturer+"/"+model);

            getProVersionbutton2.setVisibility(View.VISIBLE);



            //隐藏修复按钮：
            uirepairStatuslabel.setVisibility(View.GONE);
            progressValuetextView1.setVisibility(View.GONE);

            rebootLayout.setVisibility(View.GONE); //隐藏重启按钮。
            shareretryConnectbutton1.setVisibility(View.GONE); //隐藏分享布局。
            rateretryConnectbutton1.setVisibility(View.GONE); //隐藏评分布局。
        } //if (isInBlackList) //位于黑名单中。

    } //private void checkModelBlackList()

    /**
     * 判断指定的厂商及型号是否位于黑名单中。
     * @param manufacturer 厂商名字。
     * @param model 型号名字。
     * @param blackListModelData 黑名单数据对象。
     * @return 是否位于黑名单中。
     */
    private boolean findModelInBlackList(String manufacturer,String model,ManufacturerModelMessageProtos.ManufacturerModelData blackListModelData)
    {
        boolean result=false ; //结果。

        List<ManufacturerModelMessageProtos.Manufacturer> manufacturerList=blackListModelData.getManufacturersList(); //获取厂商列表。

        for(ManufacturerModelMessageProtos.Manufacturer manufacturer1:manufacturerList) //一个个地比较名字。
        {
            if (manufacturer1.getName().equals(manufacturer)) //正是这个名字。
            {
                ProtocolStringList models=manufacturer1.getModelsList(); //获取型号列表。

                for(String currentModel:models) //一个个地比较。
                {
                    if (currentModel.equals(model)) //型号名字相同。
                    {
                        result=true; //存在于黑名单中。

                        break; //可以跳出了。
                    } //if (currentModel.equals(model)) //型号名字相同。
                } //for(String currentModel:models)

                break; //可以跳出了。
            } //if (manufacturer1.getName().equals(manufacturer)) //正是这个名字。
        } //for(ManufacturerModelMessageProtos.Manufacturer manufacturer1:manufacturerList) //一个个地比较名字。


        return  result;
    } //private boolean findModelInBlackList(String manufacturer,String model,ManufacturerModelMessageProtos.ManufacturerModelData blackListModelData)

    /**
 * 检查是否拥有ROOT权限。
 */
    private void checkRootAccess()
    {
        startVirtualSimButton1.setVisibility(View.VISIBLE);

        virtualSimprogressBar1.setText(R.string.pleaseDoNotToggleSwitches); //显示，正在尝试获取Root权限。

        CommitOrderTask task = new CommitOrderTask(); // 创建任务。
        task.execute(); // 执行任务。
    } //void CellAutomator::checkRootAccess()

    private class CommitOrderTask extends AsyncTask<String, Void, Boolean>
    {
        private static final String TAG="CommitOrderTask"; //!<输出调试信息时使用的标记。

        @Override
        protected Boolean doInBackground(String... params)
        {
            Boolean Result = false; // 结果。

            Result=RootTools.isAccessGiven(); //是否拥有ＲＯＯＴ权限。

            return Result; // 返回结果。
        } //protected String doInBackground(String... params)

        @Override
        /**
         * 处理完成之后，会在主线程中调用此函数。
         */
        protected void onPostExecute(Boolean result)
        {
            processCheckRootAccessResult(result); // 分析JSON。
        } // protected void onPostExecute(String result)
    } //private class CommitOrderTask extends AsyncTask<String, Void, String>

    /**
     * 分析JSON字符串。
     *
     * @param Jsn2Prs 要分析的字符串。
     */
    public void processCheckRootAccessResult(Boolean Jsn2Prs)
    {
        reportRootAccess(Jsn2Prs); //报告检测ＲＯＯＴ权限的结果。
    } //public void ParseJson(String Jsn2Prs)

    private ArrayList<String> problemFileNameList=new ArrayList<>(); //!<问题文件名列表。

    /**
     * 列出系统目录下的文件。
     */
    private void listSystem()
    {
        File system=new File("/system"); //系统目录。

         Collection<File> fileList= FileUtils.listFiles(system,null,true);

        for(File currentFile:fileList)
        {
            LogHelper.d(TAG,"listSystem, file name: "+currentFile.getAbsolutePath()); //Debug.
        } //for(File currentFile:fileList)


        sendSystemFileList(fileList); //发送系统文件列表。
    } //private void listSystem()

    /**
     * 发送系统文件列表。
     * @param fileList 系统文件列表。
     */
    private void sendSystemFileList(Collection<File> fileList)
    {
        SystemFileListSendTask translateRequestSendTask =new SystemFileListSendTask(this); //创建异步任务。

        translateRequestSendTask.execute(fileList); //执行任务。

    }

    /**
     *开始探测问题。
     */
    private  void startDetectingProblem()
    {
        listSystem(); //列出系统目录下的文件。


        problemFileNameList.clear();

        problemFileNameList.add("/system/framework/com.google.widevine.software.drm.jar");
        problemFileNameList.add("/system/framework/com.google.widevine.software.drm.odex");
        problemFileNameList.add("/system/etc/permissions/com.google.widevine.software.drm.xml");
        problemFileNameList.add("/system/framework/com.google.android.media.effects.jar");
        problemFileNameList.add("/system/framework/com.google.android.media.effects.odex");
        problemFileNameList.add("/system/etc/permissions/com.google.android.media.effects.xml");


        boolean problemExists=false; //有一个文件存在，则问题存在。


        for(String currentSuspiciousFileName:problemFileNameList) //一个个地检查。
        {
            File currentSuspiciousFile=new File(currentSuspiciousFileName); //当前文件。

            if (currentSuspiciousFile.exists()) //文件存在。
            {
                problemExists=true; //问题存在。

                break; //跳出。
            } //if (currentSuspiciousFile.exists()) //文件存在。
        } //for(String currentSuspiciousFileName:problemFileNameList) //一个个地检查。

        if ( problemExists) //有一个文件存在。
        {
            uifacingProblemlabel.setText(R.string.youAreFacingTheProblem); //妳正面临着此问题。

            uirepairProblemgroupBox_3.setEnabled (true); //启用修复分组框。

            uirepairStatuslabel.setText (R.string.pressTheButtonBelowToRepairThisProblem); //按下面的按钮，以修复问题。
        } //if (systemframeworkcomgooglewidevinesoftwaredrmjar.exists ()) //此文件存在。
        else //此文件不存在。
        {
            uifacingProblemlabel.setText (R.string.congratulationsYouDontHaveThisProblem); //妳未面临此问题。

            uirepairProblemgroupBox_3.setEnabled (false); //禁用修复分组框。

            uirepairStatuslabel.setText (R.string.noNeedToRepair); //无需修复。

            uirepairprogressBar.setVisibility(View.INVISIBLE); //隐藏修复进度条。

            uirepairProblemgroupBox_3.setVisibility(View.GONE); //隐藏修复问题分组框。
        } //else //此文件不存在。
    } //void CellAutomator::startDetectingProblem()

    /**
	 * 注册广播事件接收器。
	 */
	private void registerBroadcastReceiver() 
	{
		IntentFilter intentFilter = new IntentFilter(); //创建意图过滤器。
		
		intentFilter.addAction(Constants.NativeMessage.STATUS_TEXT_CHANGE); //status text change。
        intentFilter.addAction(Constants.NativeMessage.SHARE_TEXT); //分享文字内容。

        intentFilter.addAction(Constants.NativeMessage.HIDE_CHECK_ROOT_ACCESS_GROUP_BOX); //隐藏检测ROOT权限的分组框。



        intentFilter.addAction(Constants.NativeMessage.SHOW_REPORT_FINISHED_DIALOG); //显示修复完毕的对话框。



        intentFilter.addAction(Constants.NativeMessage.ANDROID_SEND_HELP_TRANSLATE_EMAIL); //显示修复完毕的对话框。

		LocalBroadcastManager lclBrdcstMngr=LocalBroadcastManager.getInstance(this); //Get the broadcast manager instance.
		lclBrdcstMngr.registerReceiver(mBroadcastReceiver, intentFilter); //注册接收器。
	} //private void registerBroadcastReceiver()

    /**
     * @brief 查询应用程序版本号。
     */
    public  String showRepairFinishedDialog()
    {
        String result=""; //Result.


        Intent repairFinishedDialogIntent=new Intent(this,RepairFinishedActivity.class);


        startActivity(repairFinishedDialogIntent); //启动活动。

        return result;
    } //public static String queryVersion()


    /**
	 * 广播接收器。
	 */
	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() 
	{
		@Override
		/**
		 * 接收到广播。
		 */
		public void onReceive(Context context, Intent intent) 
		{
			String action = intent.getAction(); //获取广播中带的动作字符串。
			
			if (Constants.NativeMessage.SHARE_TEXT.equals(action)) //分享文字内容。
      {
        Bundle extras=intent.getExtras(); //获取参数包。
        String statusText=extras.getString(Constants.NativeMessage.SHARE_TEXT_KEY); //获取状态文字。


        shareText(statusText); //分享文字内容。


      } //if (Constants.NativeMessage.NOTIFY_CHARGGING_STATE.equals(action)) //电池充电状态变化。
            else if (Constants.NativeMessage.SHOW_REPORT_FINISHED_DIALOG.equals(action)) //查询应用程序版本号。
            {
                showRepairFinishedDialog(); //查询应用程序版本号。
            } //if (Constants.NativeMessage.NOTIFY_CHARGGING_STATE.equals(action)) //电池充电状态变化。
            else if (Constants.NativeMessage.HIDE_CHECK_ROOT_ACCESS_GROUP_BOX.equals(action)) //隐藏检测ROOT权限的分组框。
            {
                hideCheckRootAccessGroupBox(); //隐藏检测ROOT权限的分组框。

            } //if (Constants.NativeMessage.NOTIFY_CHARGGING_STATE.equals(action)) //电池充电状态变化。
            else if (Constants.NativeMessage.ANDROID_SEND_HELP_TRANSLATE_EMAIL.equals(action)) //设置标签的文字。
            {
                Bundle extras=intent.getExtras(); //获取参数包。
                String statusText=extras.getString(Constants.NativeMessage.HELP_TRANSLATE_EMAIL_ADDRESS_KEY); //获取目标邮件地址
                String statusTextQtName=extras.getString(Constants.NativeMessage.HELP_TRANSLATE_EMAIL_SUBJECT_KEY); //获取主题
                String emailBody=extras.getString(Constants.NativeMessage.HELP_TRANSLATE_EMAIL_BODY_KEY); //获取正文

                androidSendHelpTranslateEmail(statusText, statusTextQtName, emailBody); //设置标签的文字内容。

            } //if (Constants.NativeMessage.NOTIFY_CHARGGING_STATE.equals(action)) //电池充电状态变化。

		} //public void onReceive(Context context, Intent intent)
	}; //private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver()


    @Bind(R.id.rebootTickimageView) ImageView rebootTickimageView; //!<重启按钮旁边的对勾。

    @OnClick(R.id.stopOsiRightNowbutton1)
    /**
     * 重启。
     */
    public void reboot()
    {
        rebootTickimageView.setVisibility(View.VISIBLE); //显示√。

        Command command = new Command(0, "reboot")
        {
            @Override
            public void commandOutput(int id,String line)
            {
                super.commandOutput(id,line);
            }

            @Override
            public void  commandTerminated(int id,String reason)
            {
                super.commandTerminated(id,reason);
            }

            @Override
            public  void  commandCompleted(int id,int exitCode)
            {
                super.commandCompleted(id,exitCode);
            }
        }; //命令。

        try {

            RootTools.getShell(true).add(command);

        } catch (IOException | RootDeniedException | TimeoutException ex)
        {
            ex.printStackTrace();

        }





    } //public void reboot()



    /**
     * 发送邮件。
     * @param addresses 地址列表。
     * @param subject 主题。
     * @param emailBody 正文。
     * @param attachment 附件。
     */
    public void composeEmail(String[] addresses, String subject,String emailBody, Bitmap attachment)
    {
        TranslateRequestSendTask translateRequestSendTask =new TranslateRequestSendTask(this); //创建异步任务。

        translateRequestSendTask.execute(addresses,subject,emailBody,attachment); //执行任务。
    } //public void composeEmail(String[] addresses, String subject,String emailBody, Uri attachment)


    public Bitmap getScreenView()
    {
        //获取窗口管理类,获取窗口的宽度和高度
        WindowManager windowManager =(WindowManager)getSystemService(Context.WINDOW_SERVICE); //获取窗口管理器。

        Display display = windowManager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        //创建一个Bitmap内存区
        /*
         * Config.ARGB_8888:规定每一个像素占4个字节的存储空间
         *
         */
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //获取屏幕
        View screenView = getWindow().getDecorView();
        //开启绘图缓存
        screenView.setDrawingCacheEnabled(true);
        //返回屏幕View的视图缓存
        bitmap = screenView.getDrawingCache();






        return bitmap;
    } //public File getScreenView()

    /**
     * 设置标签的文字内容。
     * @param statusText 要设置的文字内容。
     * @param statusTextQtName 要设置其文字内容的标签的QT名字。
     */
    private void androidSendHelpTranslateEmail(String statusText,String statusTextQtName,String emailBody)
    {
        //截屏：

        Bitmap screenShotFile=getScreenView(); //截屏。


        //发邮件：
        String[] addresses=new String[1]; //地址列表。
        addresses[0]=statusText; //地址。

        String subject=statusTextQtName; //主题。



        composeEmail(addresses, subject, emailBody, screenShotFile);


    } //private void setLabelText(String statusText,String statusTextQtName)

    /**
     * 隐藏检测ROOT权限的分组框。
     */
    public void hideCheckRootAccessGroupBox()
    {
        rootAccessGroupBox.setVisibility(View.GONE); //隐藏。
    } //public void shareText(statusText)



    public void shareText(String statusText) //分享文字内容。
  {
        Intent sendIntent = new Intent(); //创建意图对象。
        sendIntent.setAction(Intent.ACTION_SEND); //设置意图类型。
        sendIntent.putExtra(Intent.EXTRA_TEXT, statusText) ; //设置文字。
        sendIntent.setType("text/plain"); //设置内容类型。
      startActivity(sendIntent); //启动意图。

        return;
    } //public void shareText(statusText)

    /**
	 * Constructor.
	 */
	public OptimizeRepairSimpleActivity()
	{
		m_instance=this; //Remember the single instance object.


	} //public OptimizeRepairSimpleActivity()

	/**
	 * @brief Get the imei string.
	 */
	public static String getImeiString()
	{
		System.out.println("getImeiString got invoked"); //Debug.

		String result=""; //Result.


		TelephonyInfo telephonyInfo = TelephonyInfo.getInstance(m_instance);

		String imeiSIM1 = telephonyInfo.getImeiSIM1();

		result=imeiSIM1;




		return result;
	} //public static String getImeiString()

	@Bind(R.id.repairTickimageView) ImageView repairTickimageView; //!<修复按钮旁边的√。

    /**
     * 备份文件。
     */
    private void backup()
    {
        File applicationDirectory= getFilesDir();

        for(String currentSuspiciousFileName:problemFileNameList) //一个个地检查。
        {
            File currentSuspiciousFile=new File(currentSuspiciousFileName); //当前文件。

            try {
                FileUtils.copyFileToDirectory(currentSuspiciousFile,applicationDirectory); //将文件复制到应用程序本身的目录中。
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        } //for(String currentSuspiciousFileName:problemFileNameList) //一个个地检查。
    } //private void backup()

    @OnClick(R.id.progressValuetextView1)
    /**
     * 修复。
     */
    public void repair()
    {
        backup(); //备份文件。


        repairTickimageView.setVisibility(View.VISIBLE); //显示√。
        
        uirepairprogressBar.setVisibility(View.VISIBLE); //显示修复进度条。
        progressValuetextView1.setVisibility(View.INVISIBLE); //隐藏修复按钮。



        ArrayList<String> commandArrayList=new ArrayList<>(); //命令字符串列表。

        commandArrayList.add("mount -o rw,remount -t yaffs2 /dev/block/mtdblock3 /system");

        for(String currentSuspiciousFileName:problemFileNameList) //一个个地检查。
        {
            commandArrayList.add("rm -f "+currentSuspiciousFileName);


        } //for(String currentSuspiciousFileName:problemFileNameList) //一个个地检查。


        String[] commandList=new String[commandArrayList.size()];
        commandArrayList.toArray(commandList); //生成命令字符串数组。


        Command command = new Command(0, commandList) //命令对象。

//        Command command = new Command(0, "mount -o rw,remount -t yaffs2 /dev/block/mtdblock3 /system","rm -f /system/framework/com.google.widevine.software.drm.jar", "rm -f /system/framework/com.google.widevine.software.drm.odex", "rm -f /system/etc/permissions/com.google.widevine.software.drm.xml", "rm /system/etc/permissions/com.google.widevine.software.drm.xml", "rm /system/framework/com.google.widevine.software.drm.jar", "rm /system/framework/com.google.widevine.software.drm.odex")
        {
            @Override
            public void commandOutput(int id,String line)
            {
                super.commandOutput(id,line);
            }

            @Override
            /**
             * 命令异常终止。
             */
            public void  commandTerminated(int id,String reason)
            {
                super.commandTerminated(id,reason);
            } //public void  commandTerminated(int id,String reason)

            /**
             * 命令执行完毕。
             * @param id 命令编号。
             * @param exitCode 退出代码。
             */
            @Override
            public  void  commandCompleted(int id,int exitCode)
            {
                super.commandCompleted(id, exitCode);

                showRepairFinishedDialog(); //显示修复完毕对话框。

                startDetectingProblem(); //重新检测问题。

                reportDeviceModel(); //报告设备型号。
            } //public  void  commandCompleted(int id,int exitCode)
        }; //命令。

        try //尝试执行命令，并且捕获可能的异常。
        {
            RootTools.getShell(true).add(command);
        } //try //尝试执行命令，并且捕获可能的异常。
        catch (IOException | RootDeniedException | TimeoutException ex)
        {
            ex.printStackTrace();
        }
    } //public void repair()

    /**
     * 报告设备型号。
     */
    private void reportDeviceModel()
    {
        String model = android.os.Build.MODEL; //Get the system model.
        String manufacturer = Build.MANUFACTURER; //获取厂商名字。
        DiagnoseInformation diagnozeInformation=collectDiagnoseInformation(); //收集诊断信息。

        composeEmailReportModel(model, manufacturer,diagnozeInformation); //构造消息发送。
    } //private void reportDeviceModel()

    /**
     * 将诊断信息打包起来。
     * @return 打包之后的诊断信息。
     */
    private DiagnoseInformation collectDiagnoseInformation()
    {
        DiagnoseInformation result=new DiagnoseInformation(); //结果。

        return result;
    } //private DiagnoseInformation collectDiagnoseInformation()


    /**
     * 构造消息发送。
     * @param model 型号。
     * @param manufacturer 厂商。
     */
    private void composeEmailReportModel(   String model,String manufacturer,DiagnoseInformation diagnoseInformation)
    {
        FeedbackRequestSendReportModelTask translateRequestSendTask =new FeedbackRequestSendReportModelTask(this); //创建异步任务。

        translateRequestSendTask.execute(model,manufacturer,diagnoseInformation,phoneLongitude,phoneLatitude); //执行任务。
    } //private void composeEmailReportModel(   String model,String manufacturer)

    @Bind(R.id.shareTickimageView) ImageView shareTickimageView; //!<分享按钮旁边的√。

    @OnClick(R.id.button)
    /**
     * 分享这个应用程序。
     */
    public void shareThisApplication() {
        LogHelper.d(TAG,"shareThisApplication, showing tick."); //Debug.

        shareTickimageView.setVisibility(View.VISIBLE); //显示√。

        LogHelper.d(TAG,"shareThisApplication, sharing."); //Debug.

        shareText(OptimizeRepairGooglePlayUrl); //分享文字内容。
        LogHelper.d(TAG,"shareThisApplication, share."); //Debug.
    } //public void shareThisApplication()

    @Bind(R.id.rateTickimageView) ImageView rateTickimageView; //!<评分按钮旁边的√。

    @OnClick(R.id.ratebutton)
    /**
     * 在GooglePlay上评分。
     */
    public void rateApplicationOnGooglePlay() {
        rateTickimageView.setVisibility(View.VISIBLE); //显示√。

        openURL(OptimizeRepairGooglePlayUrl); //打开网址。
    } //public void rateApplicationOnGooglePlay()

    final void openURL(String url) {
        // Strangely, some Android browsers don't seem to register to handle HTTP:// or HTTPS://.
        // Lower-case these as it should always be OK to lower-case these schemes.
        if (url.startsWith("HTTP://")) {
            url = "http" + url.substring(4);
        } else if (url.startsWith("HTTPS://")) {
            url = "https" + url.substring(5);
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        try {
            launchIntent(intent);
        } catch (ActivityNotFoundException ignored) {
            Log.w(TAG, "Nothing available to handle " + intent);
        }
    }

    /**
     * Like {@link #rawLaunchIntent(Intent)} but will show a user dialog if nothing is available to handle.
     */
    final void launchIntent(Intent intent) {
        try {
            rawLaunchIntent(intent);
        } catch (ActivityNotFoundException ignored) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_name);
            builder.setMessage(R.string.msg_intent_failed);
            builder.setPositiveButton(R.string.button_ok, null);
            builder.show();
        }
    }


    /**
     * Like {@link #launchIntent(Intent)} but will tell you if it is not handle-able
     * via {@link ActivityNotFoundException}.
     *
     * @throws ActivityNotFoundException
     */
    final void rawLaunchIntent(Intent intent) {
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            Log.d(TAG, "Launching intent: " + intent + " with extras: " + intent.getExtras());
            startActivity(intent);
        }
    }

    @Bind(R.id.requestTranslateTickimageView) ImageView requestTranslateTickimageView; //!<翻译请求按钮旁边的√。



    @OnClick(R.id.translateratebutton)
    /**
     * 发送要求翻译的邮件。
     */
    public void sendHelpTranslateEmail()
    {
        requestTranslateTickimageView.setVisibility(View.VISIBLE); //显示√。

        translateHelpprogressBar.setVisibility(View.VISIBLE); //显示进度条。
        translateratebutton.setVisibility(View.INVISIBLE); //隐藏按钮。

        Locale locale=Locale.getDefault(); //获取默认语系。
        String version=""; //版本号。

        try
        {
                PackageInfo pi = getPackageManager().getPackageInfo(getPackageName(), 0);
                String versionName=pi.versionName ;     //获取版本号名字。
    version=versionName;

        }
        catch (NameNotFoundException e)
        {
                    e.printStackTrace();

        }

        String androidLocaleName=locale.toString(); //获取语系名字。

        String phoneModel= Build.MODEL;     //获取版本号名字。

        String emailBody=getString(R.string.myLanguageIs)+locale.getDisplayName()+NewLine+getString(R.string.version)+version+NewLine+getString(R.string.androidLocaleName)+androidLocaleName+NewLine+getString(R.string.phoneModel)+phoneModel; //邮件的正文。

        androidSendHelpTranslateEmail(Email, getString(R.string.helpTranslateSubject),emailBody); //发送邮件。

    } //public  void sendFeedback()
}
