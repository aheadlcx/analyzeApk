package com.budejie.www.activity;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnSeekCompleteListener;
import com.budejie.www.activity.DubbingPreviewActivity.AnonymousClass2;

class DubbingPreviewActivity$2$1 implements OnSeekCompleteListener {
    final /* synthetic */ AnonymousClass2 a;

    DubbingPreviewActivity$2$1(AnonymousClass2 anonymousClass2) {
        this.a = anonymousClass2;
    }

    public void onSeekComplete(MediaPlayer mediaPlayer) {
        DubbingPreviewActivity.a(this.a.a).start();
    }
}
