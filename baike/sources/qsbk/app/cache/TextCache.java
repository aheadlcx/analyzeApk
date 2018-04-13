package qsbk.app.cache;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.LruCache;
import android.util.Log;
import java.io.File;
import org.json.JSONObject;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.image.RetainFragment;

public class TextCache {
    private DiskLruCache a;
    private LruCache<String, JSONObject> b;

    public static class TextCacheParams {
        public boolean clearDiskCacheOnStart = false;
        public boolean diskCacheEnabled = true;
        public int diskCacheSize = 204800;
        public int memCacheSize = 102400;
        public boolean memoryCacheEnabled = true;
        public String uniqueName;

        public TextCacheParams(String str) {
            this.uniqueName = str;
        }
    }

    public TextCache(Context context, TextCacheParams textCacheParams) {
        a(context, textCacheParams);
    }

    public TextCache(Context context, String str) {
        a(context, new TextCacheParams(str));
    }

    public static TextCache findOrCreateCache(FragmentActivity fragmentActivity, String str) {
        return findOrCreateCache(fragmentActivity, new TextCacheParams(str));
    }

    public static TextCache findOrCreateCache(FragmentActivity fragmentActivity, TextCacheParams textCacheParams) {
        RetainFragment findOrCreateRetainFragment = RetainFragment.findOrCreateRetainFragment(fragmentActivity.getSupportFragmentManager());
        TextCache textCache = (TextCache) findOrCreateRetainFragment.getObject();
        if (textCache != null) {
            return textCache;
        }
        textCache = new TextCache((Context) fragmentActivity, textCacheParams);
        findOrCreateRetainFragment.setObject(textCache);
        return textCache;
    }

    private void a(Context context, TextCacheParams textCacheParams) {
        File diskCacheDir = DiskLruCache.getDiskCacheDir(context, textCacheParams.uniqueName);
        if (textCacheParams.diskCacheEnabled) {
            this.a = DiskLruCache.openCache(context, diskCacheDir, (long) textCacheParams.diskCacheSize, 0);
            if (textCacheParams.clearDiskCacheOnStart) {
                this.a.clearCache();
            }
        }
        if (textCacheParams.memoryCacheEnabled) {
            this.b = new h(this, textCacheParams.memCacheSize);
        }
    }

    public void addJSONObjectToCache(String str, JSONObject jSONObject) {
        if (str != null && jSONObject != null && this.b != null && this.b.get(str) == null) {
            this.b.put(str, jSONObject);
        }
    }

    public JSONObject getJSONObjectFromMemCache(String str) {
        if (this.b != null) {
            JSONObject jSONObject = (JSONObject) this.b.get(str);
            if (jSONObject != null) {
                if (!DebugUtil.DEBUG) {
                    return jSONObject;
                }
                Log.d("TextCache", "Memory cache hit");
                return jSONObject;
            }
        }
        return null;
    }

    public void writeTextToFile(String str, String str2) {
    }

    public void clearCaches() {
        this.a.clearCache();
        this.b.evictAll();
    }
}
