package android.support.v4.media.session;

import android.media.Rating;
import android.media.RemoteControlClient.OnMetadataUpdateListener;
import android.support.v4.media.RatingCompat;

class d implements OnMetadataUpdateListener {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public void onMetadataUpdate(int i, Object obj) {
        if (i == 268435457 && (obj instanceof Rating)) {
            this.a.a(19, (Object) RatingCompat.fromRating(obj));
        }
    }
}
