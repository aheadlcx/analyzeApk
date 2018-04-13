package qsbk.app.activity;

class nn implements Runnable {
    final /* synthetic */ GroupNoticeActivity a;

    nn(GroupNoticeActivity groupNoticeActivity) {
        this.a = groupNoticeActivity;
    }

    public void run() {
        this.a.onRefresh();
    }
}
