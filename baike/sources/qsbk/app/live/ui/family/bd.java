package qsbk.app.live.ui.family;

class bd implements Runnable {
    final /* synthetic */ FamilyRankFragment a;

    bd(FamilyRankFragment familyRankFragment) {
        this.a = familyRankFragment;
    }

    public void run() {
        this.a.e.setRefreshing(true);
        this.a.h = 1;
        this.a.a();
    }
}
