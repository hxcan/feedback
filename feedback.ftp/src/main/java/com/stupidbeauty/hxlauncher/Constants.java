package com.stupidbeauty.hxlauncher;

import android.os.Environment;

import java.io.File;

/**
 * 一些常量的定义。
 * @author root 蔡火胜。
 *
 */
public class Constants 
{
    /**
     * 文件路径。
     * @author root 蔡火胜。
     *
     */
    public static class FilePath
    {
        public static final String UnknownDeviceMacAddr = "UnknownMacAddr"; //!<未知的网卡物理地址。
    } //public static class FilePath

    /**
     * 原始消息。
     * @author root
     *
     */
    public final class NativeMessage
    {
        public static final String APPLICATION_LAUNCHED = "com.stupidbeauty.hxlauncher.constants.nativemessage.applicationLaunched"; //!<应用程序被启动。
        public static final String APPLICATION_LAUNCHED_PACKAGE_KEY = "com.stupidbeauty.hxlauncher.constants.nativemessage.applicationLaunchedPackageKey"; //!<被启动的应用程序的包名的参数键。
    } //public final class NativeMessage

    /**
     * 目录路径。
     * @author root 蔡火胜。
     *
     */
    public static class DirPath
    {
        public static final String FARMING_BOOK_APP_SD_CARD_PATH = Environment.getExternalStorageDirectory().getPath()+ File.separator+ Environment.DIRECTORY_DCIM+File.separator+"GoddessCamera"; //女神相机的路径。
    } //public static class DirPath


} //public class Constants
