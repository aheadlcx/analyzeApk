package com.androidex.util;

import android.os.Environment;
import android.text.TextUtils;
import com.budejie.www.activity.video.a;
import java.io.File;

public class EnvironmentUtil {
    private static String appHomeDir = "qyer/";
    private static String dataBaseDir = "databases/";
    private static String fileDir = "files/";
    private static String picDir = "pics/";

    public static void setAppHomeDir(String str) {
        if (!TextUtils.isEmpty(str)) {
            appHomeDir = str;
        }
    }

    public static File getAppHomeDir() {
        File file = new File(getSdcardDir(), appHomeDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getAppFileDir() {
        File file = new File(getAppHomeDir(), fileDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getAppFile(String str) {
        if (str == null) {
            str = "";
        }
        return new File(getAppFileDir(), str);
    }

    public static File getAppPicDir() {
        File file = new File(getAppHomeDir(), picDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getAppPicFile(String str) {
        if (str == null) {
            str = "";
        }
        return new File(getAppPicDir(), str);
    }

    public static File getAppDatabaseDir() {
        File file = new File(getAppHomeDir(), dataBaseDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getAppDatabaseFile(String str) {
        if (str == null) {
            str = "";
        }
        return new File(getAppDatabaseDir(), str);
    }

    public static File getAppHomeSubDir(String str) {
        if (str == null) {
            str = "";
        }
        File file = new File(getAppHomeDir(), str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getAppHomeSubFile(String str) {
        if (str == null) {
            str = "";
        }
        return new File(getAppHomeDir(), str);
    }

    public static File getSdcardDir() {
        return Environment.getExternalStorageDirectory();
    }

    public static File getSdcardDir(String str) {
        if (str == null) {
            str = "";
        }
        File file = new File(getSdcardDir(), str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getSdcardFile(String str) {
        if (str == null) {
            str = "";
        }
        return new File(getSdcardDir(), str);
    }

    public static boolean sdcardIsMounted() {
        return a.a();
    }

    public static boolean sdcardIsEnable() {
        return a.a() && !Environment.getExternalStorageState().equals("shared");
    }
}
