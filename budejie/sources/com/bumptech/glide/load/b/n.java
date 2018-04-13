package com.bumptech.glide.load.b;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import com.bumptech.glide.load.a.c;

public class n<T> implements l<Integer, T> {
    private final l<Uri, T> a;
    private final Resources b;

    public n(Context context, l<Uri, T> lVar) {
        this(context.getResources(), (l) lVar);
    }

    public n(Resources resources, l<Uri, T> lVar) {
        this.b = resources;
        this.a = lVar;
    }

    public c<T> a(Integer num, int i, int i2) {
        Object parse;
        try {
            parse = Uri.parse("android.resource://" + this.b.getResourcePackageName(num.intValue()) + '/' + this.b.getResourceTypeName(num.intValue()) + '/' + this.b.getResourceEntryName(num.intValue()));
        } catch (Throwable e) {
            if (Log.isLoggable("ResourceLoader", 5)) {
                Log.w("ResourceLoader", "Received invalid resource id: " + num, e);
            }
            parse = null;
        }
        if (parse != null) {
            return this.a.a(parse, i, i2);
        }
        return null;
    }
}
