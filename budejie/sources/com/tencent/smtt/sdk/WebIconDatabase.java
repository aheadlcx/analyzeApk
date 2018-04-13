package com.tencent.smtt.sdk;

import android.content.ContentResolver;
import android.graphics.Bitmap;

@Deprecated
public class WebIconDatabase {
    private static WebIconDatabase a;

    @Deprecated
    public interface a {
        void a(String str, Bitmap bitmap);
    }

    private WebIconDatabase() {
    }

    private static synchronized WebIconDatabase a() {
        WebIconDatabase webIconDatabase;
        synchronized (WebIconDatabase.class) {
            if (a == null) {
                a = new WebIconDatabase();
            }
            webIconDatabase = a;
        }
        return webIconDatabase;
    }

    public static WebIconDatabase getInstance() {
        return a();
    }

    public void bulkRequestIconForPageUrl(ContentResolver contentResolver, String str, a aVar) {
    }

    public void close() {
        bi b = bi.b();
        if (b == null || !b.c()) {
            android.webkit.WebIconDatabase.getInstance().close();
        } else {
            b.d().m();
        }
    }

    public void open(String str) {
        bi b = bi.b();
        if (b == null || !b.c()) {
            android.webkit.WebIconDatabase.getInstance().open(str);
        } else {
            b.d().b(str);
        }
    }

    public void releaseIconForPageUrl(String str) {
        bi b = bi.b();
        if (b == null || !b.c()) {
            android.webkit.WebIconDatabase.getInstance().releaseIconForPageUrl(str);
        } else {
            b.d().d(str);
        }
    }

    public void removeAllIcons() {
        bi b = bi.b();
        if (b == null || !b.c()) {
            android.webkit.WebIconDatabase.getInstance().removeAllIcons();
        } else {
            b.d().l();
        }
    }

    public void requestIconForPageUrl(String str, a aVar) {
        bi b = bi.b();
        if (b == null || !b.c()) {
            android.webkit.WebIconDatabase.getInstance().requestIconForPageUrl(str, new bb(this, aVar));
        } else {
            b.d().a(str, new ba(this, aVar));
        }
    }

    public void retainIconForPageUrl(String str) {
        bi b = bi.b();
        if (b == null || !b.c()) {
            android.webkit.WebIconDatabase.getInstance().retainIconForPageUrl(str);
        } else {
            b.d().c(str);
        }
    }
}
