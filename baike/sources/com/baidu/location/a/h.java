package com.baidu.location.a;

import android.content.Context;
import android.util.Log;
import com.baidu.lbsapi.auth.LBSAuthManager;
import com.baidu.lbsapi.auth.LBSAuthManagerListener;
import com.baidu.location.d.a;

public class h implements LBSAuthManagerListener {
    private static Object a = new Object();
    private static h b = null;
    private int c = 0;

    public static h a() {
        h hVar;
        synchronized (a) {
            if (b == null) {
                b = new h();
            }
            hVar = b;
        }
        return hVar;
    }

    public static String b(Context context) {
        try {
            return LBSAuthManager.getInstance(context).getPublicKey(context);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void a(Context context) {
        LBSAuthManager.getInstance(context).authenticate(false, "lbs_locsdk", null, this);
    }

    public boolean b() {
        return this.c == 0;
    }

    public void onAuthResult(int i, String str) {
        this.c = i;
        Log.i(a.a, "LocationAuthManager status = " + i);
    }
}
