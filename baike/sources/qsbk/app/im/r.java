package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.LivePullLauncher;

class r implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ long b;
    final /* synthetic */ c c;

    r(c cVar, String str, long j) {
        this.c = cVar;
        this.a = str;
        this.b = j;
    }

    public void onClick(View view) {
        LivePullLauncher.from(view.getContext()).with(this.a).withSource(this.b).withStSource("im").launch(0);
    }
}
