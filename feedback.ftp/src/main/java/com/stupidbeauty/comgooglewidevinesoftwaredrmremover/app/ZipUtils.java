package com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;



/**
 * Java utils 实现的Zip工具
 *
 * @author once
 */
public class ZipUtils 
{
	private static final int BUFF_SIZE = 1024 * 8; // 1M Byte
	private static final String TAG = "ZipUtils";
	private static OnZipProgressListener zipProgressListener;
	private static long resFileSize;
	private static long allZipSize;
	private static long step;
	private static File folderFile;
    
    /**
     * 批量压缩文件（夹）
     *
    * @param resFileList 要压缩的文件（夹）列表
    * @param zipFile 生成的压缩文件
    * @throws IOException 当压缩过程出错时抛出
     * @throws ZipInterruptedException zip过程被中断。
     */
	public static void zipFiles(Collection<File> resFileList, File zipFile) throws IOException, ZipInterruptedException 
    {
		
	ZipOutputStream zipout = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile), BUFF_SIZE));
			resFileSize = LogSizeMonitor.getFolderSize(folderFile); //获取所有的文件尺寸。
			LogHelper.d(TAG, "resFileSize="+resFileSize); //Debug.
			step = resFileSize / 100; //计算出单步的压缩尺寸。
			allZipSize = 0; //已经压缩的累积文件尺寸。
			
			for (File resFile : resFileList) //一个个地压缩到压缩包中。 
			{
				zipFile(resFile, zipout, ""); //压缩当前文件。
			} //for (File resFile : resFileList) //一个个地压缩到压缩包中。
			
			zipout.close(); //关闭zip输出流。
			
		
    } //public static void zipFiles(Collection<File> resFileList, File zipFile) throws IOException

	/**
     * 解压缩一个文件
     *
    * @param zipFile 压缩文件
    * @param folderPath 解压缩的目标目录
    * @throws IOException 当解压缩过程出错时抛出
     */
	public static void upZipFile(File zipFile, String folderPath) throws ZipException, IOException 
	{
		File desDir = new File(folderPath);
		if (!desDir.exists()) 
		{
			desDir.mkdirs();
        }

		ZipFile zf = new ZipFile(zipFile);
		for (Enumeration<?> entries = zf.entries(); entries.hasMoreElements();) 
		{
			ZipEntry entry = ((ZipEntry)entries.nextElement());
			InputStream in = zf.getInputStream(entry);
			String str = folderPath + File.separator + entry.getName();
			str = new String(str.getBytes("8859_1"), "GB2312");
			File desFile = new File(str);
			if (!desFile.exists()) 
			{
				File fileParentDir = desFile.getParentFile();
				if (!fileParentDir.exists()) 
				{
					fileParentDir.mkdirs();
                }
				desFile.createNewFile();
			}
			OutputStream out = new FileOutputStream(desFile);
			byte buffer[] = new byte[BUFF_SIZE];
			int realLength;
			while ((realLength = in.read(buffer)) > 0) 
			{
				out.write(buffer, 0, realLength);
            }

			in.close();
          
			out.close();
			
			zf.close(); //关闭ZIP文件。
		}
	}

    /**
     * 解压文件名包含传入文字的文件
     *
    * @param zipFile 压缩文件
    * @param folderPath 目标文件夹
    * @param nameContains 传入的文件匹配名
    * @throws ZipException 压缩格式有误时抛出
    * @throws IOException IO错误时抛出
     */
	public static ArrayList<File> upZipSelectedFile(File zipFile, String folderPath,String nameContains) throws ZipException, IOException 
	{
		ArrayList<File> fileList = new ArrayList<File>();

		File desDir = new File(folderPath);
		if (!desDir.exists()) 
		{
			desDir.mkdir();
        }

		ZipFile zf = new ZipFile(zipFile);
		for (Enumeration<?> entries = zf.entries(); entries.hasMoreElements();) 
		{
			ZipEntry entry = ((ZipEntry)entries.nextElement());
			if (entry.getName().contains(nameContains)) 
			{
				InputStream in = zf.getInputStream(entry);
				String str = folderPath + File.separator + entry.getName();
				str = new String(str.getBytes("8859_1"), "GB2312");
				// str.getBytes("GB2312"),"8859_1" 输出
				// str.getBytes("8859_1"),"GB2312" 输入
				File desFile = new File(str);
				if (!desFile.exists()) 
				{
					File fileParentDir = desFile.getParentFile();
					if (!fileParentDir.exists()) 
					{
						fileParentDir.mkdirs();
                    }

					desFile.createNewFile();
				}
             
				OutputStream out = new FileOutputStream(desFile);
				byte buffer[] = new byte[BUFF_SIZE];
				int realLength;
				while ((realLength = in.read(buffer)) > 0) 
				{
					out.write(buffer, 0, realLength);
                }
                
				in.close();
				out.close();
				fileList.add(desFile);
			}
		}
		
		zf.close(); //关闭ZIP文件。
		
		return fileList;
	}

    /**
     * 获得压缩文件内文件列表
     *
    * @param zipFile 压缩文件
    * @return 压缩文件内文件名称
    * @throws ZipException 压缩文件格式有误时抛出
    * @throws IOException 当解压缩过程出错时抛出
     */
	public static ArrayList<String> getEntriesNames(File zipFile) throws ZipException, IOException 
	{
		ArrayList<String> entryNames = new ArrayList<String>();
		Enumeration<?> entries = getEntriesEnumeration(zipFile);
		while (entries.hasMoreElements()) 
		{
			ZipEntry entry = ((ZipEntry)entries.nextElement());
			entryNames.add(new String(getEntryName(entry).getBytes("GB2312"), "8859_1"));
        }

		return entryNames;
	}

    /**
     * 获得压缩文件内压缩文件对象以取得其属性
     *
    * @param zipFile 压缩文件
    * @return 返回一个压缩文件列表
    * @throws ZipException 压缩文件格式有误时抛出
    * @throws IOException IO操作有误时抛出
     */
	public static Enumeration<?> getEntriesEnumeration(File zipFile) throws ZipException,IOException 
	{
		Enumeration<? extends ZipEntry> result; //结果。
		ZipFile zf = new ZipFile(zipFile);
		
		result=zf.entries(); //获取结果。
		
		zf.close(); //关闭ZIP文件。
		
		return result;

	}

    /**
     * 取得压缩文件对象的注释
     *
    * @param entry 压缩文件对象
    * @return 压缩文件对象的注释
    * @throws UnsupportedEncodingException
     */
	public static String getEntryComment(ZipEntry entry) throws UnsupportedEncodingException 
	{
		return new String(entry.getComment().getBytes("GB2312"), "8859_1");
    }

    /**
     * 取得压缩文件对象的名称
     *
    * @param entry 压缩文件对象
    * @return 压缩文件对象的名称
    * @throws UnsupportedEncodingException
     */
	public static String getEntryName(ZipEntry entry) throws UnsupportedEncodingException 
	{
		return new String(entry.getName().getBytes("GB2312"), "8859_1");
    }

    /**
     * 压缩文件
     *
    * @param resFile 需要压缩的文件（夹）
    * @param zipout 压缩的目的文件
    * @param rootpath 压缩的文件路径
    * @throws FileNotFoundException 找不到文件时抛出
    * @throws IOException 当压缩过程出错时抛出
     * @throws ZipInterruptedException 
     */
	private static void zipFile(File resFile, ZipOutputStream zipout, String rootpath) throws FileNotFoundException, IOException, ZipInterruptedException 
    {
		if (Thread.interrupted()) //本线程被中断。
		{
			ZipInterruptedException zipInterruptedException=new ZipInterruptedException("Zip interrupted"); //创建异常。
			throw zipInterruptedException; //抛出异常，zip过程被中断。为什么这里要抛出异常，因为zipFiles函数被递归调用，这里需要在检查到中断时立即将此事件报告给最上层的调用者。
		} //if (Thread.interrupted()) //本线程被中断。
		else //未被中断。
		{
		
		rootpath = rootpath + (rootpath.trim().length() == 0 ? "" : File.separator)+ resFile.getName(); //构造完整的文件路径。
		
		rootpath = new String(rootpath.getBytes("8859_1"), "GB2312"); //转换字符编码。
		
		if (resFile.isDirectory()) //要压缩的是一个目录。 
        {
			File[] fileList = resFile.listFiles(); //列出该目录下的文件。
			
			if(fileList!=null && fileList.length > 0) //目录中有文件存在。
            {
				for (File file : fileList) //一个个文件地压缩进去。
	            {
					zipFile(file, zipout, rootpath); //压缩其中的一个文件。
					
	          } //for (File file : fileList) //一个个文件地压缩进去。
          } //if(fileList!=null && fileList.length > 0) //目录中有文件存在。
      } //if (resFile.isDirectory()) //要压缩的是一个目录。 
		else //要压缩的是一个文件。 
        {
			byte buffer[] = new byte[BUFF_SIZE]; //创建缓冲区。
			
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(resFile), BUFF_SIZE); //创建缓冲输入流。
			
			zipout.putNextEntry(new ZipEntry(rootpath)); //加入条目。
			
			int realLength; //此次实际压缩进去的文件内容尺寸。
			
			while ((realLength = in.read(buffer)) != -1) //文件仍未添加完毕。 
            {
				zipout.write(buffer, 0, realLength); //将内容写入到压缩包数据流中。
				allZipSize+=realLength; //记录已压缩的数据量。
				zipProgressListener.onZipProgressValue(allZipSize/step); //报告进度。
            } //while ((realLength = in.read(buffer)) != -1) //文件仍未添加完毕。 

			in.close(); //关闭文件输入流。
			zipout.flush(); //强制输出数据。
			zipout.closeEntry(); //关闭当前条目。
      } //else //要压缩的是一个文件。
		
		} //else //未被中断。

   } //private static void zipFile(File resFile, ZipOutputStream zipout, String rootpath) throws FileNotFoundException, IOException 
    
    /***
     * 设置压缩量监听器
    * @param listener 要设置的监听器对象。
     */
	public static void setZipProgressListener(OnZipProgressListener listener)
	{
		zipProgressListener = listener; //记录。
    } //public static void setZipProgressListener(OnZipProgressListener listener)
    
	public static interface OnZipProgressListener 
	{
		public void onZipProgressValue(long progress);

    }

	public static void setZipFolder(File log_base_dir) 
	{
		folderFile = log_base_dir;
	}
}

