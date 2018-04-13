package com.tencent.smtt.sdk;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.sdk.TbsReaderView.ReaderCallback;

public class k {
    private DexLoader a;
    private ReaderCallback b;

    public k(ReaderCallback readerCallback) {
        this.a = null;
        this.b = null;
        this.a = b();
        this.b = readerCallback;
    }

    public static Drawable a(int i) {
        DexLoader b = b();
        if (b != null) {
            Object invokeStaticMethod = b.invokeStaticMethod("com.tencent.tbs.reader.TbsReader", "getResDrawable", new Class[]{Integer.class}, Integer.valueOf(i));
            if (invokeStaticMethod instanceof Drawable) {
                return (Drawable) invokeStaticMethod;
            }
        }
        return null;
    }

    public static boolean a(Context context) {
        DexLoader b = b();
        if (b != null) {
            Object invokeStaticMethod = b.invokeStaticMethod("com.tencent.tbs.reader.TbsReader", "isSupportCurrentPlatform", new Class[]{Context.class}, context);
            if (invokeStaticMethod instanceof Boolean) {
                return ((Boolean) invokeStaticMethod).booleanValue();
            }
        }
        return false;
    }

    public static boolean a(String str) {
        DexLoader b = b();
        if (b != null) {
            Object invokeStaticMethod = b.invokeStaticMethod("com.tencent.tbs.reader.TbsReader", "isSupportExt", new Class[]{String.class}, str);
            if (invokeStaticMethod instanceof Boolean) {
                return ((Boolean) invokeStaticMethod).booleanValue();
            }
        }
        return false;
    }

    private static DexLoader b() {
        ay c = l.a(true).c();
        return c != null ? c.b() : null;
    }

    public static String b(int i) {
        String str = "";
        DexLoader b = b();
        if (b != null) {
            Object invokeStaticMethod = b.invokeStaticMethod("com.tencent.tbs.reader.TbsReader", "getResString", new Class[]{Integer.class}, Integer.valueOf(i));
            if (invokeStaticMethod instanceof String) {
                return (String) invokeStaticMethod;
            }
        }
        return str;
    }

    public Object a() {
        return this.a.newInstance("com.tencent.tbs.reader.TbsReader", new Class[0], new Object[0]);
    }

    public void a(Object obj) {
        this.b = null;
        if (this.a == null || obj == null) {
            Log.e("ReaderWizard", "destroy:Unexpect null object!");
            return;
        }
        this.a.invokeMethod(obj, "com.tencent.tbs.reader.TbsReader", "destroy", new Class[0], new Object[0]);
    }

    public void a(Object obj, int i, int i2) {
        if (this.a == null) {
            Log.e("ReaderWizard", "onSizeChanged:Unexpect null object!");
            return;
        }
        Object[] objArr = new Object[]{new Integer(i), new Integer(i2)};
        Object obj2 = obj;
        this.a.invokeMethod(obj2, "com.tencent.tbs.reader.TbsReader", "onSizeChanged", new Class[]{Integer.class, Integer.class}, objArr);
    }

    public void a(Object obj, Integer num, Object obj2, Object obj3) {
        if (this.a == null) {
            Log.e("ReaderWizard", "doCommand:Unexpect null object!");
            return;
        }
        Object[] objArr = new Object[]{new Integer(num.intValue()), obj2, obj3};
        Object obj4 = obj;
        this.a.invokeMethod(obj4, "com.tencent.tbs.reader.TbsReader", "doCommand", new Class[]{Integer.class, Object.class, Object.class}, objArr);
    }

    public void a(Object obj, String str) {
        if (this.a == null) {
            Log.e("ReaderWizard", "userStatistics:Unexpect null object!");
            return;
        }
        Object obj2 = obj;
        this.a.invokeMethod(obj2, "com.tencent.tbs.reader.TbsReader", "userStatistics", new Class[]{String.class}, str);
    }

    public boolean a(Object obj, Context context) {
        if (this.a == null || obj == null) {
            Log.e("ReaderWizard", "initTbsReader:Unexpect null object!");
            return false;
        }
        Object obj2 = obj;
        Object invokeMethod = this.a.invokeMethod(obj2, "com.tencent.tbs.reader.TbsReader", UserTrackerConstants.P_INIT, new Class[]{Context.class, DexLoader.class, Object.class}, context, this.a, this);
        if (invokeMethod instanceof Boolean) {
            return ((Boolean) invokeMethod).booleanValue();
        }
        Log.e("ReaderWizard", "Unexpect return value type of call initTbsReader!");
        return false;
    }

    public boolean a(Object obj, Context context, Bundle bundle, FrameLayout frameLayout) {
        if (this.a == null) {
            Log.e("ReaderWizard", "openFile:Unexpect null object!");
            return false;
        }
        Object obj2 = obj;
        Object invokeMethod = this.a.invokeMethod(obj2, "com.tencent.tbs.reader.TbsReader", "openFile", new Class[]{Context.class, Bundle.class, FrameLayout.class}, context, bundle, frameLayout);
        if (invokeMethod instanceof Boolean) {
            return ((Boolean) invokeMethod).booleanValue();
        }
        Log.e("ReaderWizard", "Unexpect return value type of call openFile!");
        return false;
    }

    public boolean a(Object obj, Context context, String str, boolean z) {
        if (this.a == null) {
            Log.e("ReaderWizard", "checkPlugin:Unexpect null object!");
            return false;
        }
        Object obj2 = obj;
        Object invokeMethod = this.a.invokeMethod(obj2, "com.tencent.tbs.reader.TbsReader", "checkPlugin", new Class[]{Context.class, String.class, Boolean.class}, context, str, Boolean.valueOf(z));
        if (invokeMethod instanceof Boolean) {
            return ((Boolean) invokeMethod).booleanValue();
        }
        Log.e("ReaderWizard", "Unexpect return value type of call checkPlugin!");
        return false;
    }
}
