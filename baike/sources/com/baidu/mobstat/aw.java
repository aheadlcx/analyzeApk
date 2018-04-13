package com.baidu.mobstat;

import android.content.Context;
import org.json.JSONObject;

class aw implements l {
    private ba a = ba.a;
    private Object b;
    private Class<?> c;

    public aw(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("proxy is null.");
        } else if ("com.baidu.bottom.remote.BPStretegyController2".equals(obj.getClass().getName())) {
            this.b = obj;
            this.c = obj.getClass();
        } else {
            throw new IllegalArgumentException("class isn't com.baidu.bottom.remote.BPStretegyController2");
        }
    }

    public void a(Context context, JSONObject jSONObject) {
        try {
            a(new Object[]{context, jSONObject}, "startDataAnynalyze", new Class[]{Context.class, JSONObject.class});
        } catch (Throwable e) {
            bd.b(e);
            this.a.a(context, jSONObject);
        }
    }

    public void a(Context context, String str) {
        try {
            a(new Object[]{context, str}, "saveRemoteConfig2", new Class[]{Context.class, String.class});
        } catch (Throwable e) {
            bd.b(e);
            this.a.a(context, str);
        }
    }

    public void b(Context context, String str) {
        try {
            a(new Object[]{context, str}, "saveRemoteSign", new Class[]{Context.class, String.class});
        } catch (Throwable e) {
            bd.b(e);
            this.a.b(context, str);
        }
    }

    public void a(Context context, long j) {
        try {
            a(new Object[]{context, Long.valueOf(j)}, "setLastUpdateTime", new Class[]{Context.class, Long.TYPE});
        } catch (Throwable e) {
            bd.b(e);
            this.a.a(context, j);
        }
    }

    public boolean a(Context context) {
        try {
            return ((Boolean) a(new Object[]{context}, "needUpdate", new Class[]{Context.class})).booleanValue();
        } catch (Throwable e) {
            bd.b(e);
            return this.a.a(context);
        }
    }

    public boolean b(Context context) {
        try {
            return ((Boolean) a(new Object[]{context}, "canStartService", new Class[]{Context.class})).booleanValue();
        } catch (Throwable e) {
            bd.b(e);
            return this.a.b(context);
        }
    }

    private <T> T a(Object[] objArr, String str, Class<?>[] clsArr) {
        return this.c.getMethod(str, clsArr).invoke(this.b, objArr);
    }
}
