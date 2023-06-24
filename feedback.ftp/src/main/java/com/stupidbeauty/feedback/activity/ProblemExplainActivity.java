package com.stupidbeauty.comgooglewidevinesoftwaredrmremover.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebView.HitTestResult;
import android.widget.ProgressBar;

import com.huiti.clipcms.SbWebViewClientInterface;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.R;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.LogHelper;
import com.stupidbeauty.sbrowserandroid.SbWebViewClient;
import com.stupidbeauty.victoriafresh.VFile;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * 浏览器主界面。
 */
public class ProblemExplainActivity extends Activity implements SbWebViewClientInterface
{
	private static final int TIMEOUT = 30000;
	private static final int SHOW_PROGRESS_DIALOG = 0;
	private static final int DISMISS_PROGRESS_DIALOG = 1;
	private static final int SHOW_CUSTOM_404_VIEW = 2;
	// ===========================================================
	// Fields
	// ===========================================================
	private static final String TAG = "ProblemExplainActivity"; //!<输出调试信息时使用的标记。
	
	@Bind(R.id.load_progress) ProgressBar mProgressBar; //!<载入网页时的进度条。
	
	@Bind (R.id.tab_more_service_webview) WebView mService_webview; //!<用于载入网页的主要网页视图。
		
	// ===========================================================
	// Constructors
	// ===========================================================
	private boolean mIsRequestError = false;
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	


	@OnLongClick (R.id.tab_more_service_webview)
	/**
	 * 生成上下文菜单。
	 */
	public boolean generateContextMenu()
	{
		boolean result=true; //结果。
		
        HitTestResult hitTestResult = mService_webview.getHitTestResult();  
        if (null == hitTestResult) //未命中。
        {
        	result= false;
        } //if (null == hitTestResult) //未命中。
        else //命中。
        {
            int type = hitTestResult.getType();  
            if (type == WebView.HitTestResult.UNKNOWN_TYPE) //未知类型。
            {
            	result= false;  
            	
            } //if (type == WebView.HitTestResult.UNKNOWN_TYPE) //未知类型。
            else //已知类型。
            {
                	result= true;
            } //else //已知类型。
        } //else //命中。

		return result;
	} //public void generateContextMenu()
		
		
		
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
	
	@Override
	/**
	 * 活动被创建。
	 */
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState); //创建超类。

		requestWindowFeature(Window.FEATURE_PROGRESS); //显示进度条。

		
		setContentView(R.layout.problem_explain_activity); //设置显示内容。

		ButterKnife.bind(this); //依赖注入。
		
		setProgressBarVisibility(true); //显示进度条。



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
		
		mService_webview.setOnTouchListener(new OnTouchListener() //被点击时获取焦点。 
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
		


	} //protected void onCreate(Bundle savedInstanceState)

	@OnClick(R.id.startRepairbutton2)
	/**
	 * 开始修复。
	 */
	public void startRepairbutton2()
	{
		gotoRepairActivity(); //跳转到修复活动。

	} //public void startRepairbutton2()

	/**
	 * 跳转到修复活动。
	 */
	private void gotoRepairActivity()
	{
		Intent intent=new Intent(this,OptimizeRepairSimpleActivity.class);

		startActivity(intent); //启动活动。

	} //private void gotoRepairActivity()

	/**
	 *  释放说明文档，并且返回对应的地址。
	 * @return 在文件系统上的文件名。
     */
	private String releaseExplainDocument()
	{
		String qrcFileName="Android1043.en_US.9.15.embed.html"; //具体文件名。
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


	@Override
	/**
	 * 活动重新处于活跃状态。
	 */
	protected void onResume() 
	{
		super.onResume(); //超类继续工作。
		
		if (mService_webview != null && mIsRequestError) //网页视图存在，并且之前请求出错。 
		{
			mIsRequestError = false; //请求无错误。
			
			mService_webview.clearView(); //清空页面。
		} //if (mService_webview != null && mIsRequestError) //网页视图存在，并且请求无错误。
		
		
	} //protected void onResume()
	
	
	private WebChromeClient chromeClient = new WebChromeClient() //创建chrome客户端对象。
	{
		/**
		 * 载入进度发生变化。
		 */
		public void onProgressChanged(WebView view, int progress)// 设置 加载进程
		{
			ProblemExplainActivity.this.setProgress(progress*100); //显示进度。
			
			if (progress >= 100) //载入完毕。 
			{
				handler.sendEmptyMessage(DISMISS_PROGRESS_DIALOG); //发送消息，关闭进度对话框。
				
			} //if (progress >= 100) //载入完毕。
		} //public void onProgressChanged(WebView view, int progress)// 设置 加载进程
	}; //private WebChromeClient chromeClient = new WebChromeClient()
	
	
	@Override
	/**
	 * 按了后退键。
	 */
	public void onBackPressed()
	{
		if (mService_webview.canGoBack()) //可以后退。
		{
			webViewClient.setIsBackPressed(true); //设置，按了后退键。
			
			mService_webview.goBack(); //后退。
		} //if (mService_webview.canGoBack()) //可以后退。
		else //不可以后退。
		{
			super.onBackPressed(); //由超类处理。
		} //else //不可以后退。
	} //public void onBackPressed()
	
	private SbWebViewClient webViewClient = new SbWebViewClient(this);  //用于控制webview行为的WebViewClient对象。
	
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
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}


	/**
	 * 显示错误页面。
	 */
	public void showErrorPage() 
	{
		handler.sendEmptyMessage(SHOW_CUSTOM_404_VIEW); //显示404页面。
	
		return;
		
	} //public void showErrorPage()


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
	 * 页面载入完成。
	 */
	public void onPageFinished(WebView view, String url) 
	{

		
		handler.sendEmptyMessage(DISMISS_PROGRESS_DIALOG); //发送多线程消息。
		handler.removeCallbacks(r); //删除解除回调函数。

	} //public void onPageFinished(WebView view, String url)




	
	
}
