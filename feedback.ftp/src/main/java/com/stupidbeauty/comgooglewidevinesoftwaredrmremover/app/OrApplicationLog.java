package com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.Constants;
import butterknife.BindView;
import com.stupidbeauty.feedback.R2;
import java.io.File;
import java.io.FileOutputStream;


/**
 * The main activity.
 */
public class OrApplicationLog
{
    @BindView(R2.id.sendFeedbackuirepairprogressBar) ProgressBar sendFeedbackuirepairprogressBar; //!<发送反馈信息过程的进度条。
    @BindView(R2.id.sendFeedbackprogressValuetextView1) Button sendFeedbackprogressValuetextView1; //!<发送反馈信息的按钮。




    @BindView(R2.id.rootAccessGroupBox) RelativeLayout rootAccessGroupBox; //!<检测ROOT权限的分组框。
    private static String OptimizeRepairGooglePlayUrl="https://play.google.com/store/apps/details?id=com.stupidbeauty.comgooglewidevinesoftwaredrmremover"; //!<优化修复应用程序的google play地址。
    private  static String Email="caihuosheng@gmail.com"; //!<邮件地址。
    private static String NewLine="\n"; //!<换行。
    @BindView(R2.id.uifacingProblemlabel) TextView uifacingProblemlabel; //!<妳正面临着这个问题。

    private static final String TAG="OptimizeRepairSimpleAct"; //!<输出调试信息时使用的标记。


    @BindView(R2.id.uirepairProblemgroupBox_3) View uirepairProblemgroupBox_3; //!<修复分组框。


    @BindView(R2.id.virtualSimprogressBar1) TextView virtualSimprogressBar1; //!<ROOT权限状态文字标签。
    @BindView(R2.id.startVirtualSimButton1) TextView startVirtualSimButton1; //!<ROOT权限状态文字标签。

    @BindView(R2.id.tiptextView1) RelativeLayout tiptextView1; //!<探测分组框。


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
			
			if (Constants.NativeMessage.HIDE_CHECK_ROOT_ACCESS_GROUP_BOX.equals(action)) //隐藏检测ROOT权限的分组框。
            {
                hideCheckRootAccessGroupBox(); //隐藏检测ROOT权限的分组框。

            } //if (Constants.NativeMessage.NOTIFY_CHARGGING_STATE.equals(action)) //电池充电状态变化。
		} //public void onReceive(Context context, Intent intent)
	}; //private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver()


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
     * 隐藏检测ROOT权限的分组框。
     */
    public void hideCheckRootAccessGroupBox()
    {
        rootAccessGroupBox.setVisibility(View.GONE); //隐藏。

        return;
    } //public void shareText(statusText)
}
