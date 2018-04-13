package com.iflytek.cloud.util;

import android.content.Context;
import com.iflytek.cloud.thirdparty.da;

public abstract class ContactManager {

    public interface ContactListener {
        void onContactQueryFinish(String str, boolean z);
    }

    protected ContactManager() {
    }

    public static ContactManager createManager(Context context, ContactListener contactListener) {
        return da.a(context, contactListener);
    }

    public static void destroy() {
        da.c();
    }

    public static ContactManager getManager() {
        return da.a();
    }

    public abstract void asyncQueryAllContactsName();

    public abstract String queryAllContactsName();
}
