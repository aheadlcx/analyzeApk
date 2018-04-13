package com.budejie.www.util;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

class ad$4 implements OnPreparedListener {
    final /* synthetic */ Handler a;
    final /* synthetic */ ad b;

    ad$4(ad adVar, Handler handler) {
        this.b = adVar;
        this.a = handler;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        int j = this.b.j();
        Log.i("MediaPlayerHandler2", "计算时长-->" + j);
        Message obtainMessage = this.a.obtainMessage();
        obtainMessage.what = 10;
        obtainMessage.obj = Integer.valueOf(j);
        this.a.sendMessage(obtainMessage);
        if (ad.a(this.b) != null) {
            ad.a(this.b).a();
        }
    }
}
