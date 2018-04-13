package qsbk.app.activity;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.LaiseeRecord;

class qr implements OnClickListener {
    final /* synthetic */ LaiseeRecord a;
    final /* synthetic */ d b;

    qr(d dVar, LaiseeRecord laiseeRecord) {
        this.b = dVar;
        this.a = laiseeRecord;
    }

    public void onClick(View view) {
        String pathIfDownload = this.b.k.i.getPathIfDownload(this.a.voiceUrl);
        if (TextUtils.isEmpty(pathIfDownload) || this.b.a == 0) {
            this.b.k.i.download(this.a.voiceUrl, new qs(this), this.a.voiceUrl);
        } else {
            this.b.a(pathIfDownload);
        }
    }
}
