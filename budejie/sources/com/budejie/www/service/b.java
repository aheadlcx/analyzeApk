package com.budejie.www.service;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.Handler;
import com.budejie.www.util.aa;

public class b implements OnAudioFocusChangeListener, a {
    private final String a = "AudioFocusHelperImpl";
    private Handler b;

    public b(Handler handler) {
        this.b = handler;
    }

    public boolean a(Context context, int i) {
        return 1 == ((AudioManager) context.getSystemService("audio")).requestAudioFocus(this, i, 1);
    }

    public boolean b(Context context, int i) {
        return 1 == ((AudioManager) context.getSystemService("audio")).abandonAudioFocus(this);
    }

    public void onAudioFocusChange(int i) {
        aa.e("AudioFocusHelperImpl", "MediaCommandPlayerImpl.onAudioFocusChange(): Unexpected audio focus change: " + i);
        this.b.sendEmptyMessage(1020);
    }
}
