package qsbk.app.fragments;

class bg implements Runnable {
    final /* synthetic */ bf a;

    bg(bf bfVar) {
        this.a = bfVar;
    }

    public void run() {
        CircleTopicsFragment.d(this.a.a).autoRefresh();
    }
}
