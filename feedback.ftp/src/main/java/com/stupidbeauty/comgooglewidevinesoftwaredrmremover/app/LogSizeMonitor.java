package com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import android.text.TextUtils;

/**
 * Log 大小的监测管理，默认情况下允许保存50M的Gmate log 目录结构：
 * Skyroam/Log/yyyy-MM-dd/Gmate/SN_number/GmateLog_yyyy-MM-dd HHmmss.txt
 * 首先：监测文件夹Skyroam/Log的大小，当大小大于50M A,该文件夹下的文件大于1，找到最早日期的文件删除，递归从新检查
 * B,该文件夹下的文件为1,递归监测 SN_number 目录 C,当大小小于50M，不需做任何处理。
 * 
 * @author ljp
 * 
 */
public class LogSizeMonitor 
{
	private static final long FILE_LOG_MAX_LENGTH = 1024 * 1024 * 50; //!<50M。日志文件大小的上限，超过这个尺寸就需要删除旧的日志文件。
	public static final String TAG = "LogSizeMonitor"; //!<用于输出调试信息的标签。

	/**
	 * @param file_dir 监测的文件目录
	 */
	public static void checkFileSize(File file_dir) 
	{
		long currentFolderSize=getFolderSize(file_dir) ; //当前目录的总尺寸。

		if (file_dir != null && currentFolderSize > FILE_LOG_MAX_LENGTH) //目录对象存在，并且尺寸超过阈值。 
		{
			if (file_dir.exists()) //目录存在。 
			{
				ArrayList<File> files=recursiveListOnlyFiles(file_dir); //列出此目录树下的所有文件，递归列出，只包含文件。
				
				if (files!=null) //成功地列出了子文件。
				{
					Comparator<File> fileLastModifiedTimeComparator=new FileLastModifiedTimeComparator(); //创建排序器，按照文件的最后修改时间排序。
					Collections.sort(files, fileLastModifiedTimeComparator); //排序。升序排序。排序之后，较旧的文件在前面。
					
					long needDeleteFileSizeSum=currentFolderSize-FILE_LOG_MAX_LENGTH; //计算出需要删除掉的文件大小总和。
					long deletedFileSizeSum =0; //目前已经删除的文件及目录的尺寸的累加和。
					
					for(int fileCounter=0;fileCounter<file_dir.length();fileCounter++) //一个个地按照顺序删除文件。
					{
						long currentSubFileSize=0; //当前的子文件或目录的尺寸。
						
						File currentFile=files.get(fileCounter); //获取当前文件对象。
						
						if (currentFile.getName().contains(LanImeBaseDef.EXCEPTION_FILE)) //这是例外文件名。例外文件不被删除。
						{
						} //if (currentFile.getName().contains(SimoBaseDef.EXCEPTION_FILE)) //这是例外文件名。例外文件不被删除。
						else //其它文件都可被删除。
						{
							if (currentFile.isDirectory()) //是一个目录。
							{
								currentSubFileSize=getFolderSize(currentFile); //获取当前子目录的尺寸。
							} //if (currentFile.isDirectory()) //是一个目录。
							else //是一个文件。
							{
								currentSubFileSize=currentFile.length(); //获取当前文件的尺寸。
							} //else //是一个文件。
							
							LogHelper.d(TAG, "earliestFile.getAbsolutePath() = "+ currentFile.getAbsolutePath()); //Debug.
							deleteFolderFile(currentFile.getAbsolutePath(),true); //删除当前子文件或目录。
							
							deletedFileSizeSum += currentSubFileSize; //累加记录目前已经删除的子文件或目录的总尺寸。
							if (deletedFileSizeSum>needDeleteFileSizeSum) //已经删除了足够多的大小的文件及目录了。
							{
								break; //不再删除后面的文件及目录了。
							} //if (deletedFileSizeSum>needDeleteFileSizeSum) //已经删除了足够多的大小的文件及目录了。


						} //else //其它文件都可被删除。
					} //for(int fileCounter=0;fileCounter<file_dir.length();fileCounter++) //一个个地按照顺序删除文件。
				} //if (files!=null) //成功地列出了子文件。
			} //if (file_dir.exists()) //目录存在。
		} //if (file_dir != null && getFolderSize(file_dir) > FILE_LOG_MAX_LENGTH) //目录对象存在，并且尺寸超过阈值。
		else if (file_dir != null && getFolderSize(file_dir) < FILE_LOG_MAX_LENGTH) //目录中的文件尺寸未超过阈值。 
		{
			recursiveListOnlyFiles(file_dir); //列出此目录树下的所有文件，递归列出，只包含文件。//Debug.
			
			if (file_dir.exists()) //目录存在。 
			{
				File[] files = file_dir.listFiles(); //列出文件。
				
				if(files !=null && files.length > 0) //有文件。
				{
					for (File dayFile : files) //一个个地处理那些文件。 
					{
						if (getFolderSize(dayFile) == 0) //目录尺寸为0 。空目录。 
						{
							deleteFolderFile(dayFile.getAbsolutePath(), true); //删除目录。
						} //if (getFolderSize(dayFile) == 0) //目录尺寸为0 。空目录。
					} //for (File dayFile : files) //一个个地处理那些文件。
				} //if(files !=null && files.length > 0) //有文件。
			} //if (file_dir.exists()) //目录存在。
		} //else if (file_dir != null && getFolderSize(file_dir) < FILE_LOG_MAX_LENGTH) //目录中的文件尺寸未超过阈值。
	} //public static void checkFileSize(File file_dir) 

	/**
	 * 递归地列出此目录树下的所有文件，仅包含文件。
	 * @param file_dir 要列出其下文件的目录。
	 * @return 列出的文件对象列表。
	 */
	private static ArrayList<File> recursiveListOnlyFiles(File file_dir) 
	{
		ArrayList<File> result=new ArrayList<File>(FileUtils.listFiles(file_dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE)); //结果。
		
		return result;
	} //private static File[] recursiveListOnlyFiles(File file_dir)

	/**
	 * 获取文件夹大小
	 * 
	 * @param file File实例
	 * @return long 单位为byte
	 */
	public static long getFolderSize(File file) 
	{
		long size = 0; //结果。最后的尺寸。
		
		File[] fileList = file.listFiles(); //列出文件列表。
		
		if (fileList != null) //列出成功。 
		{
			for (int i = 0; i < fileList.length; i++) //一个个地累加。 
			{
				if (fileList[i].isDirectory()) //是个目录。 
				{
					size = size + getFolderSize(fileList[i]); //累加目录的尺寸。
				} //if (fileList[i].isDirectory()) //是个目录。
				else //是个文件。 
				{
					size = size + fileList[i].length(); //累加文件的尺寸。
				} //else //是个文件。
			} //for (int i = 0; i < fileList.length; i++) //一个个地累加。
			
			LogHelper.d(TAG, "getFolderSize   size= " + size); //Debug.
		} //if (fileList != null) //列出成功。
		
		return size;
	} //public static long getFolderSize(File file)

	/**
	 * 获取所有压缩文件zip的大小
	 * 
	 * @param file File实例
	 * @return long 单位为byte
	 */
	public static long getFolderZipFilesSize(File file) 
	{
		long size = 0;
		if (!file.exists()) {
			return 0;
		}
		File[] fileList = file.listFiles();
		if (fileList != null) {
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isDirectory()) {
					size = size + getFolderZipFilesSize(fileList[i]);
				} else {
					if (fileList[i].getName().contains(".zip")) {
						size = size + fileList[i].length();
					}
				}
			}
		}
		return size;
	}

	/**
	 * 删除指定目录下文件及目录
	 * 
	 * @param deleteThisPath
	 * @param filepath
	 * @return
	 */
	public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
		if (!TextUtils.isEmpty(filePath)) {
			File file = new File(filePath);

			if (file.isDirectory()) {// 处理目录
				File files[] = file.listFiles();
				if (files != null) {
					for (int i = 0; i < files.length; i++) {
						deleteFolderFile(files[i].getAbsolutePath(), true);
					}
				}
			}
			if (deleteThisPath) {
				if (!file.isDirectory()) {// 如果是文件，删除
					file.delete();
				} else {// 目录
					File f[] = file.listFiles();
					if (f != null && f.length == 0) {// 目录下没有文件或者目录，删除
						file.delete();
					}
				}
			}
		}
	}

	/**
	 * @param dir_file
	 *            当前目录
	 * @return 最后一个文件夹
	 */
	public static File getLastDirFile(File dir_file) {
		if (dir_file.exists()) {
			File[] files = dir_file.listFiles();
			if (files != null) {
				for (File file : files) {
					if (file.isDirectory()) {
						return getLastDirFile(file);
					} else {
						return file.getParentFile();
					}
				}
			}
		}
		return null;
	}
}