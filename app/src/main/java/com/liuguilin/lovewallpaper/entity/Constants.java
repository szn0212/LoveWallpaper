package com.liuguilin.lovewallpaper.entity;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.entity
 *  文件名:   Constants
 *  创建者:   LGL
 *  创建时间:  2017/1/9 17:16
 *  描述：    常量类
 */

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.view.Gravity;

import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.activity.WebViewActivity;
import com.liuguilin.lovewallpaper.utils.L;
import com.liuguilin.lovewallpaper.view.CustomDialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Constants {

    //返回码
    public static final int REQUEST_CODE = 101;

    //第一次启动
    public static final int HANDLER_FIRST_START = 1000;
    //无限轮播
    public static final int HANDLER_AUTO_SHUFFLING = 1001;
    //下拉刷新
    public static final int HANDLER_REFRESH = 1002;
    //停止动画
    public static final int HANDLER_STOP_ANIMATION = 1003;

    public static final String WALLPAPER_BASE_URL = "http://open.lovebizhi.com/";
    public static final String WEATHER_BASE_URL = "https://api.thinkpage.cn/";

    //视频地址
    public static final String IMOOC_VIDEO_URL = "http://szv1.mukewang.com/5876eb5be520e572618b458b/H.mp4";
    //图片地址
    public static final String IMOOC_IMAGE_URL = "http://szimg.mukewang.com/5876eedd0001d20909000500.jpg";

    //Blog
    public static final String BLOG = "http://blog.csdn.net/qq_26787115";
    //Github
    public static final String GITHUB = "https://github.com/LiuGuiLinAndroid";
    //项目 Github
    public static final String LOVE_WALLPAPER_GITHUB = "https://github.com/LiuGuiLinAndroid/LoveWallpaper";

    //心知天气key
    public static final String THINKPAPE_KEY = "cjfbaiq6lln0oqk1";

    //生活指数图片
    public static final int WEATHER_LIFE_ICON[] = {R.drawable.icon_car_washing, R.drawable.icon_dressing
            , R.drawable.icon_flu, R.drawable.icon_sport, R.drawable.icon_travel, R.drawable.icon_uv};

    //设置图标
    public static final int SETTING_ICON[] = {R.drawable.icon_setting_msg, R.drawable.icon_clear
            , R.drawable.icon_version, R.drawable.icon_git, R.drawable.icon_about};

    //天气图标
    public static final int WEATHER_ICON[] =
            {R.drawable.w0, R.drawable.w1, R.drawable.w2, R.drawable.w3, R.drawable.w4
                    , R.drawable.w5, R.drawable.w6, R.drawable.w7, R.drawable.w8, R.drawable.w9
                    , R.drawable.w10, R.drawable.w11, R.drawable.w12, R.drawable.w13, R.drawable.w14
                    , R.drawable.w15, R.drawable.w16, R.drawable.w17, R.drawable.w18, R.drawable.w19
                    , R.drawable.w20, R.drawable.w21, R.drawable.w22, R.drawable.w23, R.drawable.w24
                    , R.drawable.w25, R.drawable.w26, R.drawable.w27, R.drawable.w28, R.drawable.w29
                    , R.drawable.w30, R.drawable.w31, R.drawable.w32, R.drawable.w33, R.drawable.w34
                    , R.drawable.w35, R.drawable.w36, R.drawable.w37, R.drawable.w38, R.drawable.w99};
    //新闻类别
    public static final String NEWS_TYPE_EN[] = {"top", "shehui", "guonei", "guoji", "yule", "tiyu", "junshi", "keji", "caijing", "shishang"};
    public static final String NEWS_TYPE_ZH[] = {"头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚"};

    //封装dialog
    public static CustomDialog showDialog(Context mContext, int layout) {
        //初始化提示框
        CustomDialog dialog = new CustomDialog(mContext, 0, 0,
                layout, R.style.Theme_dialog, Gravity.CENTER, R.style.pop_anim_style);
        return dialog;
    }

    //获取版本号
    public static String getAppVersion(Context mContext) {
        String version = "";
        PackageManager pm = mContext.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(mContext.getPackageName(), 0);
            version = info.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            version = "获取失败";
        }
        return version;
    }

    //跳转网页
    public static void startWebView(Context mContext, String title, String url) {
        Intent intent2 = new Intent(mContext, WebViewActivity.class);
        intent2.putExtra("title", title);
        intent2.putExtra("url", url);
        mContext.startActivity(intent2);
    }

    //获取手机IP
    public static String getPhoneIp(Context mContext) {
        // 获取wifi服务
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        // 判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        return ip;
    }

    //地址算法
    private static String intToIp(int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
                + "." + (i >> 24 & 0xFF);
    }

    //获取MAC地址
    public static String getMacAddress(Context mContext) {
        WifiManager wifi = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    //获取内存卡可用内存
    public static String getSdAvailableMemory() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File path = Environment.getExternalStorageDirectory();
            StatFs statfs = new StatFs(path.getPath());
            // 获取block的SIZE
            long blocSize = statfs.getBlockSize();
            // 获取BLOCK数量
            long totalBlocks = statfs.getBlockCount();
            // 空闲的Block的数量
            long availaBlock = statfs.getAvailableBlocks();
            // 计算总空间大小和空闲的空间大小
            long availableSize = blocSize * availaBlock;
            long allSize = blocSize * totalBlocks;
            return "可用：" + availableSize / 1024 / 1024 / 1024 + "GB"
                    + "  总共：" + allSize / 1024 / 1024 / 1024 + "GB";
        } else {
            return "SD卡不可用";
        }
    }

    //获取当前网络状态
    public static String getNetworkState(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            return "无网络连接";
        } else {
            return "网络正常";
        }
    }

    //获得可用内存
    public static String getAvailMemory(Context mContext) {
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        // mi.availMem; 当前系统的可用内存
        return Formatter.formatFileSize(mContext, mi.availMem);
    }

    //获取总内存
    public static String getTotalMemory(Context mContext) {
        // 系统内存信息文件
        String str1 = "/proc/meminfo";
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;
        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            // 读取meminfo第一行，系统总内存大小
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            // 获得系统总内存，单位是KB，乘以1024转换为Byte
            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;
            localBufferedReader.close();

        } catch (IOException e) {
            L.e("error:" + e.toString());
        }
        // Byte转换为KB或者MB，内存大小规格化
        return Formatter.formatFileSize(mContext, initial_memory);
    }

    // 1-cpu型号 2-cpu频率
    public static String[] cpuInfo = {"", ""};

    //获取CPU属性
    public static void getCpuInfo() {
        String str1 = "/proc/cpuinfo";
        String str2 = "";
        String[] arrayOfString;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++) {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException e) {
            L.i("error:" + e.toString());
        }
    }

    //获取IMEI
    public static String getImei(Context mContext) {
        TelephonyManager mTelephonyMgr = (TelephonyManager) mContext.
                getSystemService(Context.TELEPHONY_SERVICE);
        String imei = mTelephonyMgr.getDeviceId();
        return imei;
    }
}
