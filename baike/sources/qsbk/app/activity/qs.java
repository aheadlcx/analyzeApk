package qsbk.app.activity;

import android.text.TextUtils;
import qsbk.app.activity.LaiseeDetailActivity.VoiceManager.VoiceCallback;

class qs implements VoiceCallback {
    final /* synthetic */ qr a;

    qs(qr qrVar) {
        this.a = qrVar;
    }

    public void onStart(String str, Object obj) {
    }

    public void onSuccess(String str, String str2, Object obj) {
        if (TextUtils.equals((CharSequence) obj, this.a.a.voiceUrl)) {
            this.a.b.a = 2;
            this.a.b.a(str);
        } else {
            this.a.b.a = 0;
        }
        this.a.b.i.setVisibility(8);
    }

    public void onFaiure(String str, String str2, Object obj) {
        this.a.b.a = 0;
        this.a.b.i.setVisibility(8);
    }

    public void onProgress(String str, long j, long j2, Object obj) {
        if (TextUtils.equals((CharSequence) obj, this.a.a.voiceUrl)) {
            this.a.b.a = 1;
        }
        this.a.b.i.setVisibility(0);
    }
}
