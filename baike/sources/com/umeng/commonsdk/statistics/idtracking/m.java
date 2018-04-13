package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;

public class m extends a {
    private Context a;

    public m(Context context) {
        super("umtt1");
        this.a = context;
    }

    public String f() {
        try {
            String str;
            Class cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
            if (cls != null) {
                str = (String) cls.getMethod("getUmtt1", new Class[]{Context.class}).invoke(cls, new Object[]{this.a});
            } else {
                str = null;
            }
            return str;
        } catch (ClassNotFoundException e) {
            return null;
        } catch (Throwable th) {
            return null;
        }
    }
}
