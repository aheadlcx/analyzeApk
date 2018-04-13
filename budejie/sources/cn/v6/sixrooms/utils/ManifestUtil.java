package cn.v6.sixrooms.utils;

import android.text.TextUtils;
import cn.v6.sdk.sixrooms.coop.V6Coop;

public class ManifestUtil {
    public static final String CHANNEL = "UMENG_CHANNEL";
    public static final String CUSTOM_NAME = "APP_CUSTOM_NAME";
    public static final String SHILIU_PATH = "sixrooms";
    public static final String XIUCHANG_PATH = "xiuchangmajia";
    public static final String YSDK_PATH = "sixroomsysdk";
    private static String a;
    private static String b;
    public static String path;

    private ManifestUtil() {
    }

    public static String getCustomName() {
        String stringBuilder;
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        try {
            stringBuilder = new StringBuilder(AppInfoUtils.APP_CUSTOM_NAME_BASIC).append(V6Coop.mCoop).toString();
            a = stringBuilder;
            stringBuilder = stringBuilder == null ? "" : a;
            a = stringBuilder;
            return stringBuilder;
        } catch (Exception e) {
            e.printStackTrace();
            stringBuilder = "";
            a = stringBuilder;
            return stringBuilder;
        }
    }

    public static String getChannel() {
        String str;
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        try {
            str = V6Coop.mReleaseNum;
            b = str;
            str = str == null ? "" : b;
            b = str;
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            str = "";
            b = str;
            return str;
        }
    }

    public static String getSDCradFilePathName() {
        if (!TextUtils.isEmpty(path)) {
            return path;
        }
        if (getCustomName().contains("xiuchangmajia_")) {
            path = XIUCHANG_PATH;
        } else if (getCustomName().contains("xiuchang_ysdk_")) {
            path = YSDK_PATH;
        } else {
            path = SHILIU_PATH;
        }
        return path;
    }
}
