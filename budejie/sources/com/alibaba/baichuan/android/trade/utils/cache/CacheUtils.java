package com.alibaba.baichuan.android.trade.utils.cache;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.adapter.security.AlibcSecurityGuard;
import java.util.ArrayList;
import java.util.List;

public class CacheUtils {
    public static final String SHARE_PREFERENCE_EXPIRE = "SHARE_PREFERENCE_EXPIRE";
    public static final String SHARE_PREFERENCE_NAME = "ali_bc_auth_cache";

    public static class CacheInfo {
        public String key;
        public String obj;

        public CacheInfo(String str, String str2) {
            this.key = str;
            this.obj = str2;
        }
    }

    public static void asyncPutCache(String str, String str2) {
        if (!TextUtils.isEmpty(str) && str2 != null) {
            List arrayList = new ArrayList();
            arrayList.add(new CacheInfo(str, str2));
            asyncPutCacheList(arrayList);
        }
    }

    public static void asyncPutCacheList(List list) {
        if (list != null) {
            int i = 0;
            while (i < list.size()) {
                if (!(list.get(i) == null || TextUtils.isEmpty(((CacheInfo) list.get(i)).key) || ((CacheInfo) list.get(i)).obj == null)) {
                    putCache(((CacheInfo) list.get(i)).key, ((CacheInfo) list.get(i)).obj);
                }
                i++;
            }
        }
    }

    public static void asyncPutEncryptedCache(String str, String str2) {
        asyncPutCache(str, AlibcSecurityGuard.getInstance().dynamicEncrypt(str2));
    }

    public static synchronized void cleanCache(String str) {
        synchronized (CacheUtils.class) {
            if (!TextUtils.isEmpty(str)) {
                Editor edit = AlibcContext.context.getSharedPreferences(SHARE_PREFERENCE_NAME, 0).edit();
                edit.remove(str);
                edit.remove(str + SHARE_PREFERENCE_EXPIRE);
                edit.apply();
            }
        }
    }

    public static synchronized String getCache(String str) {
        String str2;
        synchronized (CacheUtils.class) {
            if (TextUtils.isEmpty(str)) {
                str2 = "";
            } else {
                SharedPreferences sharedPreferences = AlibcContext.context.getSharedPreferences(SHARE_PREFERENCE_NAME, 0);
                str2 = sharedPreferences.getString(str, "");
                Long.valueOf(sharedPreferences.getLong(str + SHARE_PREFERENCE_EXPIRE, 0));
            }
        }
        return str2;
    }

    public static String getDecryptedCache(String str) {
        Object cache = getCache(str);
        return !TextUtils.isEmpty(cache) ? AlibcSecurityGuard.getInstance().dynamicDecrypt(cache) : null;
    }

    public static synchronized void putCache(String str, String str2) {
        synchronized (CacheUtils.class) {
            if (!(TextUtils.isEmpty(str) || str2 == null)) {
                try {
                    Editor edit = AlibcContext.context.getSharedPreferences(SHARE_PREFERENCE_NAME, 0).edit();
                    edit.putString(str, str2);
                    edit.apply();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
