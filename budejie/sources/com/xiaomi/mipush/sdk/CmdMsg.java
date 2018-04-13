package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.a.a;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CmdMsg implements Serializable {
    Method a;
    Method b;
    Method c;
    Class cl;
    Method d;
    Method e;
    Object m;

    public Class getRemote() {
        return this.cl;
    }

    private CmdMsg(Context context, Object obj) {
        if (obj.getClass().getName().equals("com.xiaomi.mipush.sdk.MiPushCommandMessage")) {
            this.m = obj;
            try {
                this.cl = a.c(context).loadClass("com.xiaomi.mipush.sdk.MiPushCommandMessage");
                this.a = this.cl.getDeclaredMethod("getCommand", new Class[0]);
                this.a.setAccessible(true);
                this.b = this.cl.getDeclaredMethod("getCommandArguments", new Class[0]);
                this.b.setAccessible(true);
                this.c = this.cl.getDeclaredMethod("getResultCode", new Class[0]);
                this.c.setAccessible(true);
                this.d = this.cl.getDeclaredMethod("getReason", new Class[0]);
                this.d.setAccessible(true);
                this.e = this.cl.getDeclaredMethod("getCategory", new Class[0]);
                this.e.setAccessible(true);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static CmdMsg getInstance(Context context, Object obj) {
        return new CmdMsg(context, obj);
    }

    public String getCommand() {
        try {
            return (String) this.a.invoke(this.m, new Object[0]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
        return "";
    }

    public List getCommandArguments() {
        try {
            return (List) this.b.invoke(this.m, new Object[0]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
        return new ArrayList();
    }

    public long getResultCode() {
        try {
            return ((Long) this.c.invoke(this.m, new Object[0])).longValue();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return 0;
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public String getReason() {
        try {
            return (String) this.d.invoke(this.m, new Object[0]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
        return "";
    }

    public String getCategory() {
        try {
            return (String) this.e.invoke(this.m, new Object[0]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
        return "";
    }
}
