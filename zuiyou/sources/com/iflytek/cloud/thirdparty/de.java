package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.net.Uri;
import android.provider.Contacts.People;
import com.umeng.analytics.b.g;

public class de extends dg {
    private static final String[] d = new String[]{"_id", "name"};
    private static final String[] e = new String[]{"name", "number", "_id"};
    private static final String[] f = new String[]{"person"};
    private static final String[] g = new String[]{g.g};
    private static final String[] h = new String[]{"number", "type", "name"};
    private static final String[] i = new String[]{"_id", "name", "number", "type"};
    private static final String[] j = new String[]{"number"};

    public de(Context context) {
        super(context);
        a(context);
    }

    public Uri a() {
        return People.CONTENT_URI;
    }

    protected String[] b() {
        return d;
    }

    protected String c() {
        return "name";
    }
}
