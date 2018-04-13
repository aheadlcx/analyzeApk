package com.ixintui.plugin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public interface IPushProvider {
    int delete(Uri uri, String str, String[] strArr);

    String getType(Uri uri);

    void init(Context context);

    Uri insert(Uri uri, ContentValues contentValues);

    boolean onCreate();

    Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2);

    int update(Uri uri, ContentValues contentValues, String str, String[] strArr);
}
