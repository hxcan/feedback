package com.stupidbeauty.feedback.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
// import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.Constants;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.FeedbackRequestSendTask;
import com.stupidbeauty.feedback.R2;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.DiagnoseInformation;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.LanImeBaseDef;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.LogHelper;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.OrApplicationLog;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.ZipInterruptedException;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.ZipUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * The main activity.
 */
public class FeedbackActivity extends Activity
{
  @BindView(R2.id.emailtextFeedbackeditText) EditText emailtextFeedbackeditText; //!<电子邮件输入框。

    /**
     * 压缩处理文件夹下的文件，并删除存在的压缩文件
     * @throws ZipInterruptedException zip过程被中断。
     * */
    private void zipFolder(File dirFile) throws ZipInterruptedException
    {
        if (dirFile.exists()) //目录存在。
        {
            File[] files = dirFile.listFiles(); //列出所有文件。

            if(files == null) //空白。
            {
                return; //跳出。
            } //if(files == null) //空白。

            File zipFile = new File(dirFile.getParentFile(), dirFile.getName() + ".zip"); //压缩后得到的压缩包。
            LogHelper.d(TAG, "zipFile = " + zipFile.getPath()); //Debug.

            ArrayList<File> fileList = new ArrayList<File>(); //创建文件信息列表。

            for (int i = 0; i < files.length; i++) //一个个文件信息地加入列表中。
            {
                fileList.add(files[i]); //添加当前文件。
            } //for (int i = 0; i < files.length; i++) //一个个文件信息地加入列表中。

            try //尝试压缩，并捕获可能的异常。
            {
                ZipUtils.zipFiles(fileList, zipFile); //压缩文件。
            } //try //尝试压缩，并捕获可能的异常。
            catch (IOException e)  //捕获输入输出异常。
            {
                e.printStackTrace(); //报告错误。
            } //catch (IOException e)  //捕获输入输出异常。
        } //if (dirFile.exists()) //目录存在。
    } //private void zipFolder(File dirFile)


    private boolean hasBeanNoteSuccess = false; //!<是否已经提示成功完成。

    public static final int LOG_PREPARE_SUCCESS = 1;
    private static final int RESET_GLOBAL_PROGRESS=2; //!<重置全局进度值为0.

    public static interface OnGmateLogPrepareListener {
        void onGmateLogPrepareState(int state);
    }

    private OnGmateLogPrepareListener mLogPrepareListener;

    private ProgressDialog mProgressDlg;

    private int mCurrentProgressValue = -1;

    /**
     * 更新进度值。
     * @param progress 新的进度值。
     */
    protected void updateProgressValue(int progress)
    {
        mCurrentProgressValue = progress; //记录。

        if (mProgressDlg != null) //进度对话框存在。
        {
            mProgressDlg.setProgress(progress); //设置新的进度值。
        } //if (mProgressDlg != null) //进度对话框存在。
    } //protected void updateProgressValue(int progress)



    class ZIPHandler extends Handler {

        public ZIPHandler(Looper looper) {
            super(looper);
        }

        @Override
        /**
         * 处理消息。
         */
        public void handleMessage(Message msg)
        {
            switch (msg.what) //根据消息的代码来做不同的处理。
            {
                case UPDATE_ZIP_PROGRESS: //更新ZIP压缩的进度值。
                    long progress = (Long) msg.obj; //获取进度值。
                    int updateValue = (int) progress / 2 + 50; //换算成全局的进度值。

                    LogHelper.d(TAG,"handleMessage 即将更新进度值："+updateValue); //Debug.


                    updateProgressValue(updateValue); //更新进度值。

                    if (updateValue >= 99) //进度值达到99了。
                    {
                        LogHelper.d("readlog", "handleMessage updateValue >= 99"); //Debug.
                        mCurrentProgressValue = 0; //进度值归零。
                        if (mProgressDlg != null) //对话框存在。
                        {
                            mProgressDlg.cancel(); //取消对话框。
                            mProgressDlg = null; //释放对话框。
                        } //if (mProgressDlg != null) //对话框存在。

                        // ==通知UI界面压缩完成 调用第三方邮件发送
                        if (mLogPrepareListener != null && !hasBeanNoteSuccess) //监听器存在，之前未提示成功完成。
                        {
                            hasBeanNoteSuccess = true; //记录，提示成功完成了。
                            mLogPrepareListener.onGmateLogPrepareState(LOG_PREPARE_SUCCESS); //通知监听器，准备完毕。
                        } //if (mLogPrepareListener != null && !hasBeanNoteSuccess) //监听器存在，之前未提示成功完成。
                    } //if (updateValue >= 99) //进度值达到99了。

                    break; //跳出。

                case RESET_GLOBAL_PROGRESS: //重置全局进度值为0 。
                    LogHelper.d(TAG,"handleMessage 即将更新进度值："+0); //Debug.


                    updateProgressValue(0); //更新进度值。

                    break; //跳出。

                default: //默认情况。
                    break; //跳出。
            } //switch (msg.what) //根据消息的代码来做不同的处理。
        } //public void handleMessage(Message msg)
    }


    private Handler mHandler; //!<ZIP压缩处理器。
    private static final int UPDATE_ZIP_PROGRESS = 1; //!<更新ZIP压缩的进度值。
    private Thread mZipTask;

    @BindView(R2.id.sendFeedbackuirepairprogressBar) ProgressBar sendFeedbackuirepairprogressBar; //!<发送反馈信息过程的进度条。
    @BindView(R2.id.sendFeedbackprogressValuetextView1) Button sendFeedbackprogressValuetextView1; //!<发送反馈信息的按钮。

    /**
     * 切换到反馈界面。
     */
    private void gotoFeedbackScreen()
    {
        Intent intent=new Intent(this,FeedbackActivity.class);

        startActivity(intent); //启动活动。

        return;
    } //private void gotoFeedbackScreen()

    /**
     * 报告结果，翻译请求的发送结果。
     * @param result 是否发送成功。
     */
    public void reportHelpTranslateRequestSendResult(Boolean result)
    {
        sendFeedbackuirepairprogressBar.setVisibility(View.INVISIBLE); //隐藏进度条。

        if (result) //成功。
        {
            Toast.makeText(FeedbackActivity.this, R2.string.feedbackSendSucceeded, Toast.LENGTH_LONG).show(); //显示吐息，告知发送结果。
        } //if (result) //成功。
        else //失败。
        {
            Toast.makeText(FeedbackActivity.this, R2.string.feedbackSendFailed, Toast.LENGTH_LONG).show(); //显示吐息，告知发送结果。
        } //else //失败。

        
        return;
    } //public void reportHelpTranslateRequestSendResult(Boolean result)



  @BindView(R2.id.rootAccessGroupBox) RelativeLayout rootAccessGroupBox; //!<检测ROOT权限的分组框。
  private static String OptimizeRepairGooglePlayUrl="https://play.google.com/store/apps/details?id=com.stupidbeauty.comgooglewidevinesoftwaredrmremover"; //!<优化修复应用程序的google play地址。
  private  static String Email="caihuosheng@gmail.com"; //!<邮件地址。
  private static String NewLine="\n"; //!<换行。
  @BindView(R2.id.uifacingProblemlabel) TextView uifacingProblemlabel; //!<妳正面临着这个问题。

  private static final String TAG="OptimizeRepairSimpleAct"; //!<输出调试信息时使用的标记。


  @BindView(R2.id.uirepairProblemgroupBox_3) View uirepairProblemgroupBox_3; //!<修复分组框。
  @BindView(R2.id.textFeedbackeditText) EditText textFeedbackeditText; //!<用户输入反馈文字的输入框。


  @BindView(R2.id.virtualSimprogressBar1) TextView virtualSimprogressBar1; //!<ROOT权限状态文字标签。
  @BindView(R2.id.startVirtualSimButton1) TextView startVirtualSimButton1; //!<ROOT权限状态文字标签。

  @BindView(R2.id.tiptextView1) RelativeLayout tiptextView1; //!<探测分组框。


  public void setGmateLogPrepareListener(OnGmateLogPrepareListener listener) 
  {
    mLogPrepareListener = listener;
  }



    @Override
	/**
	 * 此活动正在被创建。
	 */
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

    setContentView(R2.layout.activity_feedback); //显示界面。

    ButterKnife.bind(this); //注入视图。

    mHandler = new ZIPHandler(Looper.getMainLooper()); //创建ZIP压缩处理器。


    setGmateLogPrepareListener(new OnGmateLogPrepareListener() //设置事件监听器，日志准备完毕。
    {
      @Override
      /**
      * 日志准备完毕。
      */
      public void onGmateLogPrepareState(int state)
      {
        if (state == LOG_PREPARE_SUCCESS) //日志准备成功。
        {
          // if is force stop synch/zip task,do not show the email
          // dialog
          sendLogs(); //发送日志。
        } //if (state == logManager.LOG_PREPARE_SUCCESS) //日志准备成功。
      } //public void onGmateLogPrepareState(int state)
    }); //logManager.setGmateLogPrepareListener(new OnGmateLogPrepareListener() //设置事件监听器，日志准备完毕。
	} //protected void onCreate(Bundle savedInstanceState)

    /**
     * 发送日志及反馈内容。
     *  */
    private void sendLogs()
    {
        // File file = new File(LanImeBaseDef.LOG_BASE_ZIP_DIR); //压缩之后的日志文件。

        DiagnoseInformation diagnozeInformation=collectDiagnoseInformation(); //收集诊断信息。

        String feedbackFromUser=textFeedbackeditText.getText().toString(); //获取用户输入的文字内容。

        String emailAddress=emailtextFeedbackeditText.getText().toString(); //获取邮件地址。

        androidSendHelpTranslateEmail(diagnozeInformation,feedbackFromUser,emailAddress); //发送反馈信息。
    } //private void sendLogs()

    private void reportRootAccess(Boolean isAccessGiven)
    {
        if (isAccessGiven) //已经获取到Root权限。
        {
            virtualSimprogressBar1.setText(getString( R2.string.weVeGotRootAccess)); //已经获取到ROOT权限。

            tiptextView1.setEnabled(true); //启用探测分组框。

            startDetectingProblem(); //开始探测问题。

            hideCheckRootAccessGroupBox(); //隐藏掉检测ROOT权限的分组框。

        } //if (RootTools.isAccessGiven()) //已经获取到Root权限。
        else //未获取到ROOT权限。
        {
            virtualSimprogressBar1.setText (R2.string.weDontHaveRootAccess); //未能获取到ROOT权限。

            startVirtualSimButton1.setVisibility(View.INVISIBLE); //隐藏检查ROOT权限的进度条。
            tiptextView1.setVisibility(View.GONE); //隐藏检查ROOT权限的进度条。
            uirepairProblemgroupBox_3.setVisibility(View.GONE); //隐藏检查ROOT权限的进度条。

        } //else //未获取到ROOT权限。

    } //private void reportRootAccess()

    @OnClick(R2.id.sendFeedbackprogressValuetextView1)
    /**
     * 发送反馈。
     */
    public void sendFeedback()
    {
      sendFeedbackuirepairprogressBar.setVisibility(View.VISIBLE); //显示进度条。
      sendFeedbackprogressValuetextView1.setVisibility(View.INVISIBLE); //隐藏按钮。

      //启动zip任务，对日志目录进行压缩。压缩完毕之后，再启动发送过程。

      // startZipTask(); //启动zip任务。
      
      sendLogs(); // 发送日志。
    } //public  void sendFeedback()


    /**
     * 开始进行ZIP压缩。
     */
    private  void  startZipTask()
    {
        // ==start zip file
        mZipTask = new Thread(new Runnable() //创建ZIP压缩任务。
        {
            @Override
            /**
             * 线程被执行。
             */
            public void run()
            {
                Looper.prepare(); //准备事件循环。
                File log_base_dir = new File(LanImeBaseDef.LOG_BASE_DIR); //基本目录。
                LogHelper.d(TAG, "start zip file!!! "); //Debug.
                ZipUtils.setZipProgressListener(new ZipUtils.OnZipProgressListener() //设置ZIP压缩进度监听器。
                {
                    @Override
                    /**
                     * 进度值发生改变。
                     */
                    public void onZipProgressValue(long progress)
                    {
                            mHandler.removeMessages(UPDATE_ZIP_PROGRESS); //删除已有的队列消息。
                            Message mess = mHandler.obtainMessage(UPDATE_ZIP_PROGRESS, progress); //获取消息对象。
                            mHandler.sendMessage(mess); //发送消息。
                    } //public void onZipProgressValue(long progress)
                }); //ZipUtils.setZipProgressListener(new OnZipProgressListener() //设置ZIP压缩进度监听器。

                if (log_base_dir.exists()) //基准目录存在。
                {
                    ZipUtils.setZipFolder(log_base_dir); //设置要压缩的目录。

                    try // 压缩zip文件，并且捕获“zip过程被中断”异常。
                    {
                        zipFolder(log_base_dir); //压缩。

                    } //try // 压缩zip文件，并且捕获“zip过程被中断”异常。
                    catch (ZipInterruptedException e)
                    {
                        mHandler.removeMessages(UPDATE_ZIP_PROGRESS); //删除已有的队列消息。
                        Message mess = mHandler.obtainMessage(RESET_GLOBAL_PROGRESS, 0); //获取消息对象。
                        mHandler.sendMessage(mess); //发送消息。

                    } //catch (ZipInterruptedException e)
                } //if (log_base_dir.exists()) //基准目录存在。
                Looper.loop(); //开启事件循环。
                LogHelper.d(TAG, "File ZIP finish!!!"); //Debug.
            } //public void run()
        }); //mZipTask = new Thread(new Runnable() //创建ZIP压缩任务。

        mZipTask.start(); //启动ZIP压缩任务。
        // ====
    } //private  void  startZipTask()


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
     * 将日志打包起来。
     * @return 打包之后的日志。
     */
    private OrApplicationLog packageApplicationLog()
    {
        OrApplicationLog result=new OrApplicationLog(); //结果。



        return result;
    } //private OrApplicationLog packageApplicationLog()

    /**
     * 分析JSON字符串。
     *
     * @param Jsn2Prs 要分析的字符串。
     */
    public void ParseJsonCommitOrder(Boolean Jsn2Prs)
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

    boolean problemExists=systemframeworkcomgooglewidevinesoftwaredrmjar.exists () || comgooglewidevinesoftwaredrmodex.exists () || systemetcpermissionscomgooglewidevinesoftwaredrmxml.exists (); //有一个文件存在，则问题存在。

    if ( problemExists) //有一个文件存在。
    {
        uifacingProblemlabel.setText(R2.string.youAreFacingTheProblem); //妳正面临着此问题。



        uirepairProblemgroupBox_3.setEnabled (true); //启用修复分组框。
    } //if (systemframeworkcomgooglewidevinesoftwaredrmjar.exists ()) //此文件存在。
    else //此文件不存在。
    {
        uifacingProblemlabel.setText (R2.string.congratulationsYouDontHaveThisProblem); //妳未面临此问题。


        uirepairProblemgroupBox_3.setEnabled (false); //禁用修复分组框。




        uirepairProblemgroupBox_3.setVisibility(View.GONE); //隐藏修复问题分组框。

    } //else //此文件不存在。


    return;
} //void CellAutomator::startDetectingProblem()




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
      else if (Constants.NativeMessage.HIDE_CHECK_ROOT_ACCESS_GROUP_BOX.equals(action)) //隐藏检测ROOT权限的分组框。
      {
        hideCheckRootAccessGroupBox(); //隐藏检测ROOT权限的分组框。
      } //if (Constants.NativeMessage.NOTIFY_CHARGGING_STATE.equals(action)) //电池充电状态变化。
		} //public void onReceive(Context context, Intent intent)
	}; //private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver()


    /**
     * 发送反馈内容。
     * @param subject 主题。
     */
    public void composeEmail(DiagnoseInformation diagnoseInformation,  String subject,String emailAddress)
    {
      FeedbackRequestSendTask translateRequestSendTask =new FeedbackRequestSendTask(this); //创建异步任务。

      translateRequestSendTask.execute(subject, diagnoseInformation,emailAddress); //执行任务。
    } //public void composeEmail(String[] addresses, String subject,String emailBody, Uri attachment)

    /**
     * 发送反馈内容。
     */
    private void androidSendHelpTranslateEmail(DiagnoseInformation diagnoseInformation,String feedBackFromUser,String emailAddress)
    {
      composeEmail(diagnoseInformation,feedBackFromUser,emailAddress); //构造消息发送。
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
     * Like {@link #rawLaunchIntent(Intent)} but will show a user dialog if nothing is available to handle.
     */
    final void launchIntent(Intent intent) {
        try {
            rawLaunchIntent(intent);
        } catch (ActivityNotFoundException ignored) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R2.string.app_name);
            builder.setMessage(R2.string.msg_intent_failed);
            builder.setPositiveButton(R2.string.button_ok, null);
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
}
