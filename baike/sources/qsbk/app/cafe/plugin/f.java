package qsbk.app.cafe.plugin;

class f implements Runnable {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public void run() {
        this.a.c.b.getCurActivity().sendBroadcast(this.a.a);
    }
}
