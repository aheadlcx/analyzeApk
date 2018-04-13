package com.baidu.mobstat;

import android.system.Os;

class k {
    static boolean a(String str, int i) {
        try {
            Os.chmod(str, i);
            return true;
        } catch (Throwable e) {
            g.b(e);
            return false;
        }
    }
}
