package com.ishumei.dfp;

import android.content.Context;
import com.ishumei.b.c;
import java.io.IOException;

public class SMSDK {
    public static final int LOCAL_ID = 0;
    public static final int OLD_ID = 2;
    public static final int SERVER_ID = 1;
    public static final int UNKNOWN_ID = -1;

    static {
        try {
            System.loadLibrary("smsdk");
        } catch (Throwable th) {
            c.a(th);
        }
    }

    public static int idType(String str) {
        try {
            return new SMSDK().z3(str);
        } catch (Throwable th) {
            IOException iOException = new IOException(th);
        }
    }

    public static String x1(String str, String str2, String str3) {
        try {
            return new SMSDK().x2(str, str2, str3);
        } catch (Throwable th) {
            IOException iOException = new IOException(th);
        }
    }

    private native String x2(String str, String str2, String str3);

    public static String y1(boolean z) {
        try {
            return new SMSDK().y2(z);
        } catch (Throwable th) {
            IOException iOException = new IOException(th);
        }
    }

    private native String y2(boolean z);

    private native String z1(Context context);

    public static String z2(Context context) {
        try {
            return new SMSDK().z1(context);
        } catch (Throwable th) {
            IOException iOException = new IOException(th);
        }
    }

    private native int z3(String str);
}
