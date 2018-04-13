package com.getkeepsafe.relinker;

import android.os.Build;
import android.os.Build.VERSION;
import com.getkeepsafe.relinker.b.b;
import com.tencent.tinker.loader.shareutil.ShareConstants;

final class d implements b {
    d() {
    }

    public void a(String str) {
        System.loadLibrary(str);
    }

    public void b(String str) {
        System.load(str);
    }

    public String c(String str) {
        return (str.startsWith(ShareConstants.SO_PATH) && str.endsWith(".so")) ? str : System.mapLibraryName(str);
    }

    public String d(String str) {
        return str.substring(3, str.length() - 3);
    }

    public String[] a() {
        if (VERSION.SDK_INT >= 21 && Build.SUPPORTED_ABIS.length > 0) {
            return Build.SUPPORTED_ABIS;
        }
        if (e.a(Build.CPU_ABI2)) {
            return new String[]{Build.CPU_ABI};
        }
        return new String[]{Build.CPU_ABI, Build.CPU_ABI2};
    }
}
