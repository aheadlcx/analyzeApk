package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.Build.VERSION;
import android.view.View;

public class bw {
    public static int a = 9;
    public static int b = 14;

    public static void a(View view) {
        if (VERSION.SDK_INT >= b) {
            bx.a(view);
        }
    }

    public static boolean a(Context context, Boolean bool, OnAudioFocusChangeListener onAudioFocusChangeListener) {
        if (bool.booleanValue() && VERSION.SDK_INT >= a) {
            bx.a(context, onAudioFocusChangeListener);
        }
        return false;
    }

    public static boolean b(Context context, Boolean bool, OnAudioFocusChangeListener onAudioFocusChangeListener) {
        return (!bool.booleanValue() || VERSION.SDK_INT < a) ? false : bx.b(context, onAudioFocusChangeListener);
    }
}
