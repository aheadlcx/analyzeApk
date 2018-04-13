package qsbk.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import qsbk.app.QsbkApp;
import qsbk.app.adapter.ManageMyContentsAdapter;
import qsbk.app.im.GroupConversationActivity;
import qsbk.app.model.Article;

public class SharePreferenceUtils {
    static SharedPreferences a;
    static Editor b;

    public static SharedPreferences getDefaultSharedPreferences(Context context) {
        if (a == null) {
            a = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return a;
    }

    private static Editor a() {
        if (b == null) {
            b = getDefaultSharedPreferences(QsbkApp.mContext).edit();
        }
        return b;
    }

    public static String getSharePreferencesValue(String str) {
        String str2 = "";
        SharedPreferences defaultSharedPreferences = getDefaultSharedPreferences(QsbkApp.mContext);
        if (defaultSharedPreferences != null) {
            return defaultSharedPreferences.getString(str, "");
        }
        return str2;
    }

    public static int getSharePreferencesIntValue(String str) {
        SharedPreferences defaultSharedPreferences = getDefaultSharedPreferences(QsbkApp.mContext);
        if (defaultSharedPreferences != null) {
            return defaultSharedPreferences.getInt(str, 0);
        }
        return 0;
    }

    public static long getSharePreferencesLongValue(String str) {
        SharedPreferences defaultSharedPreferences = getDefaultSharedPreferences(QsbkApp.mContext);
        if (defaultSharedPreferences == null || !defaultSharedPreferences.contains(str)) {
            return 0;
        }
        try {
            return defaultSharedPreferences.getLong(str, 0);
        } catch (Exception e) {
            return (long) defaultSharedPreferences.getInt(str, 0);
        }
    }

    public static boolean getSharePreferencesBoolValue(String str) {
        return getSharePreferencesBoolValue(str, false);
    }

    public static boolean getSharePreferencesBoolValue(String str, boolean z) {
        SharedPreferences defaultSharedPreferences = getDefaultSharedPreferences(QsbkApp.mContext);
        if (defaultSharedPreferences != null) {
            return defaultSharedPreferences.getBoolean(str, z);
        }
        return false;
    }

    public static void adaptCommit(Editor editor) {
        if (VERSION.SDK_INT > 8) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public static void setSharePreferencesValue(String str, String str2) {
        a().putString(str, str2);
        adaptCommit(a());
    }

    public static void setSharePreferencesValue(String str, int i) {
        a().putInt(str, i);
        adaptCommit(a());
    }

    public static void setSharePreferencesValue(String str, long j) {
        a().putLong(str, j);
        adaptCommit(a());
    }

    public static void setSharePreferencesValue(String str, boolean z) {
        a().putBoolean(str, z);
        adaptCommit(a());
    }

    public static void getCollections() {
        Object obj = "";
        SharedPreferences defaultSharedPreferences = getDefaultSharedPreferences(QsbkApp.mContext);
        if (defaultSharedPreferences != null) {
            obj = defaultSharedPreferences.getString("collection", "");
        }
        if (!TextUtils.isEmpty(obj)) {
            String[] split = obj.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
            int length = split.length;
            int i = 0;
            while (i < length) {
                if (!(TextUtils.isEmpty(split[i]) || QsbkApp.allCollection.contains(split[i]))) {
                    QsbkApp.allCollection.add(split[i]);
                }
                i++;
            }
        }
    }

    public static void setCollections(ArrayList<String> arrayList) {
        remove("collection");
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append((String) it.next()).append(Constants.ACCEPT_TIME_SEPARATOR_SP);
        }
        a().putString("collection", stringBuffer.toString());
        adaptCommit(a());
    }

    public static void setCollectionsByArticle(ArrayList<Object> arrayList) {
        remove("collection");
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof Article) {
                stringBuffer.append(((Article) next).id).append(Constants.ACCEPT_TIME_SEPARATOR_SP);
            }
        }
        a().putString("collection", stringBuffer.toString());
        adaptCommit(a());
    }

    public static void trimEmpty() {
        Map all = getDefaultSharedPreferences(QsbkApp.mContext).getAll();
        if (!all.isEmpty()) {
            for (Entry entry : all.entrySet()) {
                Object value = entry.getValue();
                if (value == null || ((value instanceof String) && ((String) value).isEmpty())) {
                    a().remove((String) entry.getKey());
                }
            }
            adaptCommit(a());
        }
    }

    public static void remove(String str) {
        adaptCommit(a().remove(str));
    }

    public static void getRevokes() {
        Object obj = "";
        SharedPreferences defaultSharedPreferences = getDefaultSharedPreferences(QsbkApp.mContext);
        if (defaultSharedPreferences != null) {
            obj = defaultSharedPreferences.getString("revokes", "");
        }
        if (!TextUtils.isEmpty(obj)) {
            String[] split = obj.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
            int length = split.length;
            int i = 0;
            while (i < length) {
                if (!(TextUtils.isEmpty(split[i]) || ManageMyContentsAdapter.revokeList.contains(split[i]))) {
                    ManageMyContentsAdapter.revokeList.add(split[i]);
                }
                i++;
            }
        }
    }

    public static void setRevokes(ArrayList<String> arrayList) {
        remove("revokes");
        if (arrayList.size() > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                stringBuffer.append((String) it.next()).append(Constants.ACCEPT_TIME_SEPARATOR_SP);
            }
            a().putString("revokes", stringBuffer.toString());
        } else {
            a().putString("revokes", "");
        }
        adaptCommit(a());
    }

    public static void setClickGroupRemind(ArrayList<String> arrayList) {
        remove("groupSets");
        if (arrayList.size() > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                stringBuffer.append((String) it.next()).append(Constants.ACCEPT_TIME_SEPARATOR_SP);
            }
            a().putString("groupSets", stringBuffer.toString());
        } else {
            a().putString("groupSets", "");
        }
        adaptCommit(a());
    }

    public static void getClickGroupRemind() {
        Object obj = "";
        SharedPreferences defaultSharedPreferences = getDefaultSharedPreferences(QsbkApp.mContext);
        if (defaultSharedPreferences != null) {
            obj = defaultSharedPreferences.getString("groupSets", "");
        }
        if (!TextUtils.isEmpty(obj)) {
            String[] split = obj.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
            int length = split.length;
            int i = 0;
            while (i < length) {
                if (!(TextUtils.isEmpty(split[i]) || GroupConversationActivity.groupSets.contains(split[i]))) {
                    GroupConversationActivity.groupSets.add(split[i]);
                }
                i++;
            }
        }
    }
}
