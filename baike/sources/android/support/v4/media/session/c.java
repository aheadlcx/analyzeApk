package android.support.v4.media.session;

import android.media.RemoteControlClient.OnPlaybackPositionUpdateListener;

class c implements OnPlaybackPositionUpdateListener {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public void onPlaybackPositionUpdate(long j) {
        this.a.a(18, (Object) Long.valueOf(j));
    }
}
