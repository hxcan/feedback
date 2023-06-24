package com.stupidbeauty.comgooglewidevinesoftwaredrmremover.activity;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ProtocolStringList;
import com.huiti.clipcms.SbWebViewClientInterface;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootTools.RootTools;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.Constants;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.ManufacturerModelMessageProtos;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.R;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.DiagnoseInformation;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.LanImeUncaughtExceptionHandler;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.LogHelper;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.OptimizeRepairApp;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.RepairFinishedActivity;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.TelephonyInfo;
import com.stupidbeauty.qtdocchinese.ArticleInfo;
import com.stupidbeauty.sbrowserandroid.SbWebViewClient;
import com.stupidbeauty.victoriafresh.VFile;

import org.eclipse.paho.android.service.MqttAndroidClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.ApplicationListData;


/**
 * The main activity.
 */
public class FixRejectedRootAccessActivity extends Activity implements SbWebViewClientInterface
{
    private Set<String> rootAccessManagerPackageNameSet=new HashSet<>(); //!<Root权限管理器的包名集合。

    @OnClick(R.id.launchRootAccessManagerbutton2)
    /**
     * 探测并启动Root权限管理器应用。
     */
    public void launchRootAccessManagerbutton2()
    {
        OptimizeRepairApp baseApplication= OptimizeRepairApp.getInstance(); //获取应用程序对象。
        ApplicationListData applicationListData =baseApplication.getApplicationListData(); //获取本地服务器列表数据对象。

        Log.d(TAG,"reqGameDataFromApplication, applicationListData: "+ applicationListData); //Debug.

        if (applicationListData !=null) //存在对象。
        {
            List<PackageInfo> courtList= applicationListData.getPackageInfoList(); //获取本地服务器列表。

            if (courtList!=null) //对象存在。
            {
                showInstalledPackages(courtList); //显示已安装的应用程序列表。
            } //if (courtList!=null) //对象存在。
        } //if (applicationListData!=null) //存在对象。


    } //public void launchRootAccessManagerbutton2()

    /**
     * 显示软件包列表。
     * @param packageInfoList 软件包列表。
     */
    void showInstalledPackages(List<PackageInfo>  packageInfoList)
    {
        String Result = ""; // 结果。

        String maskFileName=""; //获取掩码图片文件名。

        ArrayList<ArticleInfo> articleInfoArrayList = new ArrayList<>();

        PackageManager packageManager=getPackageManager(); //获取软件包管理器。
        for (PackageInfo packageInfo:packageInfoList) //一个个地遍历。
        {
            String packageName=packageInfo.packageName; //获取软件包名字。

            LogHelper.d(TAG,"showInstalledPackages, package name: "+packageName); //Debug.

            Intent launchIntent= packageManager.getLaunchIntentForPackage(packageName); //获取当前软件包的启动意图。

            if (launchIntent!=null) //有启动意图。
            {
                ApplicationInfo applicationInfo=packageInfo.applicationInfo; //获取应用程序信息。
                CharSequence applicationLabel=packageManager.getApplicationLabel(applicationInfo); //获取应用程序的文字。
                LogHelper.d(TAG,"showInstalledPackages, application label: "+applicationLabel); //Debug.

                if (rootAccessManagerPackageNameSet.contains(packageName)) //包名是位于权限管理器包名集合中。
                {



                    ArticleInfo currentApplication=new ArticleInfo(); //创建应用程序信息对象。
                    currentApplication.setApplicationLabel(applicationLabel); //设置应用程序文字。
                    currentApplication.setLaunchIntent(launchIntent); //设置启动意图。
                    currentApplication.setPackageName(packageName); //设置包名。

                    articleInfoArrayList.add(currentApplication); //添加应用。

                    startActivity(launchIntent); //启动活动。

                    break; //跳出。
                } //if (rootAccessManagerPackageNameSet.contains(packageName)) //包名是位于权限管理器包名集合中。

            } //else //有启动意图。
        } //for (PackageInfo packageInfo:packageInfoList) //一个个地遍历。
    } //void showInstalledPackages(List<PackageInfo>  packageInfoList)

    private WebChromeClient chromeClient = new WebChromeClient() //创建chrome客户端对象。
    {
        /**
         * 载入进度发生变化。
         */
        public void onProgressChanged(WebView view, int progress)// 设置 加载进程
        {
            FixRejectedRootAccessActivity.this.setProgress(progress*100); //显示进度。

            if (progress >= 100) //载入完毕。
            {
                handler.sendEmptyMessage(DISMISS_PROGRESS_DIALOG); //发送消息，关闭进度对话框。

            } //if (progress >= 100) //载入完毕。
        } //public void onProgressChanged(WebView view, int progress)// 设置 加载进程
    }; //private WebChromeClient chromeClient = new WebChromeClient()

    private static final int TIMEOUT = 30000;

    private Runnable r = new Runnable() {

        @Override
        public void run() {

            if (mService_webview.getProgress() < 100)
            {
                mProgressBar.setVisibility(View.GONE);
                mIsRequestError = true;

            }

        }
    };

    private boolean mIsRequestError = false;

    private static final int SHOW_CUSTOM_404_VIEW = 2;

    @Bind(R.id.load_progress) ProgressBar mProgressBar; //!<载入网页时的进度条。

    private static final int SHOW_PROGRESS_DIALOG = 0;


    /**
     * handler处理消息机制
     */
    protected Handler handler = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case SHOW_PROGRESS_DIALOG:
                    mProgressBar.setVisibility(View.VISIBLE);

                    break;
                case DISMISS_PROGRESS_DIALOG:
                    mProgressBar.setVisibility(View.GONE);
                    break;
                case SHOW_CUSTOM_404_VIEW:
                    mProgressBar.setVisibility(View.GONE);
                    mIsRequestError = true;

                    break;
            }
        }
    };


    private static final int DISMISS_PROGRESS_DIALOG = 1;


    /**
     * 页面载入完成。
     */
    public void onPageFinished(WebView view, String url)
    {


        handler.sendEmptyMessage(DISMISS_PROGRESS_DIALOG); //发送多线程消息。
        handler.removeCallbacks(r); //删除解除回调函数。

    } //public void onPageFinished(WebView view, String url)


    /**
     * 页面开始载入。
     */
    public void onPageStarted(WebView view, String url, Bitmap favicon)
    {
        handler.sendEmptyMessage(SHOW_PROGRESS_DIALOG);

        handler.postDelayed(r, TIMEOUT);

        return;
    } //public void onPageStarted(WebView view, String url, Bitmap favicon)


    /**
     * 弹出编写邮件界面。
     * @param addresses 目标地址。
     */
    public void composeEmail(String addresses)
    {
        Intent intent = new Intent(Intent.ACTION_SENDTO); //创建意图，只能由邮件应用处理。

        intent.setData(Uri.parse(addresses)); //设置数据，只有邮件应用会处理这个。

        intent.putExtra(Intent.EXTRA_EMAIL, addresses); //目标邮件地址。


        if (intent.resolveActivity(getPackageManager()) != null) //当前手机上安装了邮件地址。
        {
            startActivity(intent); //启动目标活动。
        } //if (intent.resolveActivity(getPackageManager()) != null) //当前手机上安装了邮件地址。
    } //public void composeEmail(String addresses)


    @Override
    /**
     * 显示错误页面。
     */
    public void showErrorPage()
    {
        handler.sendEmptyMessage(SHOW_CUSTOM_404_VIEW); //显示404页面。

        return;

    } //public void showErrorPage()


    @Override
    /**
     * 发起电话呼叫。
     * @param phoneNumberUrl 要呼叫的电话号码。
     */
    public void dialPhoneNumber(String phoneNumberUrl)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL); //创建意图。

        intent.setData(Uri.parse(phoneNumberUrl)); //设置数据。

        if (intent.resolveActivity(getPackageManager()) != null) //有电话拨号应用。
        {
            startActivity(intent); //启动意图。
        } //if (intent.resolveActivity(getPackageManager()) != null) //有电话拨号应用。


        return;
    } //public void dialPhoneNumber(String phoneNumberUrl)


    private SbWebViewClient webViewClient = new SbWebViewClient(this);  //用于控制webview行为的WebViewClient对象。


    @Bind (R.id.forgetRootAccessRejectionWebView) WebView mService_webview; //!<用于载入网页的主要网页视图。


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
        if (result) //成功。
        {
            Toast.makeText(FixRejectedRootAccessActivity.this, R.string.translateHelpSendSucceeded, Toast.LENGTH_LONG).show(); //显示吐息，告知发送结果。
        } //if (result) //成功。
        else //失败。
        {
            Toast.makeText(FixRejectedRootAccessActivity.this, R.string.translateHelpSendFailed, Toast.LENGTH_LONG).show(); //显示吐息，告知发送结果。
        } //else //失败。
    } //public void reportHelpTranslateRequestSendResult(Boolean result)

	private static FixRejectedRootAccessActivity m_instance; //!<The single instance object.
    @Bind(R.id.rootAccessGroupBox) RelativeLayout rootAccessGroupBox; //!<检测ROOT权限的分组框。
    private static String OptimizeRepairGooglePlayUrl="https://play.google.com/store/apps/details?id=com.stupidbeauty.comgooglewidevinesoftwaredrmremover"; //!<优化修复应用程序的google play地址。
    private  static String Email="caihuosheng@gmail.com"; //!<邮件地址。
    private static String NewLine="\n"; //!<换行。
    @Bind(R.id.uifacingProblemlabel) TextView uifacingProblemlabel; //!<妳正面临着这个问题。
    private static final String TAG="OptimizeRepairSimpleAct"; //!<输出调试信息时使用的标记。

    @Bind(R.id.virtualSimprogressBar1) TextView virtualSimprogressBar1; //!<ROOT权限状态文字标签。
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
	/**
	 * 此活动正在被创建。
	 */
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

        setContentView(R.layout.fix_rejected_root_access_activity); //显示界面。


        initLocalLogUtil(); //初始化本地日志工具。

        ButterKnife.bind(this); //注入视图。

        registerBroadcastReceiver(); //注册广播事件监听器。

        startGpsWork(); //开始进行GPS定位。

        showHowToForgetRejectionOfRootAccessForOptimizeRepair(); //显示帮助文件内容，如何撤销掉已拒绝的ROOT权限。

        initializeRootAccessManagerPackageNameSet(); //初始化Root权限管理器包名集合。
    } //protected void onCreate(Bundle savedInstanceState)

    /**
     * 初始化Root权限管理器包名集合。
     */
    private void initializeRootAccessManagerPackageNameSet()
    {
        //SuperSU:
        rootAccessManagerPackageNameSet.add("eu.chainfire.supersu"); //SuperSU.


    } //private void initializeRootAccessManagerPackageNameSet()

    /**
     * 从意图中获取网址。
     * @return 网址。
     */
    private String getUrlFromIntent()
    {
        String result=null; //结果。

        Intent urlIntent=getIntent(); //获取意图。

        Log.d(TAG,"getUrlFromIntent,action:"+urlIntent.getAction()); //Debug.



        Uri dataUri=urlIntent.getData(); //获取数据URI。

        try //解析成字符串，并且捕获可能的异常。
        {
            result=dataUri.toString(); //Debug.
        } //try //解析成字符串，并且捕获可能的异常。
        catch (NullPointerException e) //捕获空指针异常。
        {
            e.printStackTrace(); //报告错误。
        } //catch (NullPointerException e) //捕获空指针异常。



        Log.d(TAG,"getUrlFromIntent,url:"+result); //Debug.

        return result;
    }


    /**
     * 显示帮助文件内容，如何撤销掉已拒绝的ROOT权限。
     */
    private void showHowToForgetRejectionOfRootAccessForOptimizeRepair()
    {
        mService_webview.getSettings().setJavaScriptEnabled(true); //启用javascript.
        mService_webview.getSettings().setSupportMultipleWindows(true); //支持多窗口浏览。
        mService_webview.getSettings().setBuiltInZoomControls(true); //启用内置缩放。
        mService_webview.setWebViewClient(webViewClient); //设置网页视图客户端。
        mService_webview.setWebChromeClient(chromeClient); //设置chrome客户端。


        String url = ""; //账户URL。


        url=getUrlFromIntent(); //从意图中获取网址。

        url=releaseExplainDocument(); //释放说明文档，并且返回对应的地址。

        url="file://"+url; //拼接file前缀。

        resotreLoginSessionCookie(url); //恢复登录会话小甜饼。



        mService_webview.loadUrl(url); //开始载入页面。

        mService_webview.setOnTouchListener(new View.OnTouchListener() //被点击时获取焦点。
        {

            @Override
            /**
             * 被点击。
             */
            public boolean onTouch(View v, MotionEvent event)
            {
                mService_webview.requestFocus(); //获取焦点。
                return false; //不消化掉这个事件。
            } //public boolean onTouch(View v, MotionEvent event)
        }); //mService_webview.setOnTouchListener(new OnTouchListener() //被点击时获取焦点。


    } //private void showHowToForgetRejectionOfRootAccessForOptimizeRepair()

    /**
     * 恢复登录会话的小甜饼。
     * @param url 对应的URL。
     */
    private void resotreLoginSessionCookie(String url)
    {
        CookieSyncManager.createInstance(this); //创建小甜饼同步管理器。

        CookieSyncManager.getInstance().sync(); //同步小甜饼。

        return;
    } //private void resotreLoginSessionCookie(String url)


    /**
     *  释放说明文档，并且返回对应的地址。
     * @return 在文件系统上的文件名。
     */
    private String releaseExplainDocument()
    {
        String qrcFileName="HowToForgetRejectionOfRootAccessForOptimizeRepair.zh_CN.html"; //具体文件名。
        String fullQrcFileName=":/Articles/"+qrcFileName; //构造完整的qrc文件名。
        String result=""; //结果。

        Context context=this; //获取上下文。

        File htmlFile=new File(context.getFilesDir(),"Goddezz.htm"); //网页文件。

        htmlFile.delete(); //删除已有文件。


        VFile qrcHtmlFile=new VFile(context, fullQrcFileName); //qrc网页文件。
        VFile noSuchQrcHtmlFile=new VFile(context, ":/NOSuchQrcFileYet.html"); //此文档暂未收录。

        if (qrcHtmlFile.exists ()) //文件存在。
        {
            qrcHtmlFile.copy (htmlFile.getName()); //复制为目标文件。
        } //if (qrcHtmlFile.exists ()) //文件存在。
        else //文件不存在。
        {
            noSuchQrcHtmlFile.copy(htmlFile.getName()); //复制为目录文件。“此文档暂未收录，我们正在努力。”
        } //else //文件不存在。



        result=htmlFile.getAbsolutePath (); //获取完整路径。

        LogHelper.d(TAG,"releaseQrcFile, absolute file path: "+result); //Debug.


        //释放CSS文件：
        File cssFile=new File("AdjustHtml.css"); //样式单文件。

        cssFile.delete (); //删除已有文件。

        VFile qrcCssFile=new VFile(context, ":/AdjustHtml.css"); //qrc样式单文件。

        qrcCssFile.copy (cssFile.getName()); //复制为目标文件。



        return result;
    } //private String releaseExplainDocument()


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

    private MqttAndroidClient client; //!<MQTT客户端。

    private String discoverNearbyStadiumResponseTopic; //!<剪辑列表查询结果主题。

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

            tiptextView1.setVisibility(View.GONE); //隐藏检查ROOT权限的进度条。
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

    /**
     *开始探测问题。
     */
    private  void startDetectingProblem()
    {
        File systemframeworkcomgooglewidevinesoftwaredrmjar=new File("/system/framework/com.google.widevine.software.drm.jar"); //要检查的文件。
        File comgooglewidevinesoftwaredrmodex=new File("/system/framework/com.google.widevine.software.drm.odex"); //要检查的文件。
        File systemetcpermissionscomgooglewidevinesoftwaredrmxml=new File("/system/etc/permissions/com.google.widevine.software.drm.xml"); //要检查的文件。

        LogHelper.d(TAG, "startDetectingProblem,jar exists?:" + systemframeworkcomgooglewidevinesoftwaredrmjar.exists() + ",odex exists?:" + comgooglewidevinesoftwaredrmodex.exists() + ",xml exists?:" + systemetcpermissionscomgooglewidevinesoftwaredrmxml.exists()); //Debug.

        boolean problemExists=systemframeworkcomgooglewidevinesoftwaredrmjar.exists () || comgooglewidevinesoftwaredrmodex.exists () || systemetcpermissionscomgooglewidevinesoftwaredrmxml.exists (); //有一个文件存在，则问题存在。

        if ( problemExists) //有一个文件存在。
        {
            uifacingProblemlabel.setText(R.string.youAreFacingTheProblem); //妳正面临着此问题。

        } //if (systemframeworkcomgooglewidevinesoftwaredrmjar.exists ()) //此文件存在。
        else //此文件不存在。
        {
            uifacingProblemlabel.setText (R.string.congratulationsYouDontHaveThisProblem); //妳未面临此问题。

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

        System.out.println("showRepairFinishedDialog got invoked"); //Debug.

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
     * 将bitmap保存到内存卡中
     * @param bitmap
     */
    private File saveBitmapToSdcard(Bitmap bitmap){
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/AutoCrop.png");

        FileOutputStream out = null;
        try{
            out = new FileOutputStream(file);
            if(bitmap != null){
                /*
                 * 三个参数的含义分别是：
                 * 1.保存图片的格式
                 * 2.标识图片质量0~100.质量越小压缩的越小（这里设置100标识不压缩）。另外如果图片是png格式，压缩是无损的，将忽略此参数（设置无效）
                 * 3.向OutputStream写入图片数据
                 */
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(out != null){
                    out.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        return file;
    }



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



    } //private void setLabelText(String statusText,String statusTextQtName)

    /**
     * 隐藏检测ROOT权限的分组框。
     */
    public void hideCheckRootAccessGroupBox()
    {
        rootAccessGroupBox.setVisibility(View.GONE); //隐藏。

        return;
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
	public FixRejectedRootAccessActivity()
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

	/**
	 * @brief Show toast.
	 */
	public static String showToast(final String toastString)
	{

		System.out.println("showToast got invoked"); //Debug.

		String result=""; //Result.


		m_instance.runOnUiThread
                (
                        new Runnable() {


                            public void run() {
                                Toast.makeText(m_instance, toastString, Toast.LENGTH_LONG).show();   //show toast as requested.

                            }
                        }
                );



		return result;
	}

    /**
     * 修复。
     */
    public void repair()
    {

        Command command = new Command(0, "mount -o rw,remount -t yaffs2 /dev/block/mtdblock3 /system","rm -f /system/framework/com.google.widevine.software.drm.jar", "rm -f /system/framework/com.google.widevine.software.drm.odex", "rm -f /system/etc/permissions/com.google.widevine.software.drm.xml", "rm /system/etc/permissions/com.google.widevine.software.drm.xml", "rm /system/framework/com.google.widevine.software.drm.jar", "rm /system/framework/com.google.widevine.software.drm.odex")
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
                Log.d(TAG,"commandTerminated,reason:"+reason); //Debug.

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
                LogHelper.d(TAG,"commandCompleted,exit code:"+exitCode); //Debug.
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
        String model = Build.MODEL; //Get the system model.
        String manufacturer = Build.MANUFACTURER; //获取厂商名字。
        DiagnoseInformation diagnozeInformation=collectDiagnoseInformation(); //收集诊断信息。

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
}
