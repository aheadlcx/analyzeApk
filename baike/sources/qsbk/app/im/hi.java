package qsbk.app.im;

class hi implements Runnable {
    final /* synthetic */ long a;
    final /* synthetic */ int b;
    final /* synthetic */ IMMessageListFragment c;

    hi(IMMessageListFragment iMMessageListFragment, long j, int i) {
        this.c = iMMessageListFragment;
        this.a = j;
        this.b = i;
    }

    public void run() {
        if (this.c.q != null) {
            this.c.q.onMsgStateChange(this.a, this.b);
        }
    }
}
