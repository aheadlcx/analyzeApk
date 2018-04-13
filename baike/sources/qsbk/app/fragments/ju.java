package qsbk.app.fragments;

class ju implements Runnable {
    final /* synthetic */ QiuyouCircleFragment a;

    ju(QiuyouCircleFragment qiuyouCircleFragment) {
        this.a = qiuyouCircleFragment;
    }

    public void run() {
        this.a.m.post(new jv(this));
    }
}
