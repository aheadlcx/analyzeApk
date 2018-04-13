package qsbk.app.activity;

class mc implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ GroupInfoActivity c;

    mc(GroupInfoActivity groupInfoActivity, int i, int i2) {
        this.c = groupInfoActivity;
        this.a = i;
        this.b = i2;
    }

    public void run() {
        if (this.a == this.c.G) {
            switch (this.b) {
                case 1:
                    this.c.n();
                    return;
                case 2:
                case 3:
                    this.c.E = false;
                    this.c.b.joinStatus = 0;
                    this.c.m();
                    return;
                default:
                    return;
            }
        }
    }
}
