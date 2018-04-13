package com.soundcloud.android.crop;

class j implements Runnable {
    final /* synthetic */ a a;

    j(a aVar) {
        this.a = aVar;
    }

    public void run() {
        this.a.a.removeLifeCycleListener(this.a);
        if (this.a.b.getWindow() != null) {
            this.a.b.dismiss();
        }
    }
}
