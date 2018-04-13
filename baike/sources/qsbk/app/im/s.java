package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.LivePullLauncher;

class s implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ long b;
    final /* synthetic */ d c;

    s(d dVar, String str, long j) {
        this.c = dVar;
        this.a = str;
        this.b = j;
    }

    public void onClick(View view) {
        LivePullLauncher.from(view.getContext()).with(this.a).withSource(this.b).withStSource("im").launch(0);
    }
}
