package com.soundcloud.android.crop;

class h implements Runnable {
    final /* synthetic */ a a;

    h(a aVar) {
        this.a = aVar;
    }

    public void run() {
        this.a.a();
        this.a.a.l.invalidate();
        if (this.a.a.l.a.size() == 1) {
            this.a.a.m = (k) this.a.a.l.a.get(0);
            this.a.a.m.setFocus(true);
        }
    }
}
