package com.stupidbeauty.comgooglewidevinesoftwaredrmremover;

import com.stupidbeauty.codeposition.CodePosition;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.BufferedReader;
import android.media.MediaScannerConnection;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.DiagnoseInformation;
import com.stupidbeauty.feedback.activity.FeedbackActivity;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import java.util.concurrent.TimeoutException;
import java.io.File;
import java.util.Collection;
import java.util.Random;
import com.upokecenter.cbor.CBORObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import java.io.ByteArrayOutputStream;
import com.stupidbeauty.feedback.FeedbackMessage;
import com.stupidbeauty.feedback.FeedbackDataStore;

/**
 * @author Hxcan
 * @since Mar 13, 2014
 */
public final class FeedbackRequestSendTask extends AsyncTask<Object, Void, Boolean>
{
  public static final String FEEDBACK_QUEUE_NAME = "com.stupidbeauty.feedback.FeedbackQueue"; //!< The feedback queue name.

  private static final String RabbitMQUserName="optimizerepair"; //!<RabbitMQ用户名。
  private static final String RabbitMQPassword="som3150"; //!<RabbitMQ密码。

  private FeedbackActivity simpleActivity; //!<主活动对象。
	private static final String TAG="FeedbackRequestSendTask"; //!< The tag used for debug code.

  public FeedbackRequestSendTask(FeedbackActivity activity)
  {
    simpleActivity=activity; //记录。
  } //public TranslateRequestSendTask(OptimizeRepairSimpleActivity activity)

  @Override
  protected Boolean doInBackground(Object... params)
  {
    // Log.d(TAG,"doInBackground,published translate request"); //Debug.
    Log.d(TAG, CodePosition.newInstance().toString()); // Debug.    
    //参数顺序：
    //            subject, diagnozeInformation,emailAddress.

    Boolean result=false; //结果，是否成功。
    Log.d(TAG, CodePosition.newInstance().toString()); // Debug.    

    //使用protobuf将各个字段序列化成字节数组，然后使用rabbitmq发送到服务器。

    String subject=(String)(params[0]); //获取主题。
    Log.d(TAG, CodePosition.newInstance().toString()); // Debug.    

    // File body=(File)(params[1]); //获取日志压缩文件。

    DiagnoseInformation attachmentBitmap=(DiagnoseInformation)(params[1]); //获取诊断信息。
    Log.d(TAG, CodePosition.newInstance().toString()); // Debug.    

    String emailAddress=(String)(params[2]); //获取用户的邮件地址。

    try //尝试构造请求对象，并且捕获可能的异常。
    {
      Log.d(TAG, CodePosition.newInstance().toString()); // Debug.    
      // byte[] array=constructFeedbackMessageProtobuf(emailAddress, subject); // Construct feedback message with protobuf.
      byte[] array=constructFeedbackMessageCbor(emailAddress, subject); // Construct feedback message with cbor.
      
      sendAmqpRequest(array); // Send amqp request.
      Log.d(TAG, CodePosition.newInstance().toString()); // Debug.    

      result=true; //成功。
    } //try //尝试构造请求对象，并且捕获可能的异常。
    catch (Exception e)
    {
      Log.d(TAG, CodePosition.newInstance().toString()); // Debug.    
      e.printStackTrace();
    }
    catch (OutOfMemoryError error) //内存不足。
    {
      Log.d(TAG, CodePosition.newInstance().toString()); // Debug.    
      error.printStackTrace(); //报告错误。
    } //catch (OutOfMemoryError error) //内存不足。

    Log.d(TAG, CodePosition.newInstance().toString()); // Debug.    
    return result;
  }
  
  /**
  * Construct feedback message with cbor.
  */
  private byte[] constructFeedbackMessageCbor(String emailAddress, String subject)
  {
    byte[] array=null; //序列化成字节数组。

    {
      // byte[] attachmentImageBytes=FileUtils.readFileToByteArray(body); //将日志压缩文件整个读入。

      // ByteString attachment=ByteString.copyFrom(attachmentImageBytes); //构造字节字符串。

      FeedbackMessage translateRequestBuilder = new FeedbackMessage();

      translateRequestBuilder.setFeedbacktext(subject); //设置反馈文字内容。

      translateRequestBuilder.setOsversion(Build.VERSION.RELEASE);

      translateRequestBuilder.setManufacturer(Build.MANUFACTURER);
      translateRequestBuilder.setModel(Build.MODEL);

    FeedbackDataStore feedbackDataStore = FeedbackDataStore.sharedInstance(); // Get the feedback data store.
    
    String developerEmail = feedbackDataStore.getDeveloperEmail(); // Remember devloepr email address.

      translateRequestBuilder.setDeveloperEmail(developerEmail); // Set the developer email.
      translateRequestBuilder.setEmailAddress(emailAddress); //设置邮件地址。
      
      String packageName = simpleActivity.getPackageName(); // 获取本个应用的包名。

      translateRequestBuilder.setPackageName(packageName); // SEt the package name.

      // array=translateRequestBuilder.build().toByteArray(); //序列化成字节数组。
      
      CBORObject cborObject= CBORObject.FromObject(translateRequestBuilder); //创建对象

      array=cborObject.EncodeToBytes();

    }
    
    return array; 
  } // private byte[] constructFeedbackMessageCbor(String emailAddress, String subject)
  
  private void sendAmqpRequest(byte[] array) // Send amqp request.
  {
    //使用RabbitMQ来发送：
    ConnectionFactory factory=new ConnectionFactory(); //创建连接工厂。
    factory.setHost("stupidbeauty.com"); //设置主机。
    factory.setUsername(RabbitMQUserName); //设置用户名。
    factory.setPassword(RabbitMQPassword); //设置密码。

    try
    {
      Connection connection=factory.newConnection(); //创建连接。
      Channel channel=connection.createChannel(); //创建频道。

      Boolean durableTrue=true; //持久的。

      Boolean exclusiveFalse=false; //非排他性的。
      Boolean autoDeleteFalse=false; //不要自动删除。

      channel.queueDeclare(FEEDBACK_QUEUE_NAME, durableTrue, exclusiveFalse, autoDeleteFalse, null); //声明队列。

      String exchange=""; //交换机。
      String routingKey= FEEDBACK_QUEUE_NAME; //路由键。锓

      byte[] byteArrayBody=array; //消息体。

      channel.basicPublish(exchange,routingKey, MessageProperties.PERSISTENT_BASIC,byteArrayBody);
    } // try
    catch(IOException e)
    {
      e.printStackTrace();
    } // catch(IOException e)
    catch(TimeoutException e)
    {
      e.printStackTrace();
    } // catch(IOException e)

    Log.d(TAG,"doInBackground,published translate request"); //Debug.

  } // private void sendAmqpRequest()

  /**
    * 报告结果。
    * @param result 结果。是否成功。
    */
  @Override
  protected void onPostExecute(Boolean result)
  {
    Log.d(TAG, CodePosition.newInstance().toString()); // Debug.    
    simpleActivity.reportHelpTranslateRequestSendResult(result); //报告结果，翻译请求的发送结果。
  } //protected void onPostExecute(Boolean result)
}



