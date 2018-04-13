package qsbk.app.fragments;

import android.widget.TextView;
import qsbk.app.image.FrescoImageloader;

class id implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ QiushiListFragment c;

    id(QiushiListFragment qiushiListFragment, String str, int i) {
        this.c = qiushiListFragment;
        this.a = str;
        this.b = i;
    }

    public void run() {
        FrescoImageloader.displayAvatar(this.c.s, this.a);
        TextView l = this.c.t;
        String str = "%s 条新消息";
        Object[] objArr = new Object[1];
        objArr[0] = this.b > 99 ? "99+" : Integer.valueOf(this.b);
        l.setText(String.format(str, objArr));
        this.c.r.setVisibility(0);
        this.c.a((this.b > 99 ? "99+" : Integer.valueOf(this.b)) + "");
    }
}
