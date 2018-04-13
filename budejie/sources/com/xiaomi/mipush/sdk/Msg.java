package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.a.a;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Msg implements Serializable {
    Method a;
    Method b;
    Method c;
    Class cl;
    Method d;
    Method e;
    Method f;
    Method g;
    Method h;
    Method i;
    Method j;
    Method k;
    Object m;

    public Class getRemote() {
        return this.cl;
    }

    private Msg(Context context, Object obj) {
        if (obj.getClass().getName().equals("com.xiaomi.mipush.sdk.MiPushMessage")) {
            this.m = obj;
            try {
                this.cl = a.c(context).loadClass("com.xiaomi.mipush.sdk.MiPushMessage");
                this.a = this.cl.getDeclaredMethod("isArrivedMessage", new Class[0]);
                this.a.setAccessible(true);
                this.b = this.cl.getDeclaredMethod("isNotified", new Class[0]);
                this.b.setAccessible(true);
                this.c = this.cl.getDeclaredMethod("getContent", new Class[0]);
                this.c.setAccessible(true);
                this.d = this.cl.getDeclaredMethod("getTitle", new Class[0]);
                this.d.setAccessible(true);
                this.e = this.cl.getDeclaredMethod("getDescription", new Class[0]);
                this.e.setAccessible(true);
                this.f = this.cl.getDeclaredMethod("getNotifyType", new Class[0]);
                this.f.setAccessible(true);
                this.g = this.cl.getDeclaredMethod("getExtra", new Class[0]);
                this.g.setAccessible(true);
                this.h = this.cl.getDeclaredMethod("getAlias", new Class[0]);
                this.h.setAccessible(true);
                this.i = this.cl.getDeclaredMethod("getTopic", new Class[0]);
                this.i.setAccessible(true);
                this.j = this.cl.getDeclaredMethod("getCategory", new Class[0]);
                this.j.setAccessible(true);
                this.k = this.cl.getDeclaredMethod("getPassThrough", new Class[0]);
                this.k.setAccessible(true);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static Msg getInstance(Context context, Object obj) {
        return new Msg(context, obj);
    }

    public boolean isArrivedMessage() {
        try {
            return ((Boolean) this.a.invoke(this.m, new Object[0])).booleanValue();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean isNotified() {
        try {
            return ((Boolean) this.b.invoke(this.m, new Object[0])).booleanValue();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public String getContent() {
        try {
            return (String) this.c.invoke(this.m, new Object[0]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
        return "";
    }

    public String getTitle() {
        try {
            return (String) this.d.invoke(this.m, new Object[0]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
        return "";
    }

    public String getDescription() {
        try {
            return (String) this.e.invoke(this.m, new Object[0]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
        return "";
    }

    public int getNotifyType() {
        try {
            return ((Integer) this.f.invoke(this.m, new Object[0])).intValue();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return 0;
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public HashMap getExtra() {
        try {
            return (HashMap) this.g.invoke(this.m, new Object[0]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    public List getAlias() {
        try {
            return (List) this.h.invoke(this.m, new Object[0]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
        return new ArrayList();
    }

    public long getTopic() {
        try {
            return ((Long) this.i.invoke(this.m, new Object[0])).longValue();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return 0;
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public String getCategory() {
        try {
            return (String) this.j.invoke(this.m, new Object[0]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
        return "";
    }

    public int getPassThrough() {
        try {
            return ((Integer) this.k.invoke(this.m, new Object[0])).intValue();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return 0;
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            return 0;
        }
    }
}
