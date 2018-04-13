package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.Build.VERSION;
import android.view.View;

public class bx {
    private static int a = 0;

    public static void a(View view) {
        if (VERSION.SDK_INT >= 11) {
            view.setLayerType(1, null);
        }
    }

    public static boolean a(Context context, OnAudioFocusChangeListener onAudioFocusChangeListener) {
        try {
            cb.a("start request music_stream focus");
            ((AudioManager) context.getSystemService("audio")).requestAudioFocus(onAudioFocusChangeListener, 3, 2);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean b(Context context, OnAudioFocusChangeListener onAudioFocusChangeListener) {
        try {
            cb.a("start abandon audio focus");
            ((AudioManager) context.getSystemService("audio")).abandonAudioFocus(onAudioFocusChangeListener);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
