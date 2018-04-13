package qsbk.app.fragments;

import android.widget.TextView;
import qsbk.app.image.FrescoImageloader;

class jq implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ QiuyouCircleFragment c;

    jq(QiuyouCircleFragment qiuyouCircleFragment, String str, int i) {
        this.c = qiuyouCircleFragment;
        this.a = str;
        this.b = i;
    }

    public void run() {
        FrescoImageloader.displayAvatar(this.c.o, this.a);
        TextView m = this.c.p;
        String str = "%s 条新消息";
        Object[] objArr = new Object[1];
        objArr[0] = this.b > 99 ? "99+" : Integer.valueOf(this.b);
        m.setText(String.format(str, objArr));
        this.c.n.setVisibility(0);
        this.c.a((this.b > 99 ? "99+" : Integer.valueOf(this.b)) + "");
    }
}
