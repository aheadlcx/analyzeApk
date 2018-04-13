package com.ixintui.push;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.ixintui.plugin.IPushProvider;
import com.ixintui.pushsdk.a.a;

public class Provider extends ContentProvider {
    private IPushProvider a;

    public boolean onCreate() {
        Context context = getContext();
        this.a = (IPushProvider) a.a(context, "com.ixintui.push.PushProviderImpl");
        if (this.a == null) {
            return false;
        }
        this.a.init(context);
        return this.a.onCreate();
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        if (this.a == null) {
            return null;
        }
        return this.a.query(uri, strArr, str, strArr2, str2);
    }

    public String getType(Uri uri) {
        if (this.a == null) {
            return null;
        }
        return getType(uri);
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        if (this.a == null) {
            return null;
        }
        return this.a.insert(uri, contentValues);
    }

    public int delete(Uri uri, String str, String[] strArr) {
        if (this.a == null) {
            return 0;
        }
        return this.a.delete(uri, str, strArr);
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        if (this.a == null) {
            return 0;
        }
        return this.a.update(uri, contentValues, str, strArr);
    }
}
