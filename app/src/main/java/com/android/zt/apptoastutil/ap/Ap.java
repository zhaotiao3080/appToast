package com.android.zt.apptoastutil.ap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.multidex.MultiDexApplication;

import com.android.zt.openlibrary.AppToast;

public class Ap extends MultiDexApplication {

    private static Ap _instance;

    public static Ap getApplication() {
        return _instance;
    }

    public static Context getContext() {
        return _instance.getApplicationContext();
    }

    String UNKNOWN = "Unknown";

    PackageInfo info;

    int APP_VERSION_CODE;

    static String APP_VERSION_NAME;

    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;

        try {
            info = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
        } catch (Exception e) {
            info = null;
        }
        APP_VERSION_CODE = info == null ? 0 : info.versionCode;
        APP_VERSION_NAME = info == null ? UNKNOWN : info.versionName;
        info = null;

        AppToast.init(getContext());
    }


    /**
     * 检查是否有网络连接
     *
     * @return
     */
    public static boolean isNetworkConnected() {
        if (getContext() != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    //返回当前程序版本名
    public static String getAppVersionName() {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = _instance.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(_instance.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
//            LogUtil.e("PUB Exception 获取当前版本异常", e.toString());
        }
        return versionName;
    }

    /**
     * 进行版本比较
     * @param current
     * @return
     */
    public static boolean compareVersion(String current) {
        String target = getAppVersionName();

        if (current != null && target != null) {
            if (current.equalsIgnoreCase(target) == false) {
                String[] currentVersion = current.split("\\.");
                String[] targetVersion = target.split("\\.");

                int currentCount = currentVersion.length;
                int targetCount = targetVersion.length;
                int versionCount = Math.max(currentCount, targetCount);
                int currentNum;
                int targetNum;
                try {
                    for (int i = 0; i < versionCount; i++) {
                        currentNum = i < currentCount ? Integer.valueOf(currentVersion[i]) : 0;
                        targetNum = i < targetCount ? Integer.valueOf(targetVersion[i]) : 0;
                        if (currentNum < targetNum) {
                            return false;
                        } else if (currentNum > targetNum) {
                            return true;
                        }
                    }
                } catch (NumberFormatException e) {
                }
                return false;
            }
        }
        return false;
    }

    /**
     * 打开网络连接设置
     *
     * @param activity
     */
    public static void openNetworkConfig(Activity activity) {

        if (Build.VERSION.SDK_INT > 13) {
            //3.2以上打开设置界面，也可以直接用ACTION_WIRELESS_SETTINGS打开到wifi界面
            activity.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
        } else {
            activity.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
        }
    }
}
