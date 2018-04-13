package qsbk.app.nearby.ui;

import qsbk.app.utils.LogUtil;

class z implements Runnable {
    final /* synthetic */ InfoCompleteActivity a;

    z(InfoCompleteActivity infoCompleteActivity) {
        this.a = infoCompleteActivity;
    }

    public void run() {
        LogUtil.d("scrooll to bottom");
        this.a.o.scrollTo(0, this.a.o.getHeight() + 1000);
    }
}
