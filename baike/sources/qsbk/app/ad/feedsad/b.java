package qsbk.app.ad.feedsad;

import android.content.Context;

final class b implements Runnable {
    final /* synthetic */ Context a;

    b(Context context) {
        this.a = context;
    }

    public void run() {
        FeedsAdStat.onClick(this.a, "gdt");
    }
}
