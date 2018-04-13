package com.budejie.www.activity;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnSeekCompleteListener;
import com.budejie.www.activity.GodDubbingActivity.AnonymousClass2;

class GodDubbingActivity$2$1 implements OnSeekCompleteListener {
    final /* synthetic */ AnonymousClass2 a;

    GodDubbingActivity$2$1(AnonymousClass2 anonymousClass2) {
        this.a = anonymousClass2;
    }

    public void onSeekComplete(MediaPlayer mediaPlayer) {
        GodDubbingActivity.e(this.a.a).setVisibility(8);
        GodDubbingActivity.D(this.a.a).start();
    }
}
