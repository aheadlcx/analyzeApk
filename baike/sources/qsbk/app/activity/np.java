package qsbk.app.activity;

class np implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ GroupNoticeActivity b;

    np(GroupNoticeActivity groupNoticeActivity, int i) {
        this.b = groupNoticeActivity;
        this.a = i;
    }

    public void run() {
        try {
            if (this.a == 5) {
                this.b.i.destroy(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
