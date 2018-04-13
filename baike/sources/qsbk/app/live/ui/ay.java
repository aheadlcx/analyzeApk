package qsbk.app.live.ui;

import qsbk.app.live.model.LiveCommonMessage;

class ay implements Runnable {
    final /* synthetic */ long a;
    final /* synthetic */ long b;
    final /* synthetic */ LiveCommonMessage c;
    final /* synthetic */ LiveBaseActivity d;

    ay(LiveBaseActivity liveBaseActivity, long j, long j2, LiveCommonMessage liveCommonMessage) {
        this.d = liveBaseActivity;
        this.a = j;
        this.b = j2;
        this.c = liveCommonMessage;
    }

    public void run() {
        this.d.a(true, this.a, this.b, this.c);
    }
}
