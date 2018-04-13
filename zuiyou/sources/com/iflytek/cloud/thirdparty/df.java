package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.ContactsContract.Contacts;
import com.umeng.analytics.b.g;

public class df extends dg {
    private static final String[] d = new String[]{g.g, "_id"};
    private static final String[] e = new String[]{g.g, "data1", "contact_id"};
    private static final String[] f = new String[]{"_id", "has_phone_number"};
    private static final String[] g = new String[]{"contact_id"};
    private static final String[] h = new String[]{g.g};
    private static final String[] i = new String[]{"data1", "data2", g.g};
    private static final String[] j = new String[]{"has_phone_number"};

    public df(Context context) {
        super(context);
        a(context);
    }

    public Uri a() {
        return Contacts.CONTENT_URI;
    }

    protected String[] b() {
        return d;
    }

    protected String c() {
        return Integer.parseInt(VERSION.SDK) >= 8 ? "sort_key" : g.g;
    }
}
