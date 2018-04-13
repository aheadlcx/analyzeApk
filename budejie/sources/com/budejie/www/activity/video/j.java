package com.budejie.www.activity.video;

import android.util.Log;

public class j {
    private static boolean a = false;

    public j() throws Exception {
        if (!a()) {
            throw new Exception("Couldn't load native libs!!");
        }
    }

    private static boolean a() {
        if (a) {
            return true;
        }
        boolean z = false;
        try {
            if (i.d()) {
                System.loadLibrary("letvplayer_neon");
            } else {
                System.loadLibrary("letvplayer_vfp");
            }
        } catch (UnsatisfiedLinkError e) {
            Log.d("NativeLib", "Couldn't load lib: " + e.getMessage());
            z = true;
        }
        if (!z) {
            a = true;
        }
        return a;
    }
}
