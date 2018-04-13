package com.ak.android.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.ak.android.bridge.IBridge;
import com.ak.android.bridge.c;
import java.io.FileNotFoundException;

public class AKProvider extends ContentProvider {
    protected static IBridge bridge;
    private ContentProvider providerBridge;

    public boolean onCreate() {
        if (bridge == null) {
            IBridge a = c.a(getContext());
            bridge = a;
            if (a != null) {
                bridge.initSdk(getContext());
                this.providerBridge = bridge.getProvider();
            }
            if (this.providerBridge != null) {
                return this.providerBridge.onCreate();
            }
        }
        return false;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        if (this.providerBridge != null) {
            return this.providerBridge.query(uri, strArr, str, strArr2, str2);
        }
        return null;
    }

    public String getType(Uri uri) {
        if (this.providerBridge != null) {
            return this.providerBridge.getType(uri);
        }
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        if (this.providerBridge != null) {
            return this.providerBridge.insert(uri, contentValues);
        }
        return null;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        if (this.providerBridge != null) {
            return this.providerBridge.delete(uri, str, strArr);
        }
        return 0;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        if (this.providerBridge != null) {
            return this.providerBridge.update(uri, contentValues, str, strArr);
        }
        return 0;
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        if (this.providerBridge != null) {
            return this.providerBridge.openFile(uri, str);
        }
        return null;
    }
}
