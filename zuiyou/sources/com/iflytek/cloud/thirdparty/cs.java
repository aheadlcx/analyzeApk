package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.iflytek.msc.MSC;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class cs {
    public static int a = 3;
    public static int b = 4;
    public static int c = 7;
    public static int d = 8;
    private static final String e = (MSC.isIflyVersion() ? "iflytek/" : "cmcc/");
    private static final String f = ("assets/" + e);
    private static HashMap<String, Drawable> g = new HashMap();
    private static HashMap<String, Drawable> h = new HashMap();

    public static synchronized Drawable a(Context context, String str) throws Exception {
        Drawable drawable;
        synchronized (cs.class) {
            drawable = (Drawable) g.get(str);
            if (drawable == null) {
                drawable = c(context, str);
                g.put(str, drawable);
            }
        }
        return drawable;
    }

    public static View a(Context context, String str, ViewGroup viewGroup) throws Exception {
        return ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(context.getAssets().openXmlResourceParser(f + str + ".xml"), viewGroup);
    }

    public static int[] a() {
        return new int[]{-1579033, -9933198};
    }

    private static InputStream b(Context context, String str) throws IOException {
        return context.getAssets().open(str);
    }

    public static int[] b() {
        return new int[]{20, 16};
    }

    private static Drawable c(Context context, String str) throws Exception {
        InputStream b = b(context, e + str + ".png");
        TypedValue typedValue = new TypedValue();
        typedValue.density = 240;
        Drawable a = VERSION.SDK_INT > a ? ct.a(context.getResources(), typedValue, b, str, null) : Drawable.createFromResourceStream(context.getResources(), typedValue, b, str);
        if (b != null) {
            b.close();
        }
        return a;
    }
}
