package com.stupidbeauty.feedback.activity;

import com.stupidbeauty.voiceui.VoiceUi;
// import android.content.pm.PackageItemInfo;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
// import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
// import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
import com.stupidbeauty.feedback.R;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.DiagnoseInformation;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.LanImeBaseDef;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.LogHelper;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.OrApplicationLog;
// import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.ZipUtils;
import java.io.File;
// import java.io.IOException;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * The main activity.
 */
public class FeedbackActivity extends Activity
{
  private VoiceUi voiceUi=null; //!< 语音交互对象。
  @BindView(R2.id.emailtextFeedbackeditText) EditText emailtextFeedbackeditText; //!<电子邮件输入框。

    public static final int LOG_PREPARE_SUCCESS = 1;
    private static final int RESET_GLOBAL_PROGRESS=2; //!<重置全局进度值为0.

    private int mCurrentProgressValue = -1;

    private Handler mHandler; //!<ZIP压缩处理器。
    private static final int UPDATE_ZIP_PROGRESS = 1; //!<更新ZIP压缩的进度值。
    private Thread mZipTask;

    @BindView(R2.id.sendFeedbackuirepairprogressBar) ProgressBar sendFeedbackuirepairprogressBar; //!<发送反馈信息过程的进度条。
    @BindView(R2.id.sendFeedbackprogressValuetextView1) Button sendFeedbackprogressValuetextView1; //!<发送反馈信息的按钮。

    /**
     * 报告结果，翻译请求的发送结果。
     * @param result 是否发送成功。
     */
    public void reportHelpTranslateRequestSendResult(Boolean result)
    {
      sendFeedbackuirepairprogressBar.setVisibility(View.INVISIBLE); //隐藏进度条。

      if (result) //成功。
      {
        Toast.makeText(FeedbackActivity.this, R.string.feedbackSendSucceeded, Toast.LENGTH_LONG).show(); //显示吐息，告知发送结果。
        
        finish(); // close activity.
      } //if (result) //成功。
      else //失败。
      {
        Toast.makeText(FeedbackActivity.this, R.string.feedbackSendFailed, Toast.LENGTH_LONG).show(); //显示吐息，告知发送结果。
        
        sendFeedbackprogressValuetextView1.setVisibility(View.VISIBLE); // Show the button again.
      } //else //失败。
    } //public void reportHelpTranslateRequestSendResult(Boolean result)

  private static String OptimizeRepairGooglePlayUrl="https://play.google.com/store/apps/details?id=com.stupidbeauty.comgooglewidevinesoftwaredrmremover"; //!<优化修复应用程序的google play地址。

  @BindView(R2.id.uifacingProblemlabel) TextView uifacingProblemlabel; //!<妳正面临着这个问题。

  private static final String TAG="OptimizeRepairSimpleAct"; //!<输出调试信息时使用的标记。


  @BindView(R2.id.textFeedbackeditText) EditText textFeedbackeditText; //!<用户输入反馈文字的输入框。


  @BindView(R2.id.virtualSimprogressBar1) TextView virtualSimprogressBar1; //!<ROOT权限状态文字标签。
  @BindView(R2.id.startVirtualSimButton1) TextView startVirtualSimButton1; //!<ROOT权限状态文字标签。

  @BindView(R2.id.tiptextView1) RelativeLayout tiptextView1; //!<探测分组框。


  @Override
	/**
	 * 此活动正在被创建。
	 */
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_feedback); //显示界面。

    ButterKnife.bind(this); //注入视图。

    voiceUi=new VoiceUi(this); // 创建语音交互对象。
    
    // Chen xin
    voiceSayYourOpinion(); // Voice request, say you opinion.
	} //protected void onCreate(Bundle savedInstanceState)
	
	/**
	* Voice request, say you opinion.
	*/
	private void voiceSayYourOpinion()
	{
    String mWordSeparators = getResources().getString(R.string.sayYourOpinionled); // load explain text string. download failed

    voiceUi.say(mWordSeparators); // say , download failed.
	} // private void voiceSayYourOpinion()

  /**
  * 发送日志及反馈内容。
  */
  private void sendLogs()
  {
    // File file = new File(LanImeBaseDef.LOG_BASE_ZIP_DIR); //压缩之后的日志文件。

    DiagnoseInformation diagnozeInformation=collectDiagnoseInformation(); //收集诊断信息。

    String feedbackFromUser=textFeedbackeditText.getText().toString(); //获取用户输入的文字内容。

    String emailAddress=emailtextFeedbackeditText.getText().toString(); //获取邮件地址。

    androidSendHelpTranslateEmail(diagnozeInformation,feedbackFromUser,emailAddress); //发送反馈信息。
  } //private void sendLogs()

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
     * 将诊断信息打包起来。
     * @return 打包之后的诊断信息。
     */
    private DiagnoseInformation collectDiagnoseInformation()
    {
      DiagnoseInformation result=new DiagnoseInformation(); //结果。

      return result;
    } //private DiagnoseInformation collectDiagnoseInformation()

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
}
