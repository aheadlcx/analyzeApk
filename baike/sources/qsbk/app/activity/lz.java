package qsbk.app.activity;

class lz implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ GroupInfoActivity b;

    lz(GroupInfoActivity groupInfoActivity, String str) {
        this.b = groupInfoActivity;
        this.a = str;
    }

    public void run() {
        this.b.i();
        this.b.f.setMessage(this.a);
        this.b.f.show();
    }
}
