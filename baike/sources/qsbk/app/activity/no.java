package qsbk.app.activity;

class no implements Runnable {
    final /* synthetic */ GroupNoticeActivity a;

    no(GroupNoticeActivity groupNoticeActivity) {
        this.a = groupNoticeActivity;
    }

    public void run() {
        this.a.i.destroy(false);
    }
}
