package cn.v6.sixrooms.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import java.util.Random;

public class SaveUserInfoUtils {
    public static final String MBLOG_DRAFT_MSG = "mblogDraftMsg";
    public static final String MBLOG_DRAFT_PIC = "mblogDraftPic";
    public static final String MBLOG_DRAFT_UID = "mblogDraftUid";
    public static final String VISITOR_ID = "visitorId";
    private static String a = "";

    public static void saveEncpass(Context context, String str) {
        Editor edit = context.getSharedPreferences("userInfo", 32768).edit();
        edit.putString("encpass", str);
        edit.commit();
    }

    public static String getEncpass(Context context) {
        if (context == null) {
            return "";
        }
        return context.getSharedPreferences("userInfo", 32768).getString("encpass", "");
    }

    public static void clearEncpass(Context context) {
        Editor edit = context.getSharedPreferences("userInfo", 32768).edit();
        edit.putString("encpass", "");
        edit.commit();
    }

    public static void saveVisitorId(Context context, String str) {
        Editor edit = context.getSharedPreferences("visitorId", 32768).edit();
        edit.putString("visitorId", str);
        edit.putString("randomVisitorId", "");
        edit.commit();
        a = str;
    }

    public static String getVisitorId(Context context) {
        if (TextUtils.isEmpty(a)) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("visitorId", 32768);
            CharSequence string = sharedPreferences.getString("visitorId", "");
            a = string;
            if (TextUtils.isEmpty(string)) {
                string = sharedPreferences.getString("randomVisitorId", "");
                a = string;
                if (TextUtils.isEmpty(string)) {
                    Random random = new Random();
                    int nextInt = random.nextInt(999999999) + 1;
                    long nextInt2 = (long) (random.nextInt(9) + 1);
                    for (int i = 0; i < 9; i++) {
                        nextInt2 *= 10;
                    }
                    a = Long.toString(nextInt2 + ((long) nextInt));
                    Editor edit = sharedPreferences.edit();
                    edit.putString("randomVisitorId", a);
                    edit.commit();
                }
            }
        }
        return a;
    }

    public static void saveUserCoinV(Context context, String str, String str2) {
        Editor edit = context.getSharedPreferences(SharedPreferencesUtils.USER_COIN_V, 0).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public static String getUserCoinV(Context context, String str) {
        return context.getSharedPreferences(SharedPreferencesUtils.USER_COIN_V, 0).getString(str, "");
    }

    public static void saveMBlogDraftData(Context context, String str, String str2) {
        Editor edit = context.getSharedPreferences("mblogDraft", 0).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public static String getMBlogDraftData(Context context, String str) {
        return context.getSharedPreferences("mblogDraft", 0).getString(str, "");
    }

    public static void clearMBlogDraftData(Context context) {
        Editor edit = context.getSharedPreferences("mblogDraft", 0).edit();
        edit.clear();
        edit.commit();
    }

    public static void saveCoopUid(Context context, String str) {
        Editor edit = context.getSharedPreferences("userInfo", 32768).edit();
        edit.putString("coopuid", str);
        edit.commit();
    }

    public static String getCoopUid(Context context) {
        if (context == null) {
            return "";
        }
        return context.getSharedPreferences("userInfo", 32768).getString("coopuid", "");
    }
}
