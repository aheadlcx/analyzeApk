package qsbk.app.im.voice;

import android.text.TextUtils;
import qsbk.app.core.AsyncTask;
import qsbk.app.im.voice.VoiceManager.VoiceCallback;
import qsbk.app.im.voice.VoiceManager.VoiceUploadTask;

class a extends AsyncTask<Void, Void, String> {
    final /* synthetic */ VoiceCallback a;
    final /* synthetic */ String b;
    final /* synthetic */ Object c;
    final /* synthetic */ VoiceUploadTask d;
    final /* synthetic */ VoiceManager e;

    a(VoiceManager voiceManager, VoiceCallback voiceCallback, String str, Object obj, VoiceUploadTask voiceUploadTask) {
        this.e = voiceManager;
        this.a = voiceCallback;
        this.b = str;
        this.c = obj;
        this.d = voiceUploadTask;
    }

    protected void a() {
        this.a.onStart(this.b, this.c);
    }

    protected String a(Void... voidArr) {
        return this.e.d(this.e.b);
    }

    protected void a(String str) {
        super.a(str);
        if (TextUtils.isEmpty(str)) {
            this.a.onFaiure(this.b, "", this.c);
            return;
        }
        this.d.setUploadTaskExecutor(this.e.a(str, this.b, this.a, this.c));
    }
}
