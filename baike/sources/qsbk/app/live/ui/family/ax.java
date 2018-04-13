package qsbk.app.live.ui.family;

class ax implements Runnable {
    final /* synthetic */ FamilyMessageActivity a;

    ax(FamilyMessageActivity familyMessageActivity) {
        this.a = familyMessageActivity;
    }

    public void run() {
        this.a.g.setRefreshing(true);
        this.a.h = 1;
        this.a.i = 0;
        this.a.c();
    }
}
