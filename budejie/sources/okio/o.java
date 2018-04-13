package okio;

import android.support.v4.media.session.PlaybackStateCompat;

final class o {
    static n a;
    static long b;

    private o() {
    }

    static n a() {
        synchronized (o.class) {
            if (a != null) {
                n nVar = a;
                a = nVar.f;
                nVar.f = null;
                b -= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                return nVar;
            }
            return new n();
        }
    }

    static void a(n nVar) {
        if (nVar.f != null || nVar.g != null) {
            throw new IllegalArgumentException();
        } else if (!nVar.d) {
            synchronized (o.class) {
                if (b + PlaybackStateCompat.ACTION_PLAY_FROM_URI > 65536) {
                    return;
                }
                b += PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                nVar.f = a;
                nVar.c = 0;
                nVar.b = 0;
                a = nVar;
            }
        }
    }
}
