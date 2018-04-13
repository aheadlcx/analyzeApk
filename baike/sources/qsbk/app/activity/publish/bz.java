package qsbk.app.activity.publish;

import qsbk.app.utils.LogUtil;

class bz implements Runnable {
    final /* synthetic */ Publish_Image a;

    bz(Publish_Image publish_Image) {
        this.a = publish_Image;
    }

    public void run() {
        this.a.t = this.a.b.getWidth();
        this.a.s = this.a.b.getHeight();
        LogUtil.d("imageLayoutWidth:" + this.a.t);
        LogUtil.d("imageLayoutHeight:" + this.a.s);
        this.a.a();
        this.a.e();
        this.a.a(this.a.d);
        this.a.a.setImageBitmap(this.a.d);
    }
}
